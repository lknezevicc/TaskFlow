package hr.lknezevic.taskflow.taskflowserver.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.*;

public class PasswordUtil {
    // Generiranje soli na temelju lozinke
    private static String generateSalt(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());

            byte[] salt = new byte[password.length()];
            System.arraycopy(hash, 0, salt, 0, password.length());

            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Generiranje papra bez rekurzije
    private static int generatePepper(){
        SecureRandom random = new SecureRandom();
        return random.nextInt(1000) + 1;
    }

    // Hashiranje lozinke
    public static String hashPassword(String password) {
        try {
            String salt = generateSalt(password);
            int pepper = generatePepper();

            String combined = password + salt + pepper;

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(combined.getBytes());

            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Provjera lozinke pomoću višedretvenosti
    public static boolean verifyPassword(String password, String storedHash) {
        String[] parts = storedHash.split(":");
        String originalHash = parts[0];

        String salt = generateSalt(password);

        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            // Prva dretva: provjerava od 1 do 500
            Callable<Boolean> task1 = () -> bruteForceCheck(password, salt, originalHash, 1, 500);

            // Druga dretva: provjerava od 501 do 1000
            Callable<Boolean> task2 = () -> bruteForceCheck(password, salt, originalHash, 501, 1000);

            Future<Boolean> future1 = executor.submit(task1);
            Future<Boolean> future2 = executor.submit(task2);

            // Ako bilo koja dretva pronađe ispravan pepper, vrati true
            boolean result = future1.get() || future2.get();

            executor.shutdown();
            return result;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Brute-force provjera za određeni raspon paprike
    private static boolean bruteForceCheck(String password, String salt, String originalHash, int start, int end) {
        try {
            for (int i = start; i <= end; i++) {
                String combined = password + salt + i;
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(combined.getBytes());
                String computedHash = Base64.getEncoder().encodeToString(hash);

                if (computedHash.equals(originalHash)) {
                    return true; // Pronađen ispravan pepper
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }
}

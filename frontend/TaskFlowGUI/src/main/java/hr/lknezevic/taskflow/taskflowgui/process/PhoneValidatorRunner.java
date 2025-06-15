package hr.lknezevic.taskflow.taskflowgui.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PhoneValidatorRunner {
    public boolean run(String phoneNumber, String phonePrefix) {
        ProcessBuilder processBuilder = new ProcessBuilder("java", "PhoneValidator",  phoneNumber, phonePrefix);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[PhoneValidator]: " + line);
            }

            int exitCode = process.waitFor();
            return exitCode == 0;

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}

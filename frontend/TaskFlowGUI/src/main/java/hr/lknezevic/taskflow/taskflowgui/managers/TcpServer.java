package hr.lknezevic.taskflow.taskflowgui.managers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    public static void main(String[] args) {
        final int PORT = 5005;
        File pdfFile = new File("sample.pdf"); // ← Ovdje stavi svoj PDF

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("TCP server pokrenut na portu " + PORT);

            while (true) {
                try (
                        Socket clientSocket = serverSocket.accept();
                        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())
                ) {
                    System.out.println("Klijent povezan: " + clientSocket.getInetAddress());

                    String request = in.readUTF();
                    System.out.println("Zahtjev primljen: " + request);

                    if ("GET_REPORT".equals(request)) {
                        long length = pdfFile.length();
                        out.writeLong(length); // Prvo pošalji veličinu

                        try (FileInputStream fis = new FileInputStream(pdfFile)) {
                            byte[] buffer = new byte[4096];
                            int bytesRead;
                            while ((bytesRead = fis.read(buffer)) != -1) {
                                out.write(buffer, 0, bytesRead);
                            }
                            out.flush();
                            System.out.println("📤 PDF datoteka poslana (" + length + " bajtova)");
                        }
                    } else {
                        System.out.println("Nepoznat zahtjev: " + request);
                        out.writeLong(0); // signaliziraj da nema fajla
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Greška pri pokretanju servera", e);
        }
    }
}
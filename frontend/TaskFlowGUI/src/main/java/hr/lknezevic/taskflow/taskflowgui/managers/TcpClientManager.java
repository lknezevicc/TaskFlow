package hr.lknezevic.taskflow.taskflowgui.managers;

import com.google.inject.Inject;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class TcpClientManager {
    private static final int SERVER_PORT = 5005;
    private final static String SERVER_IP = "localhost";
    private final ExecutorService executor;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    @Inject
    public TcpClientManager(ExecutorService executor) {
        this.executor = executor;
    }

    private void connect() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("TCP connection established");
        } catch (IOException e) {
            throw new RuntimeException("Neuspješno spajanje na TCP server", e);
        }
    }

    public void requestReport() {
        if (socket == null || socket.isClosed()) {
            connect();
        }
        executor.submit(this::sendReportRequest);
    }

    private void sendReportRequest() {
        System.out.println("Thread: " + Thread.currentThread().getName());
        try {
            out.writeUTF("GET_REPORT");
            out.flush();

            long fileLength = in.readLong();

            // 🛠 osiguraj da postoji "reports" direktorij
            File dir = new File("reports");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            System.out.println(dir.getAbsolutePath());

            File pdf = new File(dir, fileLength + ".pdf");

            try (FileOutputStream fos = new FileOutputStream(pdf)) {
                byte[] buffer = new byte[4096];
                long totalRead = 0;

                while (totalRead < fileLength) {
                    int bytesRead = in.read(buffer);
                    if (bytesRead == -1) break;
                    fos.write(buffer, 0, bytesRead);
                    totalRead += bytesRead;
                }

                System.out.println("📥 PDF spremljen: " + pdf.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

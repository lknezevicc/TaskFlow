package hr.lknezevic.taskflow.taskflowgui.managers;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class UdpServer {

    public static void main(String[] args) {
        final int PORT = 5000;
        byte[] buffer = new byte[2048];

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("UDP server pokrenut na portu " + PORT);

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(request);

                String message = new String(request.getData(), 0, request.getLength(), StandardCharsets.UTF_8);
                System.out.println("Primljeno: " + message);

                String responseXml;
                if ("GET_SIDEBAR_XML".equals(message)) {
                    responseXml = """
                        <?xml version="1.0" encoding="UTF-8"?>
                        <sidebar>
                            <section title="Naslov 1">Sadržaj 1</section>
                            <section title="Naslov 2">Sadržaj 2</section>
                        </sidebar>
                        """;
                } else {
                    responseXml = "<error>Nepoznat zahtjev</error>";
                }

                byte[] responseBytes = responseXml.getBytes(StandardCharsets.UTF_8);
                DatagramPacket response = new DatagramPacket(
                        responseBytes,
                        responseBytes.length,
                        request.getAddress(),
                        request.getPort()
                );

                serverSocket.send(response);
                System.out.println("XML odgovor poslan.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package hr.lknezevic.taskflow.taskflowgui.managers;

import com.google.inject.Inject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;

public class UdpClientManager {
    private static final int SERVER_PORT = 5000;
    private final ExecutorService executor;

    @Inject
    public UdpClientManager(ExecutorService executor) {
        this.executor = executor;
    }

    public void requestSidebarXml() {
        executor.submit(this::sendSidebarXmlRequest);
    }

    private void sendSidebarXmlRequest()  {
        System.out.println("Thread: " + Thread.currentThread().getName());
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("localhost");
            String message = "GET_SIDEBAR_XML";

            byte[] buf = message.getBytes(StandardCharsets.UTF_8);
            DatagramPacket reqPacket = new DatagramPacket(buf, buf.length, address, SERVER_PORT);
            socket.send(reqPacket);

            byte[] responseBuf = new byte[4096];
            DatagramPacket response = new DatagramPacket(responseBuf, responseBuf.length);
            socket.receive(response);

            // ByteArrayInputStream bais = new ByteArrayInputStream(response.getData(), 0, response.getLength());

            String xml = new String(response.getData(), 0, response.getLength(), StandardCharsets.UTF_8);

            // Obrada odgovora
            System.out.println("Primljen XML: " + xml);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

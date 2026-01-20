package lab9;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastServer {

    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("230.0.0.1"); // Multicast IP
            int port = 4446;

            MulticastSocket socket = new MulticastSocket();
            String message = "Hello from Multicast Server";

            while (true) {
                byte[] buffer = message.getBytes();
                DatagramPacket packet =
                        new DatagramPacket(buffer, buffer.length, group, port);

                socket.send(packet);
                System.out.println("Message sent: " + message);

                Thread.sleep(2000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

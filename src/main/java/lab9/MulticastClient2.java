package lab9;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient2 {

    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("230.0.0.1");
            int port = 4446;

            // Expected sender host (change if needed)
            String expectedHost = InetAddress.getLocalHost().getHostAddress();

            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(group);

            System.out.println("Waiting for multicast messages...");

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String senderIP = packet.getAddress().getHostAddress();
                String received = new String(packet.getData(), 0, packet.getLength());

                if (senderIP.equals(expectedHost)) {
                    System.out.println("Received from expected host: " + senderIP);
                    System.out.println("Message: " + received);
                } else {
                    System.out.println("Message from unknown host: " + senderIP);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


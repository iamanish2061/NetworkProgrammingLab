package lab8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    public static void main(String[] args){
        try (DatagramSocket dSocket = new DatagramSocket(2000)) {

            DatagramPacket requestFromClient = new DatagramPacket(new byte[1024], 0, 1024);
            dSocket.receive(requestFromClient);

            System.out.println("---------Request from client -------------");
            System.out.println( new String(requestFromClient.getData(), 0, requestFromClient.getLength()));
            InetAddress clientAddress = requestFromClient.getAddress();
            int clientPort = requestFromClient.getPort();

            System.out.println("---------Response to client -------------");
            String message = "Hello from server";
            System.out.println(message);
            byte[] data = message.getBytes("US-ASCII");

            DatagramPacket responseToClient = new DatagramPacket(data, data.length, clientAddress, clientPort);
            dSocket.send(responseToClient);

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}

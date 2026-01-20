package lab8;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class Client {

//    Question:
//    Write a program for two way communication between UDP Client and UDP Server.
    public static void main(String[] args){
        try (DatagramSocket dSocket = new DatagramSocket(2002)) {
            dSocket.setSoTimeout(10000);
            InetAddress host = InetAddress.getLocalHost();
            int serverPort = 2000;
            String message = "Are you up?";
            byte[] data = message.getBytes();
            System.out.println("---------Request to server---------");
            System.out.println(message);

            DatagramPacket request = new DatagramPacket(data, data.length, host, serverPort);

            byte[] responseData = new byte[1024];
            DatagramPacket response = new DatagramPacket(responseData, responseData.length);
            dSocket.send(request);
            dSocket.receive(response);

            System.out.println("---------Response from server---------");
            System.out.println(new String(response.getData(), 0, response.getLength()));

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}

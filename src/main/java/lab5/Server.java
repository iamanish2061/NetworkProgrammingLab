package lab5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();
            System.out.println("Client connected!");
            System.out.println("-----------------------------------------------------");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            String message = dis.readUTF();
            System.out.println("Message received from Client: " + message);
            System.out.println("-----------------------------------------------------");

            System.out.println("Sending acknowledgement to client");
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF("Hey client! Your message has been received!");
            dos.flush();
            dos.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}




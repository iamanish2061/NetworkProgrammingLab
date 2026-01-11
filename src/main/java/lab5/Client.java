package lab5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 6666);

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());

            dos.writeUTF("Hello Server! This is a test message.");
            System.out.println("Message sent to server.");
            System.out.println("------------------------------------------------");

            String message = dis.readUTF();
            System.out.println("Message from server: "+message);

            dos.flush();
            dos.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

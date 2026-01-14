package lab6;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class Solution {

//    a. Write a program creating secure sockets with google.com

    public static void main(String[] args) throws IOException {

        String hostname = "www.google.com";
        int port = 443;

        try {
            System.out.println("Starting SSL Handshake...");

            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) factory.createSocket(hostname, port);
            socket.startHandshake();
            System.out.println("Secure connection established with: " + hostname);
            System.out.println("Cipher Suite: " + socket.getSession().getCipherSuite());
            System.out.println("Protocol: " + socket.getSession().getProtocol());

            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.println("GET / HTTP/1.1");
            out.println("Host: " + hostname);
            out.println("");
            out.flush();

            // response
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            int lineCount=0;
            System.out.println("Server Response...");
            while ((line = in.readLine()) != null && lineCount<5) {
                System.out.println(line);
                lineCount++;
            }

            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

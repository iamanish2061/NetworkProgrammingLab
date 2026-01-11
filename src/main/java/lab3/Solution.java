package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        question1();
        question2();
    }

//    Write a program to connect to server assuming that HTTP server is running on the localhost (IP address 127.0.0.1) at port 8000.
    private static void question1(){
        String urlString = "http://127.0.0.1:8080/index.html";
        System.out.println("Attempting to connect to: " + urlString);
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Server Response Code: " + responseCode);

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                int count = 0;
                while ((inputLine = in.readLine()) != null && count < 5) {
                    System.out.println(inputLine);
                    count++;
                }
                in.close();
            } else {
                System.out.println("Connection failed or file not found.");
            }

        } catch (ConnectException e) {
            System.out.println("Error: Could not connect. Is the server running on port 8000?");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    Write a program implementing CookieHandler class to retrieve cookies from a server.
    private static void question2(){
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        try {
            URL url = new URL("http://httpbin.org/cookies/set?myCookie=JavaLab&session=12345");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            Map<String, List<String>> headerFields = connection.getHeaderFields();
            List<String> cookiesHeader = headerFields.get("Set-Cookie");
            if(cookiesHeader != null) {
                for (String cookie : cookiesHeader)
                    cookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
            }
            System.out.println("Cookies: "+cookieManager.getCookieStore().getCookies());
            connection.disconnect();
        }catch(Exception ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }

}








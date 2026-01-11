package lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();

    }
//    1. Write a program to download a webpage using URLConnection.
    private static void question1(){
        try {
            URL url = new URL("https://acem.edu.np/pages/associations-clubs/");
            URLConnection uc = url.openConnection();

            try(InputStream is = uc.getInputStream()){
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                int c;
                int count=0;
                while ((c = reader.read()) != -1 && count<5) {
                    System.out.print((char) c);
                    if(c == '>'){
                        System.out.println();
                        count++;
                    }
                }
                reader.close();
            }
        }
        catch(IOException ex) {
            System.out.println("error: "+ex);
        }
    }

//    2. Write a program to print entire HTTP Header.
    private static void question2(){
        try {
            URL url = new URL("https://acem.edu.np/pages/associations-clubs/");
            URLConnection uc = url.openConnection();
            Map<String, List<String>> headerFields = uc.getHeaderFields();

            headerFields.forEach((k, v)->{
                System.out.print(k +" : ");
                if(v.size()>1) {
                    v.forEach(val->System.out.print(val+" , "));
                }else {
                    v.forEach(val->System.out.print(val));
                }
                System.out.println();
            });
        }
        catch(IOException ex) {
            System.out.println("error: "+ex);
        }
    }

//    3.  Write a program to print the URL of a URLConnection to “google.com”.
    private static void question3(){
		try {
			URL url = new URL("https://acem.edu.np/pages/associations-clubs/");
			URLConnection uc = url.openConnection();
			System.out.println("The url is: "+ uc.getURL());
		}
		catch(IOException ex) {
			System.out.println("error: "+ex);
		}
    }

//    4. Write a program to retrieve HTTP response code using HTTP Request Method.
    private static void question4(){
        try {
            URL url = new URL("https://acem.edu.np/pages/associations-clubs/");
            HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
            httpConnection.setRequestMethod("POST");
            System.out.println("The response code is: "+ httpConnection.getResponseCode());
        }
        catch(IOException ex) {
            System.out.println("error: "+ex);
        }
    }

//    5. Write a program to get the time when a URL was last changed.
    private static void question5(){
        try {
            URL url = new URL("https://example.com");
            HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
            httpConnection.setRequestMethod("HEAD");
            Date lastModifiedDate = new Date(httpConnection.getLastModified());
            System.out.println("The last changed time is: "+ lastModifiedDate);
        }
        catch(IOException ex) {
            System.out.println("error: "+ex.getMessage());
        }
    }

}


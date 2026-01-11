package lab2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Solution {

    public static void main(String[] args) {
//        question1();
//        question2();
//        question3();
        question4();
    }

//    1. Write a program that splits the parts of a URL.
    public static void question1(){
            String url = "https://www.example.com:8080/path/to/resource?query_param=value&another_param=another_value#section-id";
            try {
                URL urlObject = new URL(url);
                System.out.println("Portocol: " +urlObject.getProtocol());
                System.out.println("Host: " +urlObject.getHost());
                System.out.println("Path: " +urlObject.getPath());
                System.out.println("Port: " +urlObject.getPort());
                System.out.println("Query: " +urlObject.getQuery());
                System.out.println("Fragment: " +urlObject.getRef());

            }catch(MalformedURLException e) {
                System.out.println("Error : "+e.getMessage());
            }
        }

//    2. Write a program to download a web page of a given web address.
    public static void question2() {
        String acem = "https://acem.edu.np/";
        try {
            URL url = new URL(acem);
            InputStream iStream = url.openStream();
            Reader r = new InputStreamReader(iStream);
            int c;
            while ((c = r.read()) != -1) {
                System.out.print((char) c);
                if(c == '>') System.out.println();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    3. Write a program to download an object.
    public static void question3() {
      String imageUrl = "https://acem.edu.np/uploads/news/image/x0_20251104090055.jpg.pagespeed.ic.KEVYcIez3X.webp";
		String destinationFile = "D:\\Applications\\image.jpg";


        try (InputStream in = new URL(imageUrl).openStream()) {
            Files.copy(in, Paths.get(destinationFile), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image downloaded successfully: " + destinationFile);
        } catch (IOException e) {
            System.err.println("Error downloading image: " + e.getMessage());
        }
    }

//    4. Write a program for resolving relatives URI.
    public static void question4(){
        try {
            URI baseUri = new URI("https://example.com/folder/");

            URI relativeUri = new URI("images/photo.jpg");

            URI resolvedUri = baseUri.resolve(relativeUri);

            System.out.println("Base URI:      " + baseUri);
            System.out.println("Relative URI:  " + relativeUri);
            System.out.println("Resolved URI:  " + resolvedUri);
        } catch (URISyntaxException e) {
            System.err.println("Invalid URI: " + e.getMessage());
        }
    }

}

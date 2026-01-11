package lab1;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        question6();
    }

//    Write a program to find the hostname from the given address.
    private static void question1() {
        Scanner scan = new Scanner(System.in);
        System.out.println("To find hostname, please enter ip address:");
        String ipAddress = scan.next();

        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            System.out.println("Hostname is: "+ address.getHostName());
        }catch(UnknownHostException e) {
            System.out.println("Error: "+ e.getMessage());
        }
    }

//    Write a program to find the IP address of the local machine.
    private static void question2() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("IP Address of local machine is: "+ address.getHostAddress());
        }catch(UnknownHostException e) {
            System.out.println("Error : "+e.getMessage());
        }
    }

//    Write a program to verify the given domain names are same or not.
    private static void question3() {
        String domain1 = "google.com";
        String domain2 = "fb.com";

        try {
            InetAddress address1 = InetAddress.getByName(domain1);
            InetAddress address2 = InetAddress.getByName(domain2);

            if(address1.getHostName().equals(address2.getHostName()))
                System.out.println(domain1 +" and " +domain2 + " are same!");
            else
                System.out.println(domain1 +" and " +domain2 + " are not same!");

        }catch(UnknownHostException e) {
            System.out.println("Error : "+e.getMessage());
        }
    }

//    Write a program for determining whether an IP address is IPv4 or IPv6.
    private static void question4() {
        Scanner scan =new Scanner(System.in);

        System.out.println("Enter Ip address to check: ");
        String ip = scan.next();

        try {
            InetAddress a1 = InetAddress.getByName(ip);

            if (a1 instanceof Inet4Address) {
                System.out.println(ip +" is IPV4 Address!");
            }else if(a1 instanceof Inet6Address){
                System.out.println(ip +" is IPV6 Address!");
            }else {
                System.out.println(ip + " is mixed Address");
            }

        }catch(UnknownHostException e) {
            System.out.println("Error : "+e.getMessage());
        }
    }

//    Write a program to check remote system is reachable or not.
    private static void question5() {
        String ipAddress = "8.8.8.8";
        try {
            InetAddress add = InetAddress.getByName(ipAddress);
            if(add.isReachable(500))
                System.out.println(ipAddress +" is reachable!");
            else
                System.out.println(ipAddress +" is not reachable!");
        }catch(IOException e) {
            System.out.println("Error : "+ e.getMessage());
        }
    }

//    Write a program to list all the network interfaces.
    private static void question6() {
        Enumeration<NetworkInterface> ni;
        try {
            ni = NetworkInterface.getNetworkInterfaces();
            while(ni.hasMoreElements()) {
                NetworkInterface n = ni.nextElement();
                System.out.println("Name: "+ n.getName());
                System.out.println("Display Name: "+ n.getDisplayName());
                System.out.println("Hardware Address: "+ n.getHardwareAddress());
            }
        } catch (SocketException e) {
            System.out.println("Error : "+ e.getMessage());
        }
    }

}

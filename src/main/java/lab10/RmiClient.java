package lab10;

import java.rmi.Naming;
import java.util.Scanner;

public class RmiClient {
    public static void main(String[] args) {
        try {
            // 1. Lookup the remote object from the registry
            OperationInterface calc = (OperationInterface) Naming.lookup("rmi://localhost/CalculatorService");

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter first number: ");
            int num1 = sc.nextInt();
            System.out.print("Enter second number: ");
            int num2 = sc.nextInt();

            // 2. Invoke remote methods
            System.out.println("Sum: " + calc.add(num1, num2));
            System.out.println("Difference: " + calc.subtract(num1, num2));
            System.out.println("Product: " + calc.multiply(num1, num2));

        } catch (Exception e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
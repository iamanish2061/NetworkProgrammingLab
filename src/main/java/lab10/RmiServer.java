package lab10;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RmiServer {
    public static void main(String[] args) {
        try {
            // 1. Create the RMI registry on port 1099
            LocateRegistry.createRegistry(1099);

            // 2. Create the remote object
            OperationInterface stub = new OperationInterfaceImpl();

            // 3. Bind the object to a name in the registry
            Naming.rebind("rmi://localhost/CalculatorService", stub);

            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
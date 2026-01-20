package lab10;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OperationInterfaceImpl extends UnicastRemoteObject implements OperationInterface {

    public OperationInterfaceImpl() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int subtract(int a, int b) {
        return a-b;
    }

    @Override
    public int multiply(int a, int b) {
        return a*b;
    }
}

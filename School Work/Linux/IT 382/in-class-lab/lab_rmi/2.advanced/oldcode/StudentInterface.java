//package example.hello;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentInterface extends Remote {
    int getID() throws RemoteException;
    void setID(int number) throws RemoteException;
}


import java.rmi.*;
import java.util.*;
import java.io.*;

public interface RegistryInterface extends Remote{
	public File[] getDirectoryList() throws RemoteException;
	public void uploadFile(String filename, byte[] contents) throws RemoteException;
	public byte[] downloadFile(String filename) throws RemoteException;
}

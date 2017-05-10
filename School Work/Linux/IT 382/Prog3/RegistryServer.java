import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;


public class RegistryServer implements RegistryInterface
{
	private RegistryServer()
	{
	}

	@Override
	public File[] getDirectoryList() throws RemoteException
	{
		File contents = new File("./server-contents/");
		File[] directory = contents.listFiles();
		return directory;
	}

	@Override
	public void uploadFile(String filename, byte[] contents) throws RemoteException
	{
		try
		{
			File newFile = new File("./server-contents/"+filename);
			FileOutputStream fos = new FileOutputStream(newFile,true);

			if(!newFile.exists())
			{
				newFile.createNewFile();
			}
			else
			{
				//return -2;
			}
			if(contents != null)
			{
				fos.write(contents);
			}
			else
			{
				//return -3;
			}
			fos.close();
		}
		catch(IOException e)
		{
			System.out.println(e);
		}

	}

	@Override
	public byte[] downloadFile(String filename) throws RemoteException
	{
		Path path = Paths.get("./server-contents/"+filename);
		byte[] buff = null;
		try
		{
			buff = Files.readAllBytes(path);
		}
		catch(Exception e)
		{	
			System.out.println(e);
		}
		return buff;
	}


	public static void main(String[] args)
	{
		try
		{
			RegistryServer rs = new RegistryServer();
			//create client stub
			RegistryInterface stub = (RegistryInterface) UnicastRemoteObject.exportObject(rs,0);
			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.createRegistry(7007);
			registry.bind("RegistryInterface",stub);
			System.out.println("Server now running...");
		}
		catch(Exception e)
		{
			System.out.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}

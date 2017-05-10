import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class RegistryClient
{
	public static void main(String args[])
	{
		//String host = (args.length < 1) ? null : args[0];
		try
		{
			Registry registry = LocateRegistry.getRegistry("localhost",7007);
			RegistryInterface stub = (RegistryInterface) registry.lookup("RegistryInterface");
			System.out.println("Successfully connected to server!");

			Scanner scan = new Scanner(System.in);
			System.out.println("\nSelect an option to continue:");
			System.out.println("1. Get Directory List");
			System.out.println("2. Download File from Directory");
			System.out.println("3. Upload File to Directory");
			System.out.println("4. Quit");

			int input = scan.nextInt();
			while(true)
			{
				switch(input)
				{
					case 1:
					{
						getDirectory(stub);
						break;
					}
					case 2:
					{
						downloadFile(stub);
						break;
					}
					case 3:
					{
						uploadFile(stub);
						break;
					}
					case 4:
					{
						scan.close();
						System.exit(0);
					}
					
					default:
					{
						System.out.println("Enter an integer from 1-4");
						break;
					}
				}

				System.out.println("\n\nSelect an option to continue:");
				System.out.println("1. Get Server Directory List");
				System.out.println("2. Download File from Directory");
				System.out.println("3. Upload File to Directory");
				System.out.println("4. Quit");
				input = scan.nextInt();
			}
		}
		catch(Exception e)
		{
			System.err.println("Client exception: " + e.toString());
           		e.printStackTrace();
		} 
	}

	public static void getDirectory(RegistryInterface stub) throws RemoteException
	{
		File[] contents = stub.getDirectoryList();
		if(contents == null)
		{
			System.out.println("DIRECTORY IS EMPTY");
		}
		else
		{
			System.out.println("directory size: " + contents.length);
			for(int i = 0; i < contents.length; i++)
			{
				System.out.println(contents[i].getName());
			}
		}

	}
	public static void uploadFile(RegistryInterface stub) throws RemoteException
	{
		System.out.println("Enter file you wish to upload");
		Scanner scan = new Scanner(System.in);
		String target = scan.nextLine();
		Path filepath = Paths.get("./client-contents/"+target);
		byte[] buff = null;
	
		try
		{
			buff = Files.readAllBytes(filepath);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		try
		{
			stub.uploadFile(target,buff);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println(target + " sucessfully downloaded to /server-contents/");
	}
	public static void downloadFile(RegistryInterface stub) throws RemoteException
	{
		System.out.println("Enter the file you wish to download");
		Scanner scan = new Scanner(System.in);
		String target = scan.nextLine();
		byte[] buff = stub.downloadFile(target);
		File file = new File("./client-contents/"+target);
		try
		{
			FileOutputStream fos = new FileOutputStream(file,true);
			if(!file.exists())
			{
				file.createNewFile();
			}
			else
			{
				//System.out.println("file does not exist!");
			}
			if(buff != null)
			{
				fos.write(buff);
			}
			else
			{
				System.out.println("error downloading file!");
			}
			System.out.println(target + " sucessfully downloaded to /client-contents/");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}		
	}
}

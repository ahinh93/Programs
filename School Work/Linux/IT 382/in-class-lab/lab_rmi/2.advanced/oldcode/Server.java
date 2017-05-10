//Here is the source code for the class Server. Descriptions for writing this server class follow the source code: 
//package example.hello;
        
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.rmi.*;
import java.rmi.server.*;
        
public class Server extends UnicastRemoteObject implements Hello {
        
    public Server() throws RemoteException {}

/*
    public String sayHello() {
        return "Hello, world!";
    }
*/
    public String sayHello(Student std) throws RemoteException {
	std.setID(9999);
	System.out.println("new ID: "+std.getID());
        return "Hello, world!";
    }
        
    public static void main(String args[]) {
        
        try {
	     //System.setSecurityManager(new RMISecurityManger());
	     // set the security manager
            
              Server obj = new Server();

	    Naming.rebind("rmi://localhost/Hello",obj);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}


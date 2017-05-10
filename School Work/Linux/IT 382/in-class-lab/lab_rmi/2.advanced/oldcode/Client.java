//package example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.*;


public class Client {

    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            //Registry registry = LocateRegistry.getRegistry(host);
            //Registry registry = LocateRegistry.getRegistry(host,9000);
            //Hello stub = (Hello) registry.lookup("Hello");
            //String response = stub.sayHello();

	    Hello stub = (Hello) Naming.lookup("Hello");
	    Student std  = new Student("Jim","Brown",10);
            String response = stub.sayHello(std);
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}


//package example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//import java.rmi.*;


public class Client {

    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            //Registry registry = LocateRegistry.getRegistry(host);
             Registry registry = LocateRegistry.getRegistry(host,7020);
            Hello2 stub = (Hello2) registry.lookup("Hello2");
/*
	    Hello2 stub = (Hello2) Naming.lookup("Hello2");
*/
	    Student std  = new Student("Jim","Brown",10);
            System.out.println("before calling remote method: " + std);
            String response = stub.sayHello(std);
            System.out.println("after calling remote method: " + std);
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}


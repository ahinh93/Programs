import java.io.*;
import java.net.*;

public class UDPServer
{
	public static void main(String[] args) throws Exception
	{
		DatagramSocket serverSocket = new DatagramSocket(7007);

		while (true)
		{
			
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			System.out.println("UDPServer: waiting for a request...");
			DatagramPacket receivePacket =
				new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			System.out.println("UDPServer:receivePacket:" + receivePacket );
			String sentence = new String(receivePacket.getData());
			System.out.println("UDPServer:sentence:" + sentence );
			InetAddress IPAddress =
				receivePacket.getAddress();
			int port = receivePacket.getPort();			
			String capitalizedSentence =
				sentence.toUpperCase();
			System.out.println("UDPServer:capitalizedSentence:" 
					+ capitalizedSentence );
			sendData = capitalizedSentence.getBytes();
			DatagramPacket sendPacket =
				new DatagramPacket(sendData,
					sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
			System.out.println("UDPServer:sendPacket:" + sendPacket );
			System.out.println("UDPServer:sendData.length:" 
					+ sendData.length);
			String str = new String(sendData);
			System.out.println("UDPServer:sendData.str:" +str);
		}
	}
			

}


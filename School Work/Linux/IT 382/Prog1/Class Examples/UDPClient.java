import java.io.*;
import java.net.*;

public class UDPClient
{
	public static void main(String[] args) throws Exception
	{
		BufferedReader inFromUser
			= new BufferedReader(new InputStreamReader (System.in));

		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = 
			InetAddress.getByName("oak.ad.ilstu.edu");
			// InetAddress.getByName("maple.ad.ilstu.edu");

		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		String sentence = inFromUser.readLine();
		System.out.println("UDPClient: sentence: " + sentence);
		sendData = sentence.getBytes();
		DatagramPacket sendPacket =
			new DatagramPacket(sendData, sendData.length,
				IPAddress, 7007);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket = 
			new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket); //<---!!!!!
		System.out.println("UDPClient: receivePacket.length:" + 
					receiveData.length);
		System.out.println("UDPClient: receivePacket.getData(): " + 
				receivePacket.getData());
		String modifiedSentence = 
			new String(receivePacket.getData());
		System.out.println("Client: modifiedSentence"+modifiedSentence);
		System.out.println("Client: modifiedSentence.length()"
				+modifiedSentence.length());
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();

	}

}

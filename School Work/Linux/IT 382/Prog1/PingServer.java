import java.io.*;
import java.util.*;
import java.net.*;
public class PingServer
{
	public static final double LOSS_RATE = 0.25;
	public static final int AVERAGE_DELAY = 150;//milliseconds

	public static void main(String argv[]) throws Exception
	{
		String clientSentence ="";
		String capitalizedSentence="";
		String clientIP = "";
		String clientPort = "";
		long seed = -1;
		boolean validProtocol = false;
		int port = 0;
		int delay;
		
		if((argv.length != 3) && (argv.length != 2))
		{
			System.out.println("ERROR: must have 2-3  parameters. port protocol seed(optional)");
			System.exit(0);
		}
		argv[1]=argv[1].toUpperCase();
		try
		{
			port = Integer.parseInt(argv[0]);
		}
		catch(NumberFormatException e)
		{
			System.out.println("ERR - Arg 1");
			System.exit(0);
		}
		if(argv[1].equals("TCP")||argv[1].equals("UDP"))
		{
			validProtocol = true;
		}
		if(!validProtocol)
		{
			System.out.println("ERR - ARG 2");
			System.exit(0);
		}
		if(argv.length == 3)
		{
			try
			{
				seed = Long.parseLong(argv[2]);
			}
			catch(NumberFormatException e)
			{
				System.out.println("ERR - Arg 2");
				System.exit(0);
			}
		}
		//create RNG for simulating packet loss & network delay
		Random random;
		if(seed == -1){
			random = new Random();
		}
		else{
			random = new Random(seed);	
		}
		if(argv[1].equals("TCP"))
		{
			ServerSocket welcomeSocket = new ServerSocket(port);
			
			while(true){
				Socket connectionSocket = welcomeSocket.accept();
				clientIP = connectionSocket.getInetAddress().toString();
				clientPort = connectionSocket.getPort() + "";
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				clientSentence = inFromClient.readLine();
				//capitalizedSentence = clientSentence.toUpperCase() + '\n';
				System.out.print(clientIP +":"+clientPort+"> " + clientSentence+" ");

				//create artificial network delay
				// Simulate network delay.
				delay = (int) (random.nextDouble() * 2 * AVERAGE_DELAY);
				System.out.print("ACTION: delayed " + delay + " ms\n");
				Thread.sleep(delay);

				outToClient.writeBytes(clientSentence + "\n");
			}

		}
		//udp
		else
		{
			
			DatagramSocket serverSocket = new DatagramSocket(port);
			while(true)
			{
				byte[] receiveData = new byte[1024];
				byte[] sendData = new byte[1024];
				boolean packetLost = false;
				//waiting for request
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				//receive the packet
				serverSocket.receive(receivePacket);
				
				//sentence is the message from the client
				String sentence = new String(receivePacket.getData());

				//System.out.println("sentence from client: "+sentence);
			
				//get addy of client
				InetAddress IPAddress = receivePacket.getAddress();
				//get port of client
				port = receivePacket.getPort();
			
				//build reply to client
				clientIP = receivePacket.getSocketAddress()+"";
				//clientPort = serverSocket.getPort()+" ";
				System.out.print(clientIP+" "+sentence + "> ");
				//convert message to client as byte form
				sendData = sentence.getBytes();
				//create the packet to be sent
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

				//determine if packet should be simulated to be lost
				if(random.nextDouble() < LOSS_RATE)
				{
					System.out.println("packet lost");
					Thread.sleep(3500);
					serverSocket.send(sendPacket);
				}				
				
				else
				{
					// Simulate network delay.
					delay = (int) (random.nextDouble() * 2 * AVERAGE_DELAY);
					System.out.print("ACTION: delayed " + delay + " ms\n");				
					Thread.sleep(delay);
					//send the packet
					serverSocket.send(sendPacket);
					//message to be sent
					//String outMessage = new String(sendData);
		
				}
			}
		}
	}

}

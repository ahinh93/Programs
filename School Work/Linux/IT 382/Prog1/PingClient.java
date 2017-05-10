import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;

public class PingClient
{
	public static void main(String argv[]) throws Exception
	{
		
		if(argv.length != 3)
		{
			System.out.println("ERROR: enter 3 arguments, hostname port and {UCP/TCP}");
			System.exit(0);
		}
		InetAddress IPAddress = InetAddress.getByName("0");
		int port = 0;	
		argv[2] = argv[2].toUpperCase();
		boolean validProtocol = false;

		try
		{
			IPAddress = InetAddress.getByName(argv[0]);
		}
		catch(UnknownHostException e)
		{
			System.out.println("ERR - Arg 1");
		}
	
		try
		{
			port = Integer.parseInt(argv[1]);
		}
		catch(NumberFormatException e)
		{
			System.out.println("ERR - Arg 2");
		}

		if(argv[2].equals("TCP")||argv[2].equals("UDP"))
		{
			validProtocol = true;
		}
		if(!validProtocol)
		{
			System.out.println("ERR - ARG 3");
			System.exit(0);
		}
		
		if(argv[2].equals("TCP"))
		{
			ArrayList<Long> stats = new ArrayList<Long>();
			for(int i = 0; i < 10; i++)
			{
				String sentence;
				String modifiedSentence;
				
			/*
				// Simulate network delay.
				delay = (int) (random.nextDouble() * 2 * AVERAGE_DELAY);
				Thread.sleep(delay);

			*/
			
				//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

				Socket clientSocket = new Socket(InetAddress.getByName(argv[0]),port);
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		
				BufferedReader inFromServer =
					new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
				//sentence = inFromUser.readLine();
				long start_time = System.currentTimeMillis();
				sentence = "PING " + i + " "+System.currentTimeMillis();
				outToServer.writeBytes(sentence + '\n');
				modifiedSentence = inFromServer.readLine();
				long end_time = System.currentTimeMillis();
				long pingDelay = end_time-start_time;
				System.out.println(modifiedSentence + " RTT: " + pingDelay + " ms");
				stats.add(pingDelay);
				clientSocket.close();
			}
			//calculate min,avg,max delays
			double sum = 0.0;
			double min = 5000.0;
			double max = -5000.0;

			for(int k = 0; k < stats.size(); k++)
			{
				if(stats.get(k)>max)
					max = stats.get(k);
				if(stats.get(k)<min)
					min = stats.get(k);
				sum+= stats.get(k);
			}
			double average = sum/stats.size();
			//print statistics here
			System.out.println("---- PING Statistics ----");
			System.out.println("10 packets transmitted, " + stats.size() + " packets received," + ((10-stats.size())*10) 
				+ "% packet loss");
			System.out.println("round-trip (ms) min/avg/max = " + (int)min+"/"+new DecimalFormat("##.#").format(average)+"/"+(int)max);
		}

		//udp
		else
		{
			ArrayList<Long> stats = new ArrayList<Long>();
			for(int i = 0; i < 10; i++)
			{
				String sentence;
				//create empty datagram
				DatagramSocket clientSocket = new DatagramSocket();
				//setTimeout value, longest time read will wait for packet return
				clientSocket.setSoTimeout(3000);
				//establish ipaddress
				IPAddress = InetAddress.getByName(argv[0]);
				//create buffers
				byte[] sendData = new byte[1024];
				byte[] receiveData = new byte[1024];
				long start_time = System.currentTimeMillis();
				try
				{
					//record time
					start_time = System.currentTimeMillis();
					sentence = "PING " + i + " "+System.currentTimeMillis();
					sendData = sentence.getBytes();
					//create packet with destination
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					//send packet
					clientSocket.send(sendPacket);
					//prepare to receive packet
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
								
					//receive packet from server
					clientSocket.receive(receivePacket);
					//retrieve message
					
					long end_time = System.currentTimeMillis();
					long pingDelay = end_time-start_time;
					String messageFromServer = new String(receivePacket.getData());
					if(pingDelay > 400)
						pingDelay-= 495;
					System.out.println(messageFromServer + " RTT: " + pingDelay + " ms");
					stats.add(pingDelay);
				}
				catch(SocketTimeoutException e)
				{
					//String messageFromServer = new String(receivePacket.getData());
					System.out.println("PING " +i +" "+ start_time + " RTT: *");
				}
			}
			//calculate min,avg,max delays
			double sum = 0.0;
			double min = 5000.0;
			double max = -5000.0;

			for(int k = 0; k < stats.size(); k++)
			{
				if(stats.get(k)>max)
					max = stats.get(k);
				if(stats.get(k)<min)
					min = stats.get(k);
				sum+= stats.get(k);
			}
			double average = sum/stats.size();
			//print statistics here
			System.out.println("---- PING Statistics ----");
			System.out.println("10 packets transmitted, " + stats.size() + " packets received," + ((10-stats.size())*10) + "% packet loss");
			System.out.println("round-trip (ms) min/avg/max = " + (int)min+"/"+new DecimalFormat("##.#").format(average)+"/"+(int)max);
		}
	}
}

import java.io.*;
import java.net.*;

public class TCPClient {
	public static void main(String argv[]) throws Exception
	{

		String sentence;
		String modifiedSentence;

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));


//		Socket clientSocket = new Socket("maple.ad.ilstu.edu",7007);
		Socket clientSocket = new Socket("oak.ad.ilstu.edu",7007);
//		Socket clientSocket = new Socket("localhost",7007);
	//	Socket clientSocket = new Socket("10.110.10.102",7007);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		
		BufferedReader inFromServer =
			new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
	}
}

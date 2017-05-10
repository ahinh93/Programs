import java.io.*;
import java.net.*;


public class TCPClient {
	public static void main(String argv[]) throws IOException
	{
		//get port number and ip address of server from command line
		InetAddress IPAddress = InetAddress.getByName(argv[0]);
		int port = Integer.parseInt(argv[1]);
		
		
		//establish tcp connection to server
		Socket socket = new Socket(IPAddress,port);
		
		//get reference to socket's input and output stream
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		
		//setup input stream
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		//setup output stream
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		
		//send message to server
		bw.write("client requesting reply from server\n");
		bw.flush();
		
		// Get message from server.
		String messageFromServer = br.readLine();

		// Display message from server.
		System.out.println(messageFromServer);
		
		// Close the streams.
		bw.close();
		br.close();

		// Close the socket.
		socket.close();
	}
}

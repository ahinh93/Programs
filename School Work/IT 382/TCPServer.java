import java.io.*;
import java.net.*;

public class TCPServer {
	public static void main(String argv[]) throws IOException{
		//get port number and message from command line
		int port = Integer.parseInt(argv[0]);
		String messageToClient = argv[1];
		
		//open a socket
		ServerSocket listenSocket = new ServerSocket(port);
		
		//listen and accept TCP connection requests
		//note this is a blocking call
		Socket connectionSocket = listenSocket.accept();
		
		//create io streams
		InputStream is = connectionSocket.getInputStream();
		OutputStream os = connectionSocket.getOutputStream();
		
		//setup input stream filter
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		//setup output stream filter
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		
		//get and display message from client
		String messageFromClient = br.readLine();//blocks until receives complete line
		System.out.println(messageFromClient);
		
		//send message to client
		bw.write(messageToClient + "\n");
		bw.flush();
		
		//close streams
		bw.close();
		br.close();
		
		//close sockets
		connectionSocket.close();
		listenSocket.close();
	}
}

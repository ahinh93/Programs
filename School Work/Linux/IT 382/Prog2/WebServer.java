import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.*;

/**
 * This portion of the project is to initialize a TCP socket to listen
 * for incoming HTTP Requests. Once received, instantiate an HttpRequest to parse
 * request and write requested resource to the console
 * @author Ahinh
 *
 */
public class WebServer {
	public static void main(String argv[]) throws Exception
	{
		try{
			
			//set the port number - read from command line
			//print error message if used incorrectly, exit server
			int port = Integer.parseInt(argv[0]);
			
			if(port <= 1024 || port > 49151)
			{
				System.out.println("ERROR - Port must be greater than 1024 and less than 49151");
				System.exit(0);
			}
			
			//Establish the listen socket
			ServerSocket serverSocket = new ServerSocket(port);
			
			//process HTTP service requests in an infinite loop
			while(true)
			{
				//listen for TCP connection request
				Socket clientSocket = serverSocket.accept();
				//construct an object to process the HTTP request message
				HttpRequest request = new HttpRequest(clientSocket);
				//process request
				request.processRequest();
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println(argv[0] + " is not a valid port number");
			System.exit(0);
		}
	}
}
final class HttpRequest
{
	final static String CRLF = "\r\n";
	Socket socket;
	public HttpRequest(Socket clientSocket) throws Exception {
		this.socket = clientSocket;
	}
	
	//print relevant request information to console
	public void processRequest() throws Exception {
		//get reference to socket's io streams
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		//setup inputstream filters
		InputStreamReader ISR = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(ISR);
		
		//get the request line of the HTTP request message
		String requestLine = br.readLine();
		//display the request line
		System.out.println();
		System.out.println(requestLine);

		//extract the file from the request line
		StringTokenizer tokens = new StringTokenizer(requestLine);
		tokens.nextToken();//skip method
		String fileName = tokens.nextToken();
		//prepend a "." so that file request is within the current directory
		fileName = "."+ fileName;
		System.out.println("FILE NAME: "+fileName);
		
		
		//open the requested file
		FileInputStream fis = null;
		boolean fileExists = true;
		try{
			fis = new FileInputStream(fileName);
			System.out.println("File input stream opened");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("ERROR---- couldn't open file");
			fileExists = false;
		}

		//construct the response message
		String statusLine = null;
		String contentTypeLine = null;
		String entityBody = null;

		

		if(fileExists)
		{
			statusLine = "HTTP/1.1 200 OK" + CRLF;
			contentTypeLine = "Content-type: " + contentType(fileName) + CRLF;
			//System.out.println("Status line: " + statusLine);
			//System.out.println("CONTENT TYPE: " + contentTypeLine);
			//send the status line
			os.write(statusLine.getBytes());
			//send the content type
			os.write(contentTypeLine.getBytes());
			//send a blank line to indicate end of the header lines
			os.write(CRLF.getBytes());

			//send entire body
			if(fileExists){
				//System.out.println("body written");
				sendBytes(fis,os);
				fis.close();
			}
			else
			{
				//System.out.println("file doens't exist, no body t o write");
				String notFound = "<HTML>" + "<HEAD><TITLE>Not Found</TITLe></HEAD>" + "<BODY>Not Found</BODY></HTML>" + CRLF;
				os.write(notFound.getBytes());
			}
		}
		else
		{
			statusLine = "HTTP/1.1 404 Not Found" + CRLF;
			contentTypeLine = contentType(fileName)+ CRLF;
			//System.out.println("Status line: " + statusLine);
			//System.out.println("Content: " + contentTypeLine);
			entityBody="<HTML>" + "<HEAD><TITLE>Not Found</TITLE></HEAD>" + "<BODY>Not Found</BODY></HTML>"+CRLF;
		}

		if(fileExists)
		{
			sendBytes(fis,os);
			fis.close();
		}
		else{
			String temp = statusLine+contentTypeLine+entityBody;
			os.write(temp.getBytes());
		}
		
/*
		//get and display the header lines
		String headerLine = null;
		while((headerLine = br.readLine()).length() != 0)
		{
			System.out.println(headerLine);
		}
*/
		os.close();
		br.close();
		socket.close();

		
	}
	private static String contentType(String fileName)
	{

		if(fileName.endsWith(".htm") || fileName.endsWith(".html"))
		{
			return "text/html";
		}
		if(fileName.endsWith(".gif"))
		{
			return "image/gif";
		}
		if(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))
		{
			return "image/jpeg";
		}
		if(fileName.endsWith(".png"))
		{
			return "image/png";
		}

		//String host
		//String out = "";
		//FileNameMap fileNameMap = URLConnection.getFileNameMap();
		//System.out.println(fileNameMap.getContentTypeFor(fileName));
		
		return "application/octet-stream";
	}
	private static void sendBytes(FileInputStream fis, OutputStream os) throws Exception
	{
		//construct a 1k buffer to hold bytes on the way to the socket
		byte[] buffer = new byte[1024];
		int bytes = 0;
		//copy requested file into the sockets output stream
		while((bytes = fis.read(buffer))!=-1)
		{
			os.write(buffer,0,bytes);
		}
	}
}

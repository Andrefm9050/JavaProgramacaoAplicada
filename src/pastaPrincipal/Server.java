package pastaPrincipal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import users.Utilizador;

public class Server {
	
	static ServerSocket serverSocket = null;
	static Socket clientSocket       = null;	
	static PrintWriter out           = null;
	static BufferedReader in         = null;
	
	public static void main(String [] args)  {
		Utilizador login = null;

		int portNumber = 7777;
		
		try {
			serverSocket = new ServerSocket(portNumber); //starts server
			
			InetAddress address = InetAddress.getLocalHost(); // retrieves local IP			
			System.out.println("## Server up and running at IP "+address.getHostAddress()+":"+serverSocket.getLocalPort()+" Hostname "+address.getHostName()+" ##"+"\n## Server waiting for connections...");
		
			clientSocket = serverSocket.accept(); // waits until a client request a connection
			out          = new PrintWriter(clientSocket.getOutputStream(), true); 
			in           = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
		} catch (IOException ioe) {
		  	ioe.printStackTrace();
		  	System.err.println("## Server Exiting due to error...");
			System.exit(0);
		} 
		try {
			clientSocket.setSoTimeout(10000); 
			out.println("<server> <hello>;");
			
			String clientReply = in.readLine();
			
			if(clientReply.contentEquals("<cliente> <hello>;")) {
				out.println("<server> <ack>;");
			}
			else {
				out.println("<server> <bye>;");
				fechaConexao();
			}
			
			while(true) {
				clientReply = in.readLine();
				System.out.println(clientReply);
				
				
				
			}
			
			
			
		}
		catch(Exception e) {
			
		}
		finally {
			
		}
	}
	
	public static void fechaConexao() {
		try {
		    if (in != null) 
		    	in.close();
		    if (out != null) 
		    	out.close();
		    if (clientSocket != null) 
		    	clientSocket.close();
		    if (serverSocket != null) 
		    	serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

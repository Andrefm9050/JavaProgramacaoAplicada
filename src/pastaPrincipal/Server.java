package pastaPrincipal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import sistema.BDDriver;
import sistema.ServerDriver;
import users.Utilizador;

public class Server {
	
	static ServerSocket serverSocket = null;
	static Socket clientSocket       = null;	
	static PrintWriter out           = null;
	static BufferedReader in         = null;
	
	public static void main(String [] args)  {

		char choice = 'n';
		System.out.println("Deseja configurar a configuração de base de dados? (s/n)");
		choice = Main.lerDados("").charAt(0);
		if(choice == 's' || choice == 'S')
			BDDriver.menuConfiguracao();
		
		
		while(!BDDriver.configurarDriverPorFicheiro("Properties")) {
			System.out.println("Erro ao connectar á base de dados... a tentar de novo");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		}
		
		int portNumber = 7777;
		while(true) {
		try {
			serverSocket = new ServerSocket(portNumber); //starts server
			
			InetAddress address = InetAddress.getLocalHost(); // retrieves local IP			
			System.out.println("## Server up and running at IP "+address.getHostAddress()+":"+serverSocket.getLocalPort()+" Hostname "+address.getHostName()+" ##"+"\n## Server waiting for connections...");
		
			clientSocket = serverSocket.accept(); // waits until a client request a connection
			clientSocket.setSoTimeout(20000);
			out          = new PrintWriter(clientSocket.getOutputStream(), true); 
			in           = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
		} catch (IOException ioe) {
		  	ioe.printStackTrace();
		  	System.err.println("## Server Exiting due to error...");
			System.exit(0);
		} 
		try {
			System.err.println("## Client Connected...");
			out.println("<server> <hello>;");
			
			String clientReply = in.readLine();
			
			if(clientReply.contentEquals("<cliente> <hello>;")) {
				out.println("<server> <ack>;");
			}
			else {
				out.println("<server> <bye>;");
				fechaConexao();
			}
			ServerDriver driver = new ServerDriver(out);
			while(true) {
				System.out.println(" ");
				System.out.println("Waiting for client message... ");
				System.out.println(" ");
				
				clientReply = in.readLine();
				System.out.println(clientReply);
				
				if(driver.executeComand(clientReply)) {
					System.out.println("Success");
				}
				else {
					System.out.println("Fail");
				}
				
			}
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			fechaConexao();
		}
		System.out.println("Conexao fechada.");
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

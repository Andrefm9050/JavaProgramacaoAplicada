package pastaPrincipal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import sistema.BDDriver;
import sistema.ServerDriver;
import users.Utilizador;

public class Server {
	
	static ServerSocket serverSocket = null;
	
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
		boolean portSelected = false;
		while(!portSelected) {
			int portNumber = Main.lerDadosInt("Porto:");
			try {
				serverSocket = new ServerSocket(portNumber);
				portSelected = true;
			} catch (IOException e) {
				//e.printStackTrace();
				portSelected = false;
				System.out.println("Erro ao iniciar servidor... o porto provavelmente está em uso.");
			} 	
		}
		
		try {
			InetAddress l = Inet4Address.getLocalHost(); 
			System.out.println("## Server up and running at IP "+l.getHostAddress()+":"+serverSocket.getLocalPort()+" Hostname "+InetAddress.getLocalHost().getHostName()+" ##"+"\n## Server waiting for connections...");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	
		int clientID = 0;
		while(true) {
			
		try {
			
			ServerDriver driver = new ServerDriver(serverSocket.accept(),++clientID);
			driver.start();
			
		} catch (IOException ioe) {
		  	ioe.printStackTrace();
		  	System.err.println("## Server Exiting due to error...");
			System.exit(0);
		} 
		}
	}
	
	
}

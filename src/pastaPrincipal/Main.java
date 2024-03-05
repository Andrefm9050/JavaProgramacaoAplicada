package pastaPrincipal;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;


//import ProjetoProgramacao.Gestor;
import users.EstadoConta;
import users.Utilizador;

public class Main {
	
	public static void main(String [] args)  {
		
		
		while(true) {
			System.out.println("1-Registar \n2-Login \n3-Sair");
			
			int opcao = lerDadosInt("Escolha uma das seguintes opções: ");
			
			executaOpcao(opcao);
			
		}
			
				
		}
	
	
	private static void executaOpcao(int aOpcao){
		switch(aOpcao) {
		case 1: registo(); break;
		case 2: login(); break;
		case 3: sair(); break;
		default: erro();
		}
	}
	
	
	private static void erro() {
		System.out.println("Opção inválida!");
	}
	
	private static void sair() {
		//System.out.println("Adeus " + userLoginName);
		System.out.println("A encerrar aplicação!"); 
		System.exit(0);								
	}
	
	private static void registo() {
		
		Connection conn = null;
		
		try {
			
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://aid.estgoh.ipc.pt:5432/db2021159661", "a2021159661", "a2021159661");
			
			Statement st = conn.createStatement();
			//st.executeQuery("INSERT INTO Teste (texto) VALORES ('teste')");
			System.out.println("Connection OK");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		String login1 = lerDados("Insira o seu username: ");
		
		String password1 = lerDados("Insira a sua password: ");
		
		String nome1 = lerDados("Insira o seu nome: ");
		
		String email1 = lerDados("Insira o seu email(axzc@exmail.com): ");
		
		String tipo1 = lerDados("Insira o tipo de conta(Gestor, autor ou revisor): ");
		
		Utilizador u1 = new Utilizador(login1, password1, nome1, null, email1, tipo1);
		
		if(tipo1.equalsIgnoreCase("Gestor")) {
			
		}
		
	}
	
	private static void login() {
		
	}
	
	
	
	
	private static int lerDadosInt(String aMensagem){
		System.out.println(aMensagem);
		return(new Scanner(System.in)).nextInt();
		
	}
	private static String lerDados(String aMensagem){
		System.out.println(aMensagem);
		return(new Scanner(System.in)).nextLine();
	}
	
}

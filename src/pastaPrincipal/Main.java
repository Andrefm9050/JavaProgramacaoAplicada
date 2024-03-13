package pastaPrincipal;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

import sistema.BDDriver;
import users.Autor;
//import ProjetoProgramacao.Gestor;
import users.EstadoConta;
import users.Gestor;
import users.Revisor;
import users.Utilizador;

public class Main {
	
	//Eu nao queria estar a meter isto aqui
	//Mas nao funciona de outra forma já que o programa termina fora da função main
	static long startmillis;
	static long endmillis;
	
	public static void main(String [] args)  {
		startmillis = System.currentTimeMillis();
		
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
		endmillis = System.currentTimeMillis();
		
		PrintTimeStats(startmillis,endmillis);
		System.exit(0);								
	}
	
	private static void PrintTimeStats(long start, long end) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE; yyyy-MM-dd HH:mm:ss",Locale.forLanguageTag("Pt"));
		
		System.out.println("Início do processo: " + dateFormat.format(new Date(start)));
		System.out.println("Fim do processo: " + dateFormat.format(new Date(end)));
		long diff = (end - start);
		int segundos = (int)(diff/1000) % 60; //Só existem 60 segundos num minuto
		int minutos = (int)((diff/(1000*60)) % 60); //Só existem 60 minutos numa hora
		int horas = (int)((diff/(1000*60*60)) % 24); //Só existem 24 horas diferentes
		
		System.out.println("Tempo de execução: " + diff + " Milissegundos "+
		"("+segundos+ " Segundos; "+minutos+" Minutos; "+horas+ " Horas)");
	}
	
	private static void registo() {
		
		
		
		String login1 = lerDados("Insira o seu username: ");
		
		String password1 = lerDados("Insira a sua password: ");
		
		String nome1 = lerDados("Insira o seu nome: ");
		
		String email1 = lerDados("Insira o seu email(axzc@exmail.com): ");
		
		String tipo1 = lerDados("Insira o tipo de conta(Gestor, autor ou revisor): ");
		
		Utilizador u1 = new Utilizador(login1, password1, nome1, null, email1, tipo1);
		
		
		BDDriver.adicionarUtilizador(u1);
		
		
	}
	
	private static void login() {
		String login1 = lerDados("Insira o seu username: ");
		String password1 = lerDados("Insira a sua password: ");
		
		
		if(BDDriver.listarUtilizadores2(BDDriver.listarUtilizador(login1, password1), "gestores")!=null) {
			System.out.println("Bem-vindo " + login1);
			Gestor.menuGestor();
			
			
		}
		else if(BDDriver.listarUtilizadores2(BDDriver.listarUtilizador(login1, password1), "autores")!=null) {
			System.out.println("Bem-vindo " + login1);
			Autor.menuAutor();
			
		}
		else if(BDDriver.listarUtilizadores2(BDDriver.listarUtilizador(login1, password1), "revisores")!=null) {
			System.out.println("Bem-vindo " + login1);
			Revisor.menuRevisor();
		} else {
			System.out.println("Credenciais inválidas!");
		}
		//BDDriver.listarUtilizadores2(BDDriver.listarUtilizador(login1, password1), "gestores");
		//BDDriver.listarUtilizador(login1, password1);
		
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

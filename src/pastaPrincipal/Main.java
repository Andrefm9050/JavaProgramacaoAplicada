package pastaPrincipal;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import gestao.GestorLogs;

import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

import sistema.BDDriver;
import sistema.GestorContas;
import users.Autor;
//import ProjetoProgramacao.Gestor;
import users.EstadoConta;
import users.Gestor;
import users.Revisor;
import users.Utilizador;

public class Main {
	
	//
	//Eu nao queria estar a meter isto aqui
	//Mas nao funciona de outra forma já que o programa termina fora da função main
	static long startmillis;
	static long endmillis;
	static GestorLogs gestorLogs = new GestorLogs();

	
	public static void main(String [] args)  {
		startmillis = System.currentTimeMillis();
		char choice = 'n';
		System.out.println("Deseja configurar a configuração de base de dados? (s/n)");
		choice = lerDados("").charAt(0);
		if(choice == 's' || choice == 'S')
			BDDriver.menuConfiguracao();
		
		
		while(!BDDriver.configurarDriverPorFicheiro("Properties")) {
			System.out.println("Erro ao connectar á base de dados... a tentar de novo");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		}
		
		
		while(GestorContas.listarGestores().length == 0) {
			System.out.println("Nao existe nenhuma conta de administrador, por favor insira uma nova");
			registo("Gestor",true);
			System.out.println("Obrigado! Agora pode se registar com uma conta diferente ou fazer login com a mesma conta");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}

		while(true) {
			System.out.println("1-Registar \n2-Login \n3-Sair");
			
			int opcao = lerDadosInt("Escolha uma das seguintes opções: ");
			
			executaOpcao(opcao);
			
		}		
	}
	
	
	private static void executaOpcao(int aOpcao){
		switch(aOpcao) {
		case 1: registo(lerDados("Insira o tipo de conta(Gestor, autor ou revisor): "),false); break;
		case 2: login(); break;
		case 3: sair(); break;
		case 4: teste(); break;
		default: erro();
		}
	}
	public static void teste() {
		//Gestor.menuGestor(null);
		//System.out.println(BDDriver.listarUtilizadores().length);
		Utilizador[] utilizadorBuffer = BDDriver.listarUtilizadores();
 		//utilizadorBuffer = BDDriver.listarUtilizadores();
 		   
 	    for(int i=0; i<utilizadorBuffer.length; i++) {
 	    	System.out.println(utilizadorBuffer[i].getIdUser());
 	    	
 	    }
	}
	
	
	private static void erro() {
		System.out.println("Opção inválida!");
	}
	
	private static void sair() {
		
		System.out.println("A encerrar aplicação!"); 
		endmillis = System.currentTimeMillis();
		
		PrintTimeStats(startmillis,endmillis);
		BDDriver.fecharConexao();
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
	
	private static void registo(String tipo1,boolean primeiraconta) {
		

		String login2;
		
		while(true) {
			String login1 = lerDados("Insira o seu username: ");
			Utilizador utiTeste = GestorContas.pesquisarUtilizadoresUserName(login1);
			if(utiTeste != null) {
				System.out.println("Username já existe! Insira outro username.");		//verifica se o username é único
			}else {
				login2 = login1;
				break;
			}
		}
		
		String password1 = lerDados("Insira a sua password: ");
		String nome1 = lerDados("Insira o seu nome: ");
		
		String email2;
		while(true) {
		String email1 = lerDados("Insira o seu email(axzc@exmail.com): ");
		if(GestorContas.validacaoEmail(email1)==false) {
			System.out.println("Email com formato inválido! Insira no seguinte formato [designação]@[entidade].[dominio]");  //verifica se o email é unico e se está no formato pretendido
			
		}
		//else {continue;}
		if(GestorContas.pesquisarUtilizadoresEmail(email1)!=null) {
			System.out.println("O Email inserido já existe! Insira outro email.");
		}else {
			email2 = email1;
			break;
		}
		}
		
		
		
		Utilizador u1 = new Utilizador(0,login2, password1, nome1, primeiraconta ? EstadoConta.ativos : EstadoConta.por_registar, email2, tipo1); //<- Aqui nao ha problema o id=0 pois estamos a inserir
		BDDriver.adicionarUtilizador(u1);
		
	}
	
	private static void login() {
		String login1 = lerDados("Insira o seu username: ");
		String password1 = lerDados("Insira a sua password: ");
		
		Utilizador userLoginSEstado = BDDriver.encontrarUtilizador(login1, password1);
		//BDDriver.listarUtilizadores()[1].ge
		
		if(userLoginSEstado != null && userLoginSEstado.getEstado() == EstadoConta.ativos) {
				
			
			if(userLoginSEstado instanceof Gestor) {
				System.out.println("Bem-vindo " + login1);
				Gestor.menuGestor(login1);
			}
			else if(userLoginSEstado instanceof Autor) {
				System.out.println("Bem-vindo " + login1);
				Autor.menuAutor(login1);
			}
			else if(userLoginSEstado instanceof Revisor) {
				System.out.println("Bem-vindo " + login1);
				Revisor.menuRevisor(login1);
			}
			else {
				System.out.println("Credenciais inválidas!");
			}
		} else if(userLoginSEstado != null && userLoginSEstado.getEstado() == EstadoConta.rejeitado){
			System.out.println("O seu pedido de registo foi Rejeitado!");
			
		} else {
			System.out.println("Credenciais inválidas!");
		}
		
		//BDDriver.listarUtilizadores2(BDDriver.listarUtilizador(login1, password1), "gestores");
		//BDDriver.listarUtilizador(login1, password1);
		
	}
	
	
	
	
	public static int lerDadosInt(String aMensagem){
		System.out.print(aMensagem);
		return(new Scanner(System.in)).nextInt();
		
	}
	public static String lerDados(String aMensagem){
		System.out.print(aMensagem);
		return(new Scanner(System.in)).nextLine();
	}
	
}

package pastaPrincipal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;

import gestao.GestorLogs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import sistema.BDDriver;
import sistema.Obra;
import users.Autor;
//import ProjetoProgramacao.Gestor;
import users.EstadoConta;
import users.Gestor;
import users.GestorContas;
import users.Revisor;
import users.Utilizador;

public class Main {
	
	//
	//Eu nao queria estar a meter isto aqui
	//Mas nao funciona de outra forma já que o programa termina fora da função main
	static long startmillis;
	static long endmillis;

	
	public static void main(String [] args)  {
		startmillis = System.currentTimeMillis();
		
		
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
		case 1: 
			String tipoConta = "";
			
			while(!tipoConta.contentEquals("autor") && !tipoConta.contentEquals("revisor")) {
				tipoConta = lerDados("Insira o tipo de conta(autor ou revisor): ");
			}
			registo(tipoConta,false); 
			
			break;
		case 2: login(); break;
		case 3: sair(); break;
		case 4: teste(); break;
		default: erro();
		}
	}
	public static void teste() {
		Main.SelectionarObjetoMenu(BDDriver.listarRevisoes());
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
			continue; //Este email está com o formato inválido... nao podemos avançar!
		}
		if(GestorContas.pesquisarUtilizadoresEmail(email1)!=null) {
			System.out.println("O Email inserido já existe! Insira outro email.");
			continue; //Este email já existe... nao podemos avançar!
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
		
		if(userLoginSEstado != null && (userLoginSEstado.getEstado().equals(EstadoConta.ativos) || userLoginSEstado.getEstado().equals(EstadoConta.por_remover))) {
			GestorLogs.adicionarLog(userLoginSEstado, userLoginSEstado.getNome() + " fez Login!");	

			
			if(userLoginSEstado instanceof Gestor) {
				System.out.println("Bem-vindo " + login1);
				Gestor.menuGestor((Gestor)userLoginSEstado);
			}
			else if(userLoginSEstado instanceof Autor) {
				System.out.println("Bem-vindo " + login1);
				Autor.menuAutor((Autor)userLoginSEstado);
			}
			else if(userLoginSEstado instanceof Revisor) {
				System.out.println("Bem-vindo " + login1);
				Revisor.menuRevisor((Revisor)userLoginSEstado);
			}
			else {
				System.out.println("Credenciais inválidas!");
			}
		} else if(userLoginSEstado != null && userLoginSEstado.getEstado().equals(EstadoConta.rejeitado) ){
			System.out.println("O seu pedido de registo foi Rejeitado!");
			
		} else {
			System.out.println("Credenciais inválidas!");
		}
		
		//BDDriver.listarUtilizadores2(BDDriver.listarUtilizador(login1, password1), "gestores");
		//BDDriver.listarUtilizador(login1, password1);
		
	}
	
	
	//https://stackoverflow.com/questions/68532023/java-can-a-function-return-the-same-type-as-one-of-the-input-arguments
	/**
	 * 
	 * @param objlist - lista de variável de qualquer tipo genérico que tenha hierarquia de Comparable
	 * @return Uma variável do mesmo tipo dado como argumento, null se cancelar a operação
	 */
	public static <T extends Comparable<T>> T SelectionarObjetoMenu(T[] objlist) {
		
		String termoPesquisa = "";
		String ord = "Ascendente";
		String escolha = "c";
		T choice = null;
		int indexdiff = 0;
		List<T> fromlist = new ArrayList<T>();
		for(var obj : objlist)
			fromlist.add(obj);
		
		while(!escolha.contentEquals("s") || choice != null) {
			
			if(ord.equals("Ascendente")) {
			Collections.sort(fromlist);
			}
			else {
				Collections.sort(fromlist,Collections.reverseOrder());
			}
			if(termoPesquisa != null && !termoPesquisa.equals(""))
			System.out.println("Termo de pesquisa atual: " + termoPesquisa);
			
			System.out.println("Sair (s); Mudar termo Pesquisa (p); A seguir(n); Anterior (r); Mudar ordenação (o): " + ord + ";");
			int starterindexdiff = indexdiff; //Ultimo index da listagem atual a partir do fromlist
			boolean finished = true;
			List<T> list = new ArrayList<T>();
			int elementsfound = 0;
			for(int p = 0;elementsfound < 10; p++) {
				try {
				if(fromlist.get(p + indexdiff).toString().contains(termoPesquisa)) {
						list.add(fromlist.get(p + indexdiff));
						elementsfound++;
						if(p+indexdiff + 1 >= fromlist.size()) {
							finished = false; //Mesmo que possamos preencher a list com 10 elementos, podemos nem ter elementos a mais depois desta
						}
					}
				}
				catch(Exception e){
					finished = false; //A lista nao foi preenchida com os 10 elementos
					break;
				}
			}
			
			
			
			for(int i = 0; i< list.size(); i++) {
				if(i == 0 || indexdiff % 10 != 0) { //If statement antigo, isto é redundante mas não quero fazer mais uma fase de testes
					System.out.println(i + ": " + list.get(i).toString());
					indexdiff++;
				}
			}
			
				
				escolha = lerDados(":");
				
				if(isInt(escolha) && !escolha.equals("")) { //Escolha pode ser um objeto ou uma escolha extra
					int index = Integer.parseInt(escolha);
					try {
						choice = list.get(index);
						return choice;
					}
					catch(Exception e) {
						choice = null;
						System.out.println("Escolha inválida");
						indexdiff = starterindexdiff; //A listagem vai ser feita denovo, então definimos o indice para o seu começo de novo
					}
				}
				else {
					switch(escolha) {
					
					case "n":
						if(!finished) {
							indexdiff = starterindexdiff; //O index não pode avançar mais
						}
						continue;
					case "r":
						indexdiff = indexdiff - 10;
						while(indexdiff % 10 != 0) {
							indexdiff--;
						}
						if(indexdiff <= 0) {
							indexdiff = 0;
						}
						continue;
					case "s":
						return null;
					case "p":
						termoPesquisa = lerDados("Pesquisa:");
						indexdiff = 0;
						continue;
					case "o":
						if(ord.equals("Ascendente"))
							ord = "Descendente";
						else
							ord = "Ascendente";
						break;
					}

				}
				indexdiff = starterindexdiff; //A listagem vai ser feita denovo, então definimos o indice para o seu começo de novo
		}
		return choice;
	}
	
	static boolean isInt(String mensagem) {
		try {
			Integer.parseInt(mensagem);
			return true;
		}
		catch (Exception e){
			return false;
		}
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

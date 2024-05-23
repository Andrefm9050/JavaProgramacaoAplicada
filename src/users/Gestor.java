package users;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import pastaPrincipal.Cliente;
import pastaPrincipal.Main;
import sistema.BDDriver;
import sistema.EstadoRevisao;
import sistema.Obra;
import sistema.Revisao;

public class Gestor extends Utilizador{
	
	private int idGestor;

	public Gestor(int idGestor,int idUser,String login, String password, String nome, EstadoConta estado, String email, String tipo) {
		super(idUser,login, password, nome, estado, email, tipo);
		this.idGestor = idGestor;
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int getGestorID() {
		return idGestor;
	}
	public static void menuGestor(Gestor user) {
		
		while(true) {
		System.out.println("1-Aprovar/Rejeitar Pedidos de Registo \n2-Criar conta Gestor \n3-Ativar/Inativar Conta \n4-Pedido Remover Conta "
				+ "\n5-Pedidos Remoção Contas Conta \n6-Pedidos Revisao");
		System.out.println("7-Listar Utilizadores");
		System.out.println("8-Listar Pedidos Revisao");
		System.out.println("9-Listar Pedidos Revisao nao finalizados por data");
		System.out.println("10-Listar Pedidos Revisao de Obra");
		System.out.println("11-Consultar dados pessoais");
		System.out.println("12-Alterar dados pessoais");
		System.out.println("13-Sair");
		int opcao = lerDadosInt("Escolha uma das seguintes opções: ");
		
		executaOpcao(opcao, user);
		
	}

	
	
}
	
	public static void executaOpcao(int aOpcao, Gestor user){
		
		
		switch(aOpcao) {
		case 1: aprovarRejeitarPedidosRegisto(); break;
		case 2: criarGestor(); break;
		case 3: ativarInativarConta(); break;
		case 4: GestorContas.pedidoRemoverConta(user.getLogin()); break;
		case 5: pedidosRemocaoConta(); break;
		case 6: pedidosRevisao(user); break;
		case 7: listarUtilizadores(); break;
		case 8: listarPedidosRevisao(); break;
		case 9: listarPedidosNaoFinalizados(); break;
		case 10: listarPedidosDeObra(); break;
		case 11: Cliente.consultaDadosPessoaisCliente(); break;
		case 12: Cliente.alterarDadosPessoais(user); break;
		case 13: sair(user.getLogin()); break;
		default: erro();
		}
	}
	static void listarPedidosDeObra() {
		Obra obra = Main.SelectionarObjetoMenu(BDDriver.listarObras());
		
		if(obra == null) return;
		
		Revisao[] list = BDDriver.listarRevisoes();
		ArrayList<Revisao> actualList = new ArrayList<Revisao>();
		for(var rev : list) {
			if(rev.getObraID() == obra.getObraId()) {
				actualList.add(rev);
			}
		}
		Main.SelectionarObjetoMenu(actualList.toArray(new Revisao[0]));
		
		
	}
	static void listarPedidosNaoFinalizados() {
		Revisao[] list = BDDriver.listarRevisoes();
		ArrayList<Revisao> actualList = new ArrayList<Revisao>();
		for(var rev : list) {
			if(rev.getEstado() != EstadoRevisao.finalizada) {
				rev.setOrdenacao("d");
				actualList.add(rev);
			}
		}
		Main.SelectionarObjetoMenu(actualList.toArray(new Revisao[0]));
	}
	static void listarPedidosRevisao() {
		String choice = "";
		while(!(choice.contentEquals("d") || choice.contentEquals("t") || choice.contentEquals("a"))) {
			choice = Main.lerDados("Ordenar listagem por Data de criacao (d), Titulo de Obra (t) ou por Autor(a):");
		}
		Revisao[] list = BDDriver.listarRevisoes();
		for(var rev : list) {
			rev.setOrdenacao(choice.charAt(0) + "");
		}
		Main.SelectionarObjetoMenu(list);
	}
	
	static void listarUtilizadores() {
		//Listar utilizadores por nome
		
		Utilizador[] list = GestorContas.listarUtilizadores();
		for(var user : list) {
			user.setOrdenacao("nome");
		}
		Main.SelectionarObjetoMenu(list);
	}
	
	
	
	private static void aprovarRejeitarPedidosRegisto() {
		int tamanhoArray;
 		
 		tamanhoArray = BDDriver.listarUtilizadores().length;
 		Utilizador[] utilizadorBuffer = new Utilizador[tamanhoArray];
 		utilizadorBuffer = BDDriver.listarUtilizadores();
 		
 		for(int i=0; i<tamanhoArray; i++) {
 	    	if(utilizadorBuffer[i].getEstado() == EstadoConta.por_registar) {
 	    		System.out.println("ID:" + utilizadorBuffer[i].getIdUser() + " Username:" + utilizadorBuffer[i].getLogin() 
 	    				+ " Nome:" + utilizadorBuffer[i].getNome() + " Tipo Conta:" + utilizadorBuffer[i].getTipo());
 	    	}	
 		}
 		int aprovaRejeitaN;
 		int idEscolha = lerDadosInt("Insira o ID do utilizador que pretende aprovar/rejeitar:  ");
 		while(true) {
 			String aprovaRejeita = lerDados("Pretende aprovar ou rejeitar o pedido de registo(s/n): ");
 			if(aprovaRejeita.contentEquals("s")) {
 				aprovaRejeitaN = 4;
 				break;
 			}else if(aprovaRejeita.contentEquals("n")) {
 				aprovaRejeitaN = 1;
 				break;
 			}else {
 				System.out.println("Resposta inválida! Insira s ou n como resposta. (s-sim, n-não)");
 			}
 		}
 		
 		BDDriver.setUtilizadorEstado(idEscolha, aprovaRejeitaN);
 		System.out.println("Tarefa terminada com sucesso.");
	}
	
	
	private static void criarGestor() {
		System.out.println("Processo de criação de Conta tipo Gestor");
		
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
		Utilizador u1 = new Utilizador(0,login2, password1, nome1, EstadoConta.por_registar, email2, "Gestor");
		
		
		BDDriver.adicionarUtilizador(u1);
		
	}
	
	private static void ativarInativarConta() {
		int tamanhoArray;
 		
 		tamanhoArray = BDDriver.listarUtilizadores().length;
 		Utilizador[] utilizadorBuffer = new Utilizador[tamanhoArray];
 		utilizadorBuffer = BDDriver.listarUtilizadores();
 		
 		for(int i=0; i<tamanhoArray; i++) {
 	    	System.out.println("ID:" + utilizadorBuffer[i].getIdUser() + " Username:" + utilizadorBuffer[i].getLogin() 
 	    			+ " Nome:" + utilizadorBuffer[i].getNome() + " Tipo Conta:" + utilizadorBuffer[i].getTipo());	
 		}
 		
 		int ativarInativarN;
 		int idEscolha = lerDadosInt("Insira o ID do utilizador que pretende ativar/Inativar:  ");
 		while(true) {
 			String ativarInativar = lerDados("Pretende ativar(a) ou inativar(i) a conta(a/i): ");
 			if(ativarInativar .contentEquals("a")) {
 				ativarInativarN = 4;
 				break;
 			}else if(ativarInativar.contentEquals("i")) {
 				ativarInativarN = 5;
 				break;
 			}else {
 				System.out.println("Resposta inválida! Insira a ou i como resposta. (a-ativo, i-inativo)");
 			}
 		}
 		
 		BDDriver.setUtilizadorEstado(idEscolha, ativarInativarN);
	}
	
	
	private static void pedidosRemocaoConta() {
		int tamanhoArray;
 		
 		tamanhoArray = BDDriver.listarUtilizadores().length;
 		Utilizador[] utilizadorBuffer = new Utilizador[tamanhoArray];
 		utilizadorBuffer = BDDriver.listarUtilizadores();
 		ArrayList<Utilizador> utilizadorNovo = new ArrayList<Utilizador>();
 		
 		for(int i=0; i<tamanhoArray; i++) {
 			if(utilizadorBuffer[i].getEstado() == EstadoConta.por_remover) {
 				utilizadorNovo.add(utilizadorBuffer[i]);
 			}
 	    	
 		}
 		//utilizadorNovo.toArray(new Utilizador[0])
 		Utilizador utiliRemove = Main.SelectionarObjetoMenu(utilizadorNovo.toArray(new Utilizador[0]));
 		
 		while(true) {
 			String verify = lerDados("Tem a certeza que deseja aceitar a remoção do utilizador selecionado(s/n): ");
 			if(verify.contentEquals("s")) {
 				BDDriver.setUtilizadorEstado(utiliRemove.getIdUser(), 3);
 				break;
 			} else if(verify.contentEquals("n")) {
 				break;
 			} else {
 				System.out.println("Resposta inválida! Insira s ou n (s-sim, n-não)");
 			}
 		}
 		
	}
	
	
	public static void pedidosRevisao(Gestor gestor) {
		//ArrayList<Revisao> revisoes = new ArrayList<Revisao>();
		int tamanhoArray;
 		
 		tamanhoArray = BDDriver.listarRevisoes().length;
 		Revisao[] revisaoBuffer = new Revisao[tamanhoArray];
 		revisaoBuffer = BDDriver.listarRevisoes();
 		ArrayList<Revisao> revisoes = new ArrayList<Revisao>();
 		for(int i = 0; i<tamanhoArray; i++) {
 			if(revisaoBuffer[i].getEstado()==EstadoRevisao.iniciada) {
 				revisoes.add(revisaoBuffer[i]);
 			}
 		}
 		
 		
 		//GestorContas.listarRevisores()
 		
 	
 		System.out.println("Escolha qual a revisão que deseja aprovar/reprovar");
		Revisao rev = Main.SelectionarObjetoMenu(revisoes.toArray(new Revisao[0]));
		String verify = lerDados("Deseja aprovar o pedido(s/n): ");
		
		if(verify.equalsIgnoreCase("s")) {
			rev.setEstado(EstadoRevisao.iniciada);
			System.out.println("Escolha um revisor responsável");
			Revisor revisor1 = (Revisor) Main.SelectionarObjetoMenu(GestorContas.listarRevisores());
			int tamanhoArray2 = GestorContas.listarRevisores().length;
	 		Revisor[] revisorBuffer = new Revisor[tamanhoArray2];
	 		revisorBuffer = GestorContas.listarRevisores();
	 		ArrayList<Revisor> revisores = new ArrayList<Revisor>();
	 		for(int i = 0; i<tamanhoArray2; i++) {
	 			if(revisorBuffer[i].getLogin() != revisor1.getLogin()) {
	 				revisores.add(revisorBuffer[i]);
	 			}
	 		}
			int idRevisor = (int) revisor1.getIdRevisor();
			rev.setRevisorResponsavel(revisor1); //???
			BDDriver.atualizarIdGestorRevisao(rev.getRevisaoID(), gestor.getGestorID());
			BDDriver.definirRevisorResponsavel(rev.getRevisaoID(),idRevisor);
			BDDriver.atualizarEstadoRevisao(rev.getRevisaoID(), 1);
		} else {
			BDDriver.atualizarEstadoRevisao(rev.getRevisaoID(), 4);
		}
		
		
		System.out.println("Adicione mais revisores.");
		Revisor revisor2 = (Revisor) Main.SelectionarObjetoMenu(GestorContas.listarRevisores());
		if(revisor2 != null) {
		BDDriver.adicionarRevisor(rev.getRevisaoID(), revisor2.getIdRevisor());
		}
		
	}
	
	
	@Override
	public String toString() {
		return "Gestor [idGestor=" + idGestor + ", getGestorID()=" + getGestorID() + ", getIdUser()=" + getIdUser()
				+ ", getLogin()=" + getLogin() + ", getNome()=" + getNome() + 
				", getEmail()=" + getEmail() + ", getEstado()=" + getEstado() + ", getTipo()=" + getTipo()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}



	private static void erro() {
		System.out.println("Opção inválida!");
	}
	
	private static void sair(String login1) {
		System.out.println("Adeus " + login1);
		Main.main(null);								
	}
	
	private static int lerDadosInt(String aMensagem){
		System.out.println(aMensagem);
		return(new Scanner(System.in)).nextInt();
		
	}
	
	public static String lerDados(String aMensagem){
		System.out.print(aMensagem);
		return(new Scanner(System.in)).nextLine();
	}
	
}

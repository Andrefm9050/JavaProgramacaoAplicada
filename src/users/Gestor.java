package users;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import pastaPrincipal.Main;
import sistema.BDDriver;
import sistema.GestorContas;

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
	public static void menuGestor(String login1) {
		
		while(true) {
		System.out.println("1-Aprovar/Rejeitar Pedidos de Registo \n2-Criar conta Gestor \n3-Ativar/Inativar Conta \n4-Pedido Remover Conta "
				+ "\n5-Pedidos Remoção Contas Conta \n6-Sair");
		
		int opcao = lerDadosInt("Escolha uma das seguintes opções: ");
		
		executaOpcao(opcao, login1);
		
	}

	
	
}
	
	public static void executaOpcao(int aOpcao, String login1){
		
		
		switch(aOpcao) {
		case 1: aprovarRejeitarPedidosRegisto(); break;
		case 2: criarGestor(); break;
		case 3: ativarInativarConta(); break;
		case 4: pedidoRemoverConta(login1); break;
		case 5: pedidosRemocaoConta(); break;
		case 6: sair(login1); break;
		default: erro();
		}
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
 		
 		BDDriver.updateEstado(idEscolha, aprovaRejeitaN);
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
 		
 		BDDriver.updateEstado(idEscolha, ativarInativarN);
	}
	
	private static void pedidoRemoverConta(String login1) {
		
		
		while(true) {
 			String resposta = lerDados("Deseja fazer um pedido de remoção de conta(s/n): ");
 			if(resposta.contentEquals("s")) {
 				String respostaConfirm = lerDados("Tem mesmo a certeza que pretende fazer o pedido de remoção de conta(s/n): ");
 				if(respostaConfirm.contentEquals("s")) {
 					break;
 				}
 			}else if(resposta.contentEquals("n")) {
 				break;
 			}else {
 				System.out.println("Resposta inválida! Insira s ou n como resposta. (s-sim, n-nao)");
 			}
 		}
		Utilizador novoUti = GestorContas.pesquisarUtilizadoresUserName(login1);
		
		BDDriver.updateEstado(novoUti.getIdUser(), 2);
		//por_remover - 2
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
 		Main.SelectionarObjetoMenu(utilizadorNovo.toArray(new Utilizador[0]));
 		
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

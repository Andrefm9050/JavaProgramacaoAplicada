package users;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import pastaPrincipal.Main;
import sistema.BDDriver;

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
		System.out.println("1-Aprovar/Rejeitar Pedidos de Registo \n2-Criar conta Gestor \n3-Ativar/Inativar Conta \n4-Pedido Remover Conta \n5-Sair");
		
		int opcao = lerDadosInt("Escolha uma das seguintes opções: ");
		
		executaOpcao(opcao, login1);
		
	}

	
	
}
	
	public static void executaOpcao(int aOpcao, String login1){
		
		
		switch(aOpcao) {
		case 1: aprovarRejeitarPedidosRegisto(); break;
		case 2: criarGestor(); break;
		case 3: ativarInativarConta(); break;
		case 4: pedidoRemoverConta(); break;
		case 5: sair(login1); break;
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
 			}else if(ativarInativar.contentEquals("n")) {
 				ativarInativarN = 5;
 				break;
 			}else {
 				System.out.println("Resposta inválida! Insira a ou i como resposta. (a-ativo, i-inativo)");
 			}
 		}
 		
 		BDDriver.updateEstado(idEscolha, ativarInativarN);
	}
	
	private static void pedidoRemoverConta() {
		
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

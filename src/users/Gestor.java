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
	}
	
	
	private static void criarGestor() {
		
	}
	
	private static void ativarInativarConta() {
		
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
	
}

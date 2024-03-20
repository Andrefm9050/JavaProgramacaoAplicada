package users;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import pastaPrincipal.Main;

public class Gestor extends Utilizador{

	public Gestor(int idUser,String login, String password, String nome, EstadoConta estado, String email, String tipo) {
		super(idUser,login, password, nome, estado, email, tipo);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public static void menuGestor() {
		
		while(true) {
		System.out.println("1-Aprovar/Rejeitar Pedidos de Registo \n2-Criar conta Gestor \n3-Ativar/Inativar Conta \n4-Pedido Remover Conta \n5-Sair");
		
		int opcao = lerDadosInt("Escolha uma das seguintes opções: ");
		
		executaOpcao(opcao);
		
	}

	
	
}
	
	public static void executaOpcao(int aOpcao){
		
		
		switch(aOpcao) {
		case 1: aprovarRejeitarPedidosRegisto(); break;
		case 2: criarGestor(); break;
		case 3: ativarInativarConta(); break;
		case 4: pedidoRemoverConta(); break;
		case 5: sair(); break;
		default: erro();
		}
	}
	
	
	
	private static void aprovarRejeitarPedidosRegisto() {
		
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
	
	private static void sair() {
		Main.main(null);								
	}
	
	private static int lerDadosInt(String aMensagem){
		System.out.println(aMensagem);
		return(new Scanner(System.in)).nextInt();
		
	}
	
}

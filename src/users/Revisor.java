package users;

import java.util.Scanner;

import pastaPrincipal.Main;
import sistema.GestorContas;

public class Revisor extends UniqueUtilizador {
	private EspecializacaoArea areaEspecializado;
	private String formacaoAcademica;
	private int idRevisor;
	
	public Revisor(int idRevisor, int idUser,String login, String password, String nome, EstadoConta estado, String email, String tipo, String nif, String telefone, String morada) {
		super(idUser,login, password, nome, estado, email, tipo, nif, telefone, morada);
		// TODO Auto-generated constructor stub
		this.idRevisor = idRevisor;
	}
	
public static void menuRevisor(String login1) {
		
		while(true) {
		System.out.println("1-Notificações de Revisão \n2-Revisões \n3-Pedido Remover Conta \n4-Sair");
		
		int opcao = lerDadosInt("Escolha uma das seguintes opções: ");
		
		executaOpcao(opcao, login1);
		
	}
	
}

public static void executaOpcao(int aOpcao, String login1){
	
	
	switch(aOpcao) {
	case 1: notificacaoRevisao(); break;
	case 2: revisoes(); break;
	case 3: GestorContas.pedidoRemoverConta(login1); break;
	case 4: sair(login1); break;
	default: erro();
	}
}



private static void gerirRevisao() {
	
	
}

private static void notificacaoRevisao() {
	
}

private static void revisoes() {
	
}

private static void pedidoRemoverConta() {
	
}




public int getIdRevisor() {
	return idRevisor;
}

public void setIdRevisor(int idRevisor) {
	this.idRevisor = idRevisor;
}

private static void sair(String login1) {
	System.out.println("Adeus " + login1);
	Main.main(null);							
}

private static void erro() {
	System.out.println("Opção inválida!");
}


private static int lerDadosInt(String aMensagem){
	System.out.println(aMensagem);
	return(new Scanner(System.in)).nextInt();
	
}
	
}

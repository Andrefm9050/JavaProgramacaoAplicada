package users;

import java.util.Scanner;

import pastaPrincipal.Main;

public class Revisor extends UniqueUtilizador {
	private EspecializacaoArea areaEspecializado;
	private String formacaoAcademica;
	
	public Revisor(String login, String password, String nome, EstadoConta estado, String email, String tipo) {
		super(login, password, nome, estado, email, tipo);
		// TODO Auto-generated constructor stub
	}
	
public static void menuRevisor() {
		
		while(true) {
		System.out.println("1-Notificações de Revisão \n2-Revisões \n3-Sair");
		
		int opcao = lerDadosInt("Escolha uma das seguintes opções: ");
		
		executaOpcao(opcao);
		
	}
	
}

public static void executaOpcao(int aOpcao){
	
	
	switch(aOpcao) {
	case 1: notificacaoRevisao(); break;
	case 2: revisoes(); break;
	case 4: sair(); break;
	default: erro();
	}
}



private static void gerirRevisao() {
	
	
}

private static void notificacaoRevisao() {
	
}

private static void revisoes() {
	
}




private static void sair() {
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

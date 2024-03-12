package users;

import java.util.Date;
import java.util.Scanner;

import pastaPrincipal.Main;
import sistema.EstiloLiterario;

public class Autor extends UniqueUtilizador{
	private String estilo;
	private Date dataInicioAtividade;
	
	public Autor(String login, String password, String nome, EstadoConta estado, String email, String tipo) {
		super(login, password, nome, estado, email, tipo);
		// TODO Auto-generated constructor stub
	}
	
public static void menuAutor() {
		
		while(true) {
		System.out.println("1-Submeter obra para Revisão \n2-Estado da Revisão \n3-Inserir Obra \n4-Sair");
		
		int opcao = lerDadosInt("Escolha uma das seguintes opções: ");
		
		executaOpcao(opcao);
		
	}
	
}

public static void executaOpcao(int aOpcao){
	
	
	switch(aOpcao) {
	case 1: submeterObraRevisao(); break;
	case 2: estadoRevisao(); break;
	case 3: inserirObra(); break;
	//case 4: pedidoRemoverConta(); break;
	case 4: sair(); break;
	default: erro();
	}
}

private static void inserirObra() {
	
}

private static void estadoRevisao() {
	
	
}


private static void submeterObraRevisao() {
	
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

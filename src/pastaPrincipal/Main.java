package pastaPrincipal;

import java.util.Scanner;

public class Main {
	
	public static void main(String [] args)  {
		
		
		while(true) {
			System.out.println("1-Registar \n2-Login \n3-Sair");
			
			int opcao = lerDadosInt("Escolha uma das seguintes opções: ");
			
			executaOpcao(opcao);
			
		}
			
				
		}
	
	
	private static void executaOpcao(int aOpcao){
		switch(aOpcao) {
		case 1: registo(); break;
		case 2: login(); break;
		case 3: sair(); break;
		default: erro();
		}
	}
	
	
	private static void erro() {
		System.out.println("Opção inválida!");
	}
	
	private static void sair() {
		//System.out.println("Adeus " + userLoginName);
		System.out.println("A encerrar aplicação!"); 
		System.exit(0);								
	}
	
	private static void registo() {
		
	}
	
	private static void login() {
		
	}
	
	
	
	
	private static int lerDadosInt(String aMensagem){
		System.out.println(aMensagem);
		return(new Scanner(System.in)).nextInt();
		
	}
	private static String lerDados(String aMensagem){
		System.out.println(aMensagem);
		return(new Scanner(System.in)).nextLine();
	}
	
}

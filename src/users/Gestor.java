package users;

public class Gestor extends Utilizador{

	public Gestor(String login, String password, String nome, EstadoConta estado, String email, String tipo) {
		super(login, password, nome, estado, email, tipo);
		// TODO Auto-generated constructor stub
	}
	
	private static void executaOpcao(int aOpcao){
		switch(aOpcao) {
		case 1: aprovarRejeitarPedidosRegisto(); break;
		case 2: login(); break;
		case 3: sair(); break;
		default: erro();
		}
	}
	
	
	
	private static void aprovarRejeitarPedidosRegisto() {
		
	}
	
	
	private static void erro() {
		System.out.println("Opção inválida!");
	}
	
	private static void sair() {
		//System.out.println("Adeus " + userLoginName);
		System.out.println("A encerrar aplicação!"); 
		System.exit(0);								
	}
	
}

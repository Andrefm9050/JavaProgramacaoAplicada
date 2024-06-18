package users;

import sistema.BDDriver;
import sistema.EmailHelper;
import sistema.Obra;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Classe responsavel por gerir contas e executar as suas diferentes operacoes no sistema
 * @author Andre Martins
 */
public class GestorContas {
	static Connection conn = null;
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9]*@[a-zA-Z]+\\.[a-zA-Z]+$";		//Expressão regular para o email
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);				
	
	
	private static final String NIF_REGEX = "^\\d{9}$";  									//Expressão regular para o NIF
    private static final Pattern NIF_PATTERN = Pattern.compile(NIF_REGEX);
    
    private static final String TELEFONE_REGEX = "^[923]\\d{8}$";						    //Expressão regular para o Telefone
    private static final Pattern TELEFONE_PATTERN = Pattern.compile(TELEFONE_REGEX);
	
	
	public static boolean validacaoEmail(String email2) {
	    Matcher matcher = EMAIL_PATTERN.matcher(email2);
        return matcher.matches();
		
	}
	
	public static boolean validacaoNIF(String nif1) {
		Matcher matcher = NIF_PATTERN.matcher(nif1);
        return matcher.matches();
	}
	
	public static boolean validacaoTelefone(String telefone1) {
		Matcher matcher = TELEFONE_PATTERN.matcher(telefone1);
        return matcher.matches();
	}
	
	
	
	/**
	 * @param nif1 - NIF
	 * @return true se o NIF for unico
	 */
	public static boolean nifVal(String nif1) {
		int tamanhoArray;
		tamanhoArray = BDDriver.listarUtilizadores().length;
		Utilizador[] utilizadorBuffer = new Utilizador[tamanhoArray];
		utilizadorBuffer = BDDriver.listarUtilizadores();
		
			for(int i = 0; i<tamanhoArray; i++) {
				if(utilizadorBuffer[i] instanceof UniqueUtilizador && ( (UniqueUtilizador) BDDriver.listarUtilizadores()[i]).getNif().contentEquals(nif1) ) {
					return false;
				}
			}
		
		
		return true;
		
	}
	public static Utilizador[] listarUtilizadores() {
		return BDDriver.listarUtilizadores();
	}
	public static Utilizador pesquisarUtilizadoresEmail(String mail1) { 
		int tamanhoArray;
 		
 		tamanhoArray = BDDriver.listarUtilizadores().length;
 		Utilizador[] utilizadorBuffer = new Utilizador[tamanhoArray];
 		utilizadorBuffer = BDDriver.listarUtilizadores();
		
 		for(int i=0; i<tamanhoArray; i++) {
 	    	if(utilizadorBuffer[i].getEmail().contentEquals(mail1)) {
 	    		
 	    		return utilizadorBuffer[i];
 	    	}
 	    	 
 	    }
		return null;
		
	}
	
	public static Utilizador pesquisarUtilizadoresUserName(String login1) { 
 		//if(BDDriver.listarUtilizadores().length != 0) {
		int tamanhoArray;
		tamanhoArray = BDDriver.listarUtilizadores().length;
		Utilizador[] utilizadorBuffer = new Utilizador[tamanhoArray];
		utilizadorBuffer = BDDriver.listarUtilizadores();
 		//}
 		//Utilizador[] utilizadorBuffer = BDDriver.listarUtilizadores();
 		//utilizadorBuffer = BDDriver.listarUtilizadores();
 		   
 	    for(int i=0; i<tamanhoArray; i++) {
 	    	if(utilizadorBuffer[i].getLogin().contentEquals(login1)) {
 	    		return utilizadorBuffer[i];
 	    	}
 	    }
 		return null;
 		
 	}
	
	/**
	 * 
	 * @param login1 - username/login da conta
	 * @param password1 - password da conta
	 * @return Utilizador com essas credencias (se existir)
	 */
	public static Utilizador encontrarUtilizador(String login1, String password1) { //verifica se existe esse utilizador na base de dados em geral
		   //BDDriver.listarUtilizadores();
		   
		   for(int i = 0; i<BDDriver.listarUtilizadores().length; i++) {
			   if(BDDriver.listarUtilizadores()[i].getLogin() == login1 && BDDriver.listarUtilizadores()[i].getPassword() == password1) {
				   return BDDriver.listarUtilizadores()[i];
				   //System.out.println(BDDriver.listarUtilizadores()[i].getLogin());
			   } 
			   
			
		   }
	        	   
		   return null;
	   }
	public static Gestor[] listarGestores() {
		ArrayList<Gestor> utilizadores = new ArrayList<Gestor>();
		
		for(var user : BDDriver.listarUtilizadores()) {
			if(user instanceof Gestor) {
				utilizadores.add((Gestor)user);
			}
		}
		return utilizadores.toArray(new Gestor[0]);
	}
	
	public static Revisor[] listarRevisores() {
		ArrayList<Revisor> revisores = new ArrayList<Revisor>();
		
		for(var user : BDDriver.listarUtilizadores()) {
			if(user instanceof Revisor) {
				revisores.add((Revisor)user);
			}
		}
		return revisores.toArray(new Revisor[0]);
	}
	
	public static void definirEstadoUtilizador(String login1,EstadoConta estado) {
		
		Utilizador u = pesquisarUtilizadoresUserName(login1);
		
		boolean result = BDDriver.setUtilizadorEstado(u.getIdUser(), EstadoConta.estadoToInt(estado));
		if(u != null) {
			
			if(u.getEstado() == EstadoConta.por_registar && estado == EstadoConta.ativos && result) {
				EmailHelper helper = new EmailHelper();
				helper.SendMailConfirmed(u);
			}
		}
	}
	
	public static boolean definirPasswordUtilizador(int userID, String password) {
		return BDDriver.setUtilizadorPassword(userID, password);
	}
	
	public static boolean definirNomeUtilizador(int userID, String nome) {
		return BDDriver.setUtilizadorNome(userID, nome);
	}
	public static boolean definirImagemUtilizador(int userID, byte[] imagem) {
		return BDDriver.setUtilizadorImagem(userID, imagem);
	}
	
	
public static void pedidoRemoverConta(String login1) {
		
		
		//while(true) {
 			String resposta = "s";
 			//if(resposta.contentEquals("s")) {
 				//String respostaConfirm = lerDados("Tem mesmo a certeza que pretende fazer o pedido de remoção de conta(s/n): ");
 				//if(respostaConfirm.contentEquals("s")) {
 					//break;
 				//}
 			//}else if(resposta.contentEquals("n")) {
 				//break;
 			//}else {
 				//System.out.println("Resposta inválida! Insira s ou n como resposta. (s-sim, n-nao)");
 			//}
 		//}
		Utilizador novoUti = GestorContas.pesquisarUtilizadoresUserName(login1);
		
		BDDriver.setUtilizadorEstado(novoUti.getIdUser(), 2);
		//por_remover - 2
	}


public static int isbnUnico() {
	int tamanhoArray;
	Random rand = new Random();
	int randomNum = rand.nextInt(1000001);
	tamanhoArray = BDDriver.listarObras().length;
	Obra[] obrasBuffer = new Obra[tamanhoArray];
	obrasBuffer = BDDriver.listarObras();
	ArrayList<Obra> obraNovo = new ArrayList<Obra>();
	int contador = 0;
	
	while(true) {
		for(int i=0; i<tamanhoArray; i++) {
			if(obrasBuffer[i].getIsbn()==randomNum ) {
				contador = 1;
			}
		}
		if(contador == 1) {
			randomNum = rand.nextInt(1000001);
		}else if(contador == 0){
			return randomNum;
		}
	}
	
}





	
	
	
	
	
	
	
public static String lerDados(String aMensagem){
	System.out.print(aMensagem);
	return(new Scanner(System.in)).nextLine();
}
}

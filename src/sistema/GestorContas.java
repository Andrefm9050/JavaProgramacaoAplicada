package sistema;
import java.util.regex.Pattern;

import users.Autor;
import users.EstadoConta;
import users.Gestor;
import users.UniqueUtilizador;
import users.Utilizador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;

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
	
	public static boolean configurarDriver(String link,String username,String password,String bd) {
	   try {
		Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
		return false;
	}
	   try {
		conn = DriverManager.getConnection(link + bd, username, password);
	} catch (SQLException e) {
		return false;
	}
	   return true;
   }
	
	
	
	public static boolean nifVal(String nif1) {
		int tamanhoArray;
		tamanhoArray = BDDriver.listarUtilizadores().length;
		Utilizador[] utilizadorBuffer = new Utilizador[tamanhoArray];
		utilizadorBuffer = BDDriver.listarUtilizadores();
		
			for(int i = 0; i<tamanhoArray; i++) {
				if(utilizadorBuffer[i] instanceof UniqueUtilizador && ( (UniqueUtilizador) BDDriver.listarUtilizadores()[i]).getNif() == nif1 ) {
					return false;
				}
			}
		
		
		return true;
		
	}
	
	public static Utilizador pesquisarUtilizadoresEmail(String mail1) { 
		int tamanhoArray;
 		
 		tamanhoArray = BDDriver.listarUtilizadores().length;
 		Utilizador[] utilizadorBuffer = new Utilizador[tamanhoArray];
 		utilizadorBuffer = BDDriver.listarUtilizadores();
		
 		for(int i=0; i<tamanhoArray; i++) {
 	    	if(utilizadorBuffer[i].getEmail() == mail1) {
 	    		
 	    		return utilizadorBuffer[i];
 	    	}
 	    	 
 	    }
		return null;
		
	}
	
	public static Utilizador pesquisarUtilizadoresUserName(String login1) { 
 		if(BDDriver.listarUtilizadores().length != 0) {
 			
 		}
 		Utilizador[] utilizadorBuffer = BDDriver.listarUtilizadores();
 		//utilizadorBuffer = BDDriver.listarUtilizadores();
 		   
 	    for(int i=0; i<utilizadorBuffer.length; i++) {
 	    	if(utilizadorBuffer[i].getLogin() == login1) {
 	    		return BDDriver.listarUtilizadores()[i];
 	    	}
 	    }
 		return null;
 		
 	}
	
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
	
	
	
	
	
	
	

}

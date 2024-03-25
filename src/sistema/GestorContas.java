package sistema;
import java.util.regex.Pattern;

import users.Autor;
import users.EstadoConta;
import users.Gestor;
import users.Utilizador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 		int tamanhoArray;
 		
 		tamanhoArray = BDDriver.listarUtilizadores().length;
 		Utilizador[] utilizadorBuffer = new Utilizador[tamanhoArray];
 		utilizadorBuffer = BDDriver.listarUtilizadores();
 		   
 	    for(int i=0; i<tamanhoArray; i++) {
 	    	if(utilizadorBuffer[i].getLogin() == login1) {
 	    		
 	    		return utilizadorBuffer[i];
 	    	}
 	    	 
 	    }
 		return null;
 		
 	}
	
	
	
	
	
	
	public static Utilizador encontrarUtilizador(String login1, String password1) { //verifica se existe esse utilizador na base de dados em geral
		   BDDriver.configurarDriver("jdbc:postgresql://aid.estgoh.ipc.pt:5432/", "a2021159661", "a2021159661", "db2021159661");
		   try {
			
			StringBuffer sqlQuery = new StringBuffer();
			sqlQuery.append("SELECT * FROM listar_gestores()");
	        PreparedStatement ps1 = conn.prepareStatement(sqlQuery.toString());
	        ps1.clearParameters();
	        
	        ResultSet rs1 = ps1.executeQuery();
	        
	        while(rs1.next()) {
	        	//user[contador] = rs.getString(5);
	        	//int idUser = rs1.getInt(1);
	        	String user = rs1.getString(6);
	        	String pass = rs1.getString(4);
	        	String maill = rs1.getString(3);
	        	String estado = rs1.getString(5);
	        	String nome = rs1.getString(7);
	        	
	        	System.out.println(user);
	        	System.out.println(pass);
	        	if(user.equals(login1) && pass.equals(password1)) {
	        		//System.out.println("Bem-vindo " + login1);
	        		
	        		Gestor utilizadorNovo = new Gestor(0 ,login1, password1, nome, EstadoConta.ativos, maill, null);
	        		ps1.close();
	        		return utilizadorNovo;
	        	}
	        	//System.out.println(pass);
	        	//contador++;
	        	
	        }
	        
	        
	        
	        StringBuffer sqlQuery1 = new StringBuffer();
			sqlQuery1.append("SELECT * FROM listar_autores()");
	        PreparedStatement ps2 = conn.prepareStatement(sqlQuery1.toString());
	        ps2.clearParameters();
	        
	        ResultSet rs2 = ps2.executeQuery();
	        
	        while(rs1.next()) {
	        	//user[contador] = rs.getString(5);
	        	//int idUser = rs2.getInt(1);
	        	String user = rs2.getString(6);
	        	String pass = rs2.getString(4);
	        	String maill = rs2.getString(3);
	        	String estado = rs2.getString(5);
	        	String nome = rs2.getString(7);
	        
	        	if(user.equals(login1) && pass.equals(password1)) {
	        		//System.out.println("Bem-vindo " + login1);
	        		
	        		Autor utilizadorNovo = new Autor(0,login1, password1, nome, EstadoConta.ativos, maill, null);
	        		ps1.close();
	        		return utilizadorNovo;
	        	}
	        	
	        	
	        }
	        
	        StringBuffer sqlQuery2 = new StringBuffer();
			sqlQuery2.append("SELECT * FROM listar_autores()");
	        PreparedStatement ps3 = conn.prepareStatement(sqlQuery2.toString());
	        ps2.clearParameters();
	        
	        ResultSet rs3 = ps3.executeQuery();
	        
	        while(rs3.next()) {
	        	//user[contador] = rs.getString(5);
	        	//int idUser = rs2.getInt(1);
	        	String user = rs3.getString(6);
	        	String pass = rs3.getString(4);
	        	String maill = rs3.getString(3);
	        	String estado = rs3.getString(5);
	        	String nome = rs3.getString(7);
	        
	        	if(user.equals(login1) && pass.equals(password1)) {
	        		//System.out.println("Bem-vindo " + login1);
	        		
	        		Autor utilizadorNovo = new Autor(0,login1, password1, nome, EstadoConta.ativos, maill, null);
	        		ps1.close();
	        		return utilizadorNovo;
	        	}
	        	
	        	
	        }
	        
	        
	        
	        
	        //utilizadorBuffer[contador] = new Utilizador(idUser,login1, pass, nome, EstadoConta.ativos, maill, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	       
		   
		   
		   return null;
	   }

}

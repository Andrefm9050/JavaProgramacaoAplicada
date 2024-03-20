package sistema;
import java.util.regex.Pattern;

import users.EstadoConta;
import users.Utilizador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;

public class GestorContas {
	
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9]*@[a-zA-Z]+\\.[a-zA-Z]+$";		//Expressão regular para o email
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);				
	
	
	private static final String NIF_REGEX = "^\\d{9}$";  										//Expressão regular para o NIF
    private static final Pattern NIF_PATTERN = Pattern.compile(NIF_REGEX);
	
	
	public static boolean validacaoEmail(String email2) {
	    Matcher matcher = EMAIL_PATTERN.matcher(email2);
        return matcher.matches();
		
	}
	
	public static boolean validacaoNIF(String nif1) {
		Matcher matcher = NIF_PATTERN.matcher(nif1);
        return matcher.matches();
	}
	
	
	//Nao deviamos estar a fazer uma query aqui... por favor lista os utilizadores a partir do BDDriver e faz os ifs aqui dentro
	public static Utilizador pesquisarUtilizadoresUserName(String login1) { 
		Connection conn = null;
		   
		   try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://aid.estgoh.ipc.pt:5432/db2021159661", "a2021159661", "a2021159661");
			StringBuffer sqlQuery = new StringBuffer();
			sqlQuery.append("SELECT * FROM listar_utilizadores()");
	        PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
	        ps.clearParameters();
	        
	        
	        
	        ResultSet rs = ps.executeQuery();
	        //int contador=0;
	        //String user[] = null;
	        while(rs.next()) {
	        	//user[contador] = rs.getString(5);
	        	int idUser = rs.getInt(1);
	        	String user = rs.getString(5);
	        	String pass = rs.getString(3);
	        	String maill = rs.getString(2);
	        	String estado = rs.getString(4);
	        	String nome = rs.getString(6);
	        	
	        	
	        	if(user.equals(login1)) {
	        		//System.out.println("Bem-vindo " + login1);
	        		
	        		Utilizador utilizadorNovo = new Utilizador(idUser,login1, pass, nome, EstadoConta.ativos, maill, null);
	        		ps.close();
	        		return utilizadorNovo;
	        	}
	        	//System.out.println(pass);
	        	//contador++;
	        	
	        }
	        
	        
	        
	        
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	       	
		
		return null;
		
	}
	
	//Lê o comentário em cima, estamos a repetir codigo desnecessáriamente
	public static Utilizador pesquisarUtilizadoresEmail(String mail1) { 
		Connection conn = null;
		   
		   try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://aid.estgoh.ipc.pt:5432/db2021159661", "a2021159661", "a2021159661");
			StringBuffer sqlQuery = new StringBuffer();
			sqlQuery.append("SELECT * FROM listar_utilizadores()");
	        PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
	        ps.clearParameters();
	        
	        
	        
	        ResultSet rs = ps.executeQuery();
	        //int contador=0;
	        //String user[] = null;
	        while(rs.next()) {
	        	//user[contador] = rs.getString(5);
	        	int idUser = rs.getInt(1);
	        	String user = rs.getString(5);
	        	String pass = rs.getString(3);
	        	String maill = rs.getString(2);
	        	String estado = rs.getString(4);
	        	String nome = rs.getString(6);
	        	
	        	
	        	if(maill.equals(mail1)) {
	        		//System.out.println("Bem-vindo " + login1);
	        		
	        		Utilizador utilizadorNovo = new Utilizador(idUser,user, pass, nome, EstadoConta.ativos, maill, null);
	        		ps.close();
	        		return utilizadorNovo;
	        	}
	        	//System.out.println(pass);
	        	//contador++;
	        	
	        }
	        
	        
	        
	        
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	       	
		
		return null;
		
	}

}

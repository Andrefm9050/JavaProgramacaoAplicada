package sistema;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import users.Utilizador;

public class BDDriver {
   public static int adicionarUtilizador(Utilizador u1) {
      Connection conn = null;

      try {
         Class.forName("org.postgresql.Driver");
         conn = DriverManager.getConnection("jdbc:postgresql://aid.estgoh.ipc.pt:5432/db2021159661", "a2021159661", "a2021159661");
         StringBuffer sqlQuery = new StringBuffer();
         String tipo1 = u1.getTipo();
         String email1 = u1.getEmail();
         String password1 = u1.getPassword();
         String login1 = u1.getLogin();
         int idUser;
         if (tipo1.equalsIgnoreCase("Gestor")) {
            sqlQuery.append("SELECT * FROM criar_gestor(?, ? ,0 , ?)");
            PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
            ps.clearParameters();
            ps.setString(1, email1);
            ps.setString(2, password1);
            ps.setString(3, login1);
            ResultSet rs = ps.executeQuery();
            rs.next();
            idUser = rs.getInt(1);
            System.out.println(idUser);
            return idUser;
         }

         String morada1;
         String nif1;
         String telefone1;
         String formacao;
         if (tipo1.equalsIgnoreCase("Autor")) {
            morada1 = lerDados("Insira a sua morada: ");
            nif1 = lerDados("Insira o seu nif: ");
            telefone1 = lerDados("Insira o seu número contacto telefónico: ");
            formacao = lerDados("Insira a data de inicio de atividade no seguinte formato yyyy-mm-dd: ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(formacao, formatter);
            Date completedDate = Date.valueOf(date);
            String estiloLiterario = lerDados("Insira o seu estilo literário(ex: drama, ficção, thriller): ");
            sqlQuery.append("SELECT * FROM criar_autor(?, ? ,0 , ?, ?, ?, ?, ?, ?)");
            PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
            ps.clearParameters();
            ps.setString(1, email1);
            ps.setString(2, password1);
            ps.setString(3, login1);
            ps.setString(4, morada1);
            ps.setString(5, nif1);
            ps.setString(6, telefone1);
            ps.setDate(7, completedDate);
            ps.setString(8, estiloLiterario);
            ResultSet rs = ps.executeQuery();
            rs.next();
            idUser = rs.getInt(1);
            System.out.println(idUser);
            return idUser;
         }

         if (tipo1.equalsIgnoreCase("Revisor")) {
            morada1 = lerDados("Insira a sua morada: ");
            nif1 = lerDados("Insira o seu nif: ");
            telefone1 = lerDados("Insira o seu número contacto telefónico: ");
            formacao = lerDados("Insira a sua formacao academica: ");
            String area = lerDados("Insira a sua area de especializacao: ");
            sqlQuery.append("SELECT * FROM criar_revisor(?, ? ,0 , ?, ?, ?, ?, ?, ?)");
            PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
            ps.clearParameters();
            ps.setString(1, email1);
            ps.setString(2, password1);
            ps.setString(3, login1);
            ps.setString(4, morada1);
            ps.setString(5, nif1);
            ps.setString(6, telefone1);
            ps.setString(7, formacao);
            ps.setString(8, area);
            ResultSet rs = ps.executeQuery();
            rs.next();
            idUser = rs.getInt(1);
            System.out.println(idUser);
            return idUser;
         }

         System.out.println("Connection OK");
      } catch (Exception var18) {
         var18.printStackTrace();
      }

      return 0;
   }
   
   public static String listarUtilizador(String login1, String password1) {
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
        	String user = rs.getString(5);
        	String pass = rs.getString(3);
      
        	
        	if(user.equals(login1) && pass.equals(password1)) {
        		System.out.println("Bem-vindo " + login1);
        		return login1;
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
   
   public static String listarUtilizadores2(String login1, String tipo1) {
	   Connection conn = null;
	   
	   try {
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection("jdbc:postgresql://aid.estgoh.ipc.pt:5432/db2021159661", "a2021159661", "a2021159661");
		String queryAppend = "SELECT * FROM listar_";
		String tipo2 = tipo1;
		String queryAppend1 = "()";
		
		StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(queryAppend+tipo2+queryAppend1);
        PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
        ps.clearParameters();
        
        
        
        ResultSet rs = ps.executeQuery();
        //int contador=0;
        //String user[] = null;
        while(rs.next()) {
        	//user[contador] = rs.getString(5);
        	String user = rs.getString(5);
        	String pass = rs.getString(3);
      
        	
        	if(user.equals(login1)) {
        		System.out.println("Bem-vindo " + login1);
        		return login1;
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

   private static int lerDadosInt(String aMensagem) {
      System.out.println(aMensagem);
      return (new Scanner(System.in)).nextInt();
   }

   private static String lerDados(String aMensagem) {
      System.out.println(aMensagem);
      return (new Scanner(System.in)).nextLine();
   }
}
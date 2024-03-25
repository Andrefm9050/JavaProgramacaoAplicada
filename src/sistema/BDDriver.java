package sistema;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import gestao.Log;
import users.EstadoConta;
import users.Utilizador;

//Por favor converte isto para um objeto (Não uses static)
public class BDDriver {
   static Connection conn = null;
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
   public static void adicionarLog(Utilizador u, String mensagem) {
	   try {
		PreparedStatement ps = conn.prepareStatement("CALL criar_log(?, ?)");
		ps.setInt(1, u.getIdUser());
		ps.setString(2, mensagem);
		ps.execute();
		ps.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }
   public static Log[] listarLogs() {
	   ArrayList<Log> logslist = new ArrayList<Log>();
	   try {
		   PreparedStatement ps = conn.prepareStatement("SELECT * FROM listar_logs()");
		   ResultSet rs = ps.executeQuery();
		   while(rs.next()) {
		   logslist.add(new Log(rs.getInt(1),rs.getString(2),rs.getDate(3)));
		   }
	   } catch (SQLException e) {
			e.printStackTrace();
	   }
	   
	   return logslist.toArray(new Log[0]);
   }
   public static int adicionarUtilizador(Utilizador u1) {
     
      try {
	     
         StringBuffer sqlQuery = new StringBuffer();																				///
         String tipo1 = u1.getTipo();												
         String email1 = u1.getEmail();
         String password1 = u1.getPassword();   /// variaveis do Objeto Utilizador u1
         String login1 = u1.getLogin();
         String nome1 = u1.getNome();
         int idUser;
         if (tipo1.equalsIgnoreCase("Gestor")) {
            sqlQuery.append("SELECT * FROM criar_gestor(?, ? , ?, 0 , ?)");  		//
            PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());		//
            ps.clearParameters();													//
            ps.setString(1, nome1);			 								 		//funcao para criar gestor base de dados com inserção de variaveis do u1
            ps.setString(2, email1);         								 		//
            ps.setString(3, password1);    										    //
            ps.setString(4, login1);
            ResultSet rs = ps.executeQuery();
            rs.next();
            idUser = rs.getInt(1);      //recebe o id do utilizador proveniente da base de dados
            //System.out.println(idUser);
            ps.close();
            return idUser;
         }

         String morada1;
         String nif1;
         String telefone1;
         String formacao;
         if (tipo1.equalsIgnoreCase("Autor")) {
            morada1 = lerDados("Insira a sua morada: ");
           
            while(true) {
            	nif1 = lerDados("Insira o seu nif (9 números): ");
            	if(BDDriver.encontrarUtilizadores3(nif1, "revisores") == false || BDDriver.encontrarUtilizadores3(nif1, "autores") == false) {
            		System.out.println("NIF já existe! Insira outro NIF.");
            	} else if(GestorContas.validacaoNIF(nif1)!=true) {
            		System.out.println("O seu número não tem 9 digitos! Insira novamente.");
            	}
            	else {
            		break;
            	}
            }
            
            while(true) {
            	telefone1 = lerDados("Insira o seu número contacto telefónico (Deverá começar por 9,2 ou 3): ");
            	if(GestorContas.validacaoTelefone(telefone1)!=true) {
            		System.out.println("O seu número não é válido! Insira novamente.");
            	}else {
            		break;
            	}
            	
            }
            
            formacao = lerDados("Insira a data de inicio de atividade no seguinte formato yyyy-mm-dd: ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(formacao, formatter);
            Date completedDate = Date.valueOf(date);
            String estiloLiterario = lerDados("Insira o seu estilo literário(ex: drama, ficção, thriller): ");
            sqlQuery.append("SELECT * FROM criar_autor(?, ?, ? , 0 , ?, ?, ?, ?, ?, ?)");
            PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
            ps.clearParameters();
            ps.setString(1, nome1);
            ps.setString(2, email1);
            ps.setString(3, password1);
            ps.setString(4, login1);
            ps.setString(5, morada1);
            ps.setString(6, nif1);
            ps.setString(7, telefone1);
            ps.setDate(8, completedDate);
            ps.setString(9, estiloLiterario);
            ResultSet rs = ps.executeQuery();
            rs.next();
            idUser = rs.getInt(1);
            //System.out.println(idUser);
            ps.close();
            return idUser;
         }

         if (tipo1.equalsIgnoreCase("Revisor")) {
            morada1 = lerDados("Insira a sua morada: ");
            
            while(true) {
            	nif1 = lerDados("Insira o seu nif (9 números): ");
            	if((BDDriver.encontrarUtilizadores3(nif1, "revisores")==false) || (BDDriver.encontrarUtilizadores3(nif1, "autores")==false)) {
            		System.out.println("NIF já existe! Insira outro NIF.");
            	} else if(GestorContas.validacaoNIF(nif1)!=true) {
            		System.out.println("O seu número não tem 9 digitos! Insira novamente.");
            	}
            	else {
            		break;
            	}
            }
            
            while(true) {
            	telefone1 = lerDados("Insira o seu número contacto telefónico (Deverá começar por 9,2 ou 3): ");
            	if(GestorContas.validacaoTelefone(telefone1)!=true) {
            		System.out.println("O seu número não é válido! Insira novamente.");
            	}else {
            		break;
            	}
            	
            }
            formacao = lerDados("Insira a sua formacao academica: ");
            String area = lerDados("Insira a sua area de especializacao: ");
            sqlQuery.append("SELECT * FROM criar_revisor(?, ? , ?, 0 , ?, ?, ?, ?, ?, ?)");
            PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
            ps.clearParameters();
            ps.setString(1, nome1);
            ps.setString(2, email1);
            ps.setString(3, password1);
            ps.setString(4, login1);
            ps.setString(5, morada1);
            ps.setString(6, nif1);
            ps.setString(7, telefone1);
            ps.setString(8, formacao);
            ps.setString(9, area);
            ResultSet rs = ps.executeQuery();
            rs.next();
            idUser = rs.getInt(1);
            //System.out.println(idUser);
            ps.close();
            return idUser;
         }
         

         System.out.println("Connection OK");
      } catch (Exception var18) {
         var18.printStackTrace();
      }

      return 0;
   }
   
   
  
   
   
   public static boolean encontrarUtilizadores3(String nif1, String tipo1) {    // verifica se existe um utilizador na base de dados, porém verifica
	   																			// também se o mesmo encontra-se numa tabela com um cargo (Gestor, autor, revisor)
	    																		 // e qual o cargo.
	   try {
	
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
        	String user = rs.getString(6);
        	String pass = rs.getString(4);
        	String mail = rs.getString(3);
        	String estado = rs.getString(5);
        	String nome = rs.getString(7);
        	String nifOutro = rs.getString(9);
        	
        	
        	//System.out.println(nifOutro);
        	//System.out.println("Bem-vindo " + teste);
        	if(nifOutro.equals(nif1)) {
        		//System.out.println(teste);
        		ps.close();
        		return false;
        		
        	}
        	//System.out.println(pass);
        	//contador++;
        	
        }
        
        
        
        
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   
	   return true; 
   }
   
   

 	
 	public static Utilizador[] listarUtilizadores() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://aid.estgoh.ipc.pt:5432/db2021159661", "a2021159661", "a2021159661");
			StringBuffer sqlQuery = new StringBuffer();
			sqlQuery.append("SELECT * FROM listar_utilizadores()");
	        PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
	        ps.clearParameters();
	        
	        
	        
	        ResultSet rs = ps.executeQuery();
	        int contador=0;
	        //String user[] = null;
	        Utilizador[] utilizadorBuffer = new Utilizador[10];
	        while(rs.next()) {
	        	//user[contador] = rs.getString(5);
	        	int idUser = rs.getInt(1);
	        	String user = rs.getString(5);
	        	String pass = rs.getString(3);
	        	String maill = rs.getString(2);
	        	String estado = rs.getString(4);
	        	String nome = rs.getString(6);
	        	
	        	
	        	//System.out.println("Bem-vindo " + login1);
	        	utilizadorBuffer[contador] = new Utilizador(idUser,user, pass, nome, EstadoConta.ativos, maill, null);
	        	//Utilizador utilizadorNovo = new Utilizador(idUser,user, pass, nome, EstadoConta.ativos, maill, null);
	        	ps.close();
	        	contador++;
	        	
	        	
	        	//System.out.println(pass);
	        	
	        }
	        return utilizadorBuffer;
	        
	        
	        
	        
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
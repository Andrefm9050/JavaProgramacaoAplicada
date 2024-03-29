package sistema;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import pastaPrincipal.Main;
import users.Autor;
import users.EstadoConta;
import users.Gestor;
import users.Revisor;
import users.UniqueUtilizador;

import users.Revisor;

import users.Utilizador;

public class BDDriver {
   static Connection conn = null;
   
   public static void menuConfiguracao() {
	   ManipulaFicheirosTexto fich = new ManipulaFicheirosTexto();
	   
	   System.out.println("Menu de configuracao BD");
	   int index = 0;
	   int input = 0;
	   
	   do {
		   index = 0;
		   if(!fich.abrirFicheiroLeitura("Properties")) {
			   
			   fich.abrirFicheiroEscrita("Properties",false);
			   fich.escreverFicheiro(new String[]{"ip=","port=","bd=","login=","password="});
			   fich.fecharFicheiroEscrita();
			   index = -1;
			   continue;
		   }
		   System.out.println("Selectione uma propriedade com o seu numero:");
		   String[] linhas = fich.lerFicheiro();
		   for(var linha : linhas) {
			   String[] conts = linha.split("=");
			   System.out.println(index + "- " + conts[0] + "=" + (conts.length == 2 ? conts[1] : ""));
			   index++;
		   }
		   System.out.println(index + "- Sair");
		   input = Main.lerDadosInt(":");
		   try {
			   linhas[input] = linhas[input].split("=")[0] + "=" + Main.lerDados("Novo Valor: ");
		   }
		   catch(Exception e) {
			   input = index;
		   }
		   finally {
			   fich.fecharFicheiroLeitura();
		   }
		   if(input != index) {
			   fich.abrirFicheiroEscrita("Properties", false);
			   fich.escreverFicheiro(linhas);
			   fich.fecharFicheiroEscrita();
		   }
		   
	   }while(input != index);
	   
	   
   }

   public static boolean configurarDriverPorFicheiro(String caminho) {
	   ManipulaFicheirosTexto fich = new ManipulaFicheirosTexto();
	   if(!fich.abrirFicheiroLeitura(caminho)) return false;
	   
	   String ip = "";
	   String port = "";
	   String bd = "";
	   String login = "";
	   String password = "";
	   
	   for(var linha : fich.lerFicheiro()) {
		   String property=linha.split("=")[0];
		   switch(property) {
		   case "ip":
			   try {
			   ip = linha.split("=")[1];
			   }
			   catch (Exception e){
				   ip = "";
			   }
			   break;
		   case "port":
			   try {
				   port = linha.split("=")[1];
				   }
				   catch (Exception e){
					   port = "";
				   }
				   break;
		   case "bd":
			   try {
				   bd = linha.split("=")[1];
			   }
			   catch (Exception e){
				   bd = "";
			   }
			   break;
		   case "login":
			   try {
				   login = linha.split("=")[1];
				   }
				   catch (Exception e){
					   login = "";
				   }
				   break;
		   case "password":
			   try {
				   password = linha.split("=")[1];
				   }
				   catch (Exception e){
					   password = "";
				   }
				   break;
			
		   }
	   }
	   
	   return configurarDriver(ip,port,login,password,bd);
   }
   
   static String link;
   static String port;
   static String username;
   static String password;
   static String bd;
   public static boolean configurarDriver(String link,String port,String username,String password,String bd) {
	   BDDriver.link = link;
	   BDDriver.port = port;
	   BDDriver.username = username;
	   BDDriver.password = password;
	   BDDriver.bd = bd;
	   try {
		Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
		return false;
	}
	   try {
		   if(conn != null && !conn.isClosed()) conn.close();
		conn = DriverManager.getConnection("jdbc:postgresql://"+BDDriver.link+":"+BDDriver.port+"/" + BDDriver.bd, BDDriver.username, BDDriver.password);
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
            sqlQuery.append("SELECT * FROM criar_gestor(?, ? , ?, 0 , ?)");  		// 4 era o 0
            PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());		//
            //EstadoConta estado = EstadoConta.por_registar;
            ps.clearParameters();													//
            ps.setString(1, nome1);			 								 		//funcao para criar gestor base de dados com inserção de variaveis do u1
            ps.setString(2, email1);         								 		//
            ps.setString(3, password1);  										    //
            //ps.setObject(4, estado);
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
            	if(GestorContas.nifVal(nif1) == false) {
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
            	if(GestorContas.nifVal(nif1) == false) {
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
   
   

   
   
   

 	
 	public static Utilizador[] listarUtilizadores() {
 		ArrayList<Utilizador> utilizadorNovo = new ArrayList<Utilizador>();
 		
		try {
			
			StringBuffer sqlQuery = new StringBuffer();
			sqlQuery.append("SELECT * FROM listar_gestores()");
	        PreparedStatement ps1 = conn.prepareStatement(sqlQuery.toString());
	        ps1.clearParameters();
	        
	        
	        
	        ResultSet rs = ps1.executeQuery();
	        //int contador=0;
	        //String user[] = null;
	        //Utilizador[] utilizadorBuffer = new Utilizador[20];
	        while(rs.next()) {
	        	//user[contador] = rs.getString(5);
	        	int idUser = rs.getInt(1);
	        	String maill = rs.getString(3);
	        	String pass = rs.getString(4);
	        	int estado = rs.getInt(5);
	        	String user = rs.getString(6);
	        	String nome = rs.getString(7);
	        	
	        	
	        	//String teste = rs.getString(7);w
	        	utilizadorNovo.add(new Utilizador(idUser,user, pass, nome, EstadoConta.intToEstado(estado), maill, null));
	        	//System.out.println(teste);
	        	//utilizadorBuffer[contador] = new Utilizador(idUser,user, pass, nome, EstadoConta.ativos, maill, null);
	        	//Utilizador utilizadorNovo = new Utilizador(idUser,user, pass, nome, EstadoConta.ativos, maill, null);
	        	
	        	//contador++;
	        	
	        	
	        	//System.out.println(pass);
	        	
	        }
	        
	        ps1.close();
	        
	        StringBuffer sqlQuery1 = new StringBuffer();
			sqlQuery1.append("SELECT * FROM listar_autores()");
	        PreparedStatement ps2 = conn.prepareStatement(sqlQuery1.toString());
	        ps2.clearParameters();
	        
	        ResultSet rs2 = ps2.executeQuery();
	        
	        while(rs2.next()) {
	        	//user[contador] = rs.getString(5);
	        	//int idUser = rs2.getInt(1);
	        	String user = rs2.getString(6);
	        	String pass = rs2.getString(4);
	        	String maill = rs2.getString(3);
	        	String estado = rs2.getString(5);
	        	String nome = rs2.getString(7);
	        	
	        	String nif = rs2.getString(9);
	        	String telefone = rs2.getString(10);
	        	String data = rs2.getString(12);
	        	
	        	//System.out.println(nif);
	        	//if(user.equals(login1) && pass.equals(password1)) {
	        		//System.out.println("Bem-vindo " + login1);
	        	utilizadorNovo.add(new Autor(0,user, pass, nome, EstadoConta.ativos, maill, null, nif, null, null));
	        	//utilizadorBuffer[contador] = new Autor(0,user, pass, nome, EstadoConta.ativos, maill, null, nif, null, null);
	        		//ps1.close();
	        		//return utilizadorNovo;
	        	//}
	        	
	        	//contador++;
	        }
	        ps2.close();
	        
	        StringBuffer sqlQuery2 = new StringBuffer();
			sqlQuery2.append("SELECT * FROM listar_revisores()");
	        PreparedStatement ps3 = conn.prepareStatement(sqlQuery2.toString());
	        ps3.clearParameters();
	        
	        ResultSet rs3 = ps3.executeQuery();
	        
	        while(rs3.next()) {
	        	//user[contador] = rs.getString(5);
	        	//int idUser = rs2.getInt(1);
	        	String user = rs3.getString(6);
	        	String pass = rs3.getString(4);
	        	String maill = rs3.getString(3);
	        	String estado = rs3.getString(5);
	        	String nome = rs3.getString(7);
	        	
	        	String nif = rs3.getString(9);
	        	String telefone = rs3.getString(10);
	        	
	        	//System.out.println(nif);
	        	utilizadorNovo.add(new Revisor(0,user, pass, nome, EstadoConta.ativos, maill, null, nif, null, null));
	        	//utilizadorBuffer[contador] = new Revisor(0,user, pass, nome, EstadoConta.ativos, maill, null, nif, null, null);
	        	//if(user.equals(login1) && pass.equals(password1)) {
	        		
	        		
	        		//Autor utilizadorNovo = new Autor(0,login1, password1, nome, EstadoConta.ativos, maill, null);
	        		//ps1.close();
	        		//return utilizadorNovo;
	        	//}
	        	
	        	
	        }
	        
	        
	        ps3.close();
	        //return utilizadorBuffer;
	        
	        
	        
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilizadorNovo.toArray(new Utilizador[0]);
		
		
	}
 	

 	
 	//GestorContas e recebe de listarUtilizadores
 	public static Utilizador encontrarUtilizador(String login1, String password1) { //verifica se existe esse utilizador na base de dados em geral
		   Utilizador[] utilizadores = listarUtilizadores();
		   
		   for(int i = 0; i<utilizadores.length; i++) {
			   if(utilizadores[i].getLogin() == login1 && utilizadores[i].getPassword() == password1) {
				   return utilizadores[i];
				   //System.out.println(BDDriver.listarUtilizadores()[i].getLogin());
			   } 
			   
			
		   }
	        	   
		   return null;
	   }

 	public static Revisao[] listarRevisoes() {
 		ArrayList<Revisao> revisoes = new ArrayList<Revisao>();
 		try {
 		PreparedStatement ps = conn.prepareStatement("SELECT * FROM listar_revisoes()");
	    ResultSet rs = ps.executeQuery();
	    int revisaoID = rs.getInt(5);
	    int revisorResID = rs.getInt(6);
	    int obraID = rs.getInt(7);
	    int gestorID = rs.getInt(9);
	    
	    Revisao rev = new Revisao(
	    		revisaoID,
	    		obraID,
	    		gestorID,
	    		revisorResID,
	    		rs.getString(3),
	    		rs.getDate(2),
	    		rs.getDate(4),
	    		null,
	    		null,
	    		rs.getDouble(1),
	    		null,
	    		null,
	    		EstadoRevisao.intToEstado(rs.getInt(8)));
	    ps.close();
	    
	    
	    //Revisores Recusados
	    ps = conn.prepareStatement("SELECT * FROM listar_revisor_recusado_revisao(?)");
	    ps.setInt(1, revisaoID);
	    rs = ps.executeQuery();
	    ArrayList<Integer> revisores = new ArrayList<Integer>();
	    while(rs.next()) {
	    revisores.add(rs.getInt(1));
	    }
	    rev.setRevisoresRec(revisores.toArray(new Integer[0]));
	    ps.close();
	    
	    //Observacoes
	    ps = conn.prepareStatement("SELECT * FROM listar_observacao_de_revisao(?)");
	    ps.setInt(1, revisaoID);
	    rs = ps.executeQuery();
	    
	    ArrayList<String> observacoes = new ArrayList<String>();
	    while(rs.next()) {
	    	observacoes.add(rs.getString(3));
	    }
	    rev.setObservacoes(observacoes.toArray(new String[0]));
	    ps.close();
	    
	    //Anotacoes
	    ps = conn.prepareStatement("SELECT * FROM listar_anotacao_de_revisao(?)");
	    ps.setInt(1, revisaoID);
	    rs = ps.executeQuery();
	    
	    ArrayList<Anotacao> anotacoes = new ArrayList<Anotacao>();
	    while(rs.next()) {
	    	anotacoes.add(new Anotacao(
	    			rs.getInt(1),
	    			rs.getString(4),
	    			rs.getInt(5),
	    			rs.getInt(6),
	    			rs.getDate(3)
	    			));
	    }
	    rev.setAnotacoes(anotacoes.toArray(new Anotacao[0]));
	    ps.close();
	    
	    //Licensas
	    ps = conn.prepareStatement("SELECT * FROM listar_licensas_de_revisao(?);");
	    ps.setInt(1, revisaoID);
	    rs = ps.executeQuery();
	    ArrayList<Licensa> licensas = new ArrayList<Licensa>();
	    while(rs.next()) {
	    	licensas.add(new Licensa(
	    			rs.getInt(1),
	    			rs.getInt(2),
	    			rs.getString(3),
	    			rs.getInt(4),
	    			rs.getDate(5)
	    			));
	    }
	    rev.setLicensas(licensas.toArray(new Licensa[0]));
	    
	    revisoes.add(rev);
	    
	    
 		}
 		catch(Exception e) {
 			e.printStackTrace();
 		}
 		
 		return revisoes.toArray(new Revisao[0]);
 	}
   
   
   public static void fecharConexao() {
	   try {
		if(conn != null && !conn.isClosed()) {
			   try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   }
	} catch (SQLException e) {
		e.printStackTrace();
	}
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
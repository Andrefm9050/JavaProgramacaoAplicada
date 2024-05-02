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
import gestao.Notificacao;
import pastaPrincipal.Main;
import users.Autor;
import users.EstadoConta;
import users.Gestor;
import users.GestorContas;
import users.Revisor;
import users.UniqueUtilizador;

import users.Revisor;

import users.Utilizador;

/**
 * Classe responsavel para separar a conexao da base de dados da aplicacao
 * @author Andre Rios, Andre Mendes
 */
public class BDDriver {
   static Connection conn = null;
   
   /**
    * Esta funcao disponibiliza um menu para editar o ficheiro de Properties, se esse ficheiro nao existir um e criado
    */
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

   /**
    * 
    * @param caminho do ficheiro de configuração
    * @return true se a configuração e conexao foi feita com sucesso
    */
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
   
   public static boolean adicionarLicensaRevisao(Licensa l, Revisao r) {
	   try {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM criar_licensa_numa_revisao(?,?,?,?)");
		ps.setInt(1, r.getRevisaoID());
		ps.setString(2,l.getNomeLicensa());
		ps.setInt(3, l.getNumeroSerie());
		ps.setDate(4, l.getExpiracao());
		ps.execute();
		
		ps.close();
		
		return true;
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   
	   
	   return false;
   }
   
   public static boolean adicionarNotificacao(Notificacao n) {
	   try {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM criar_notificacao(?,?)");
		ps.setInt(1, n.getUtilizadorID());
		ps.setString(2, n.getDescricao());
		
		ps.execute();
		ps.close();
		
		return true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   
	   
	   return false;
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
         if (u1 instanceof Gestor) {
            sqlQuery.append("SELECT * FROM criar_gestor(?, ? , ?, ? , ?)");  		// 4 era o 0
            PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());		//
            //EstadoConta estado = EstadoConta.por_registar;
            ps.clearParameters();													//
            ps.setString(1, nome1);			 								 		//funcao para criar gestor base de dados com inserção de variaveis do u1
            ps.setString(2, email1);         								 		//
            ps.setString(3, password1);  										    //
            ps.setInt(4, EstadoConta.estadoToInt(u1.getEstado()));
            ps.setString(5, login1);
            ResultSet rs = ps.executeQuery();
            rs.next();
            idUser = rs.getInt(1);      //recebe o id do utilizador proveniente da base de dados
            //System.out.println(idUser);
            
            ps.close();
            return idUser;
         }
         else if(u1 instanceof Autor) {
        	 Autor autor = (Autor)u1;
            sqlQuery.append("SELECT * FROM criar_autor(?, ?, ? , ? , ?, ?, ?, ?, ?, ?)");
            PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
            ps.clearParameters();
            ps.setString(1, nome1);
            ps.setString(2, email1);
            ps.setString(3, password1);
            ps.setInt(4, EstadoConta.estadoToInt(u1.getEstado()));
            ps.setString(5, login1);
            ps.setString(6, autor.getMorada());
            ps.setString(7, autor.getNif());
            ps.setString(8, autor.getTelefone());
            ps.setDate(9, autor.getDataInicioAtividade());
            ps.setInt(10, EstiloLiterario.estiloToInt(autor.getEstilo()));
            ResultSet rs = ps.executeQuery();
            rs.next();
            idUser = rs.getInt(1);
            //System.out.println(idUser);
            ps.close();
            return idUser;
         }
         else if(u1 instanceof Revisor) {
        	Revisor revisor = (Revisor)u1;
            sqlQuery.append("SELECT * FROM criar_revisor(?, ? , ?, ? , ?, ?, ?, ?, ?, ?)");
            PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
            ps.clearParameters();
            ps.setString(1, nome1);
            ps.setString(2, email1);
            ps.setString(3, password1);
            ps.setInt(4, EstadoConta.estadoToInt(u1.getEstado()));
            ps.setString(5, login1);
            ps.setString(6, revisor.getMorada());
            ps.setString(7, revisor.getNif());
            ps.setString(8, revisor.getTelefone());
            ps.setString(9, revisor.getFormacao());
            ps.setString(10, revisor.getArea().toString());
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
	        	int idGestor = rs.getInt(2);
	        	String maill = rs.getString(3);
	        	String pass = rs.getString(4);
	        	int estado = rs.getInt(5);
	        	String user = rs.getString(6);
	        	String nome = rs.getString(7);
	        	
	        	
	        	//String teste = rs.getString(7);w
	        	utilizadorNovo.add(new Gestor(idGestor,idUser,user, pass, nome, EstadoConta.intToEstado(estado), maill, null));
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
	        	int idUser = rs2.getInt(1);
	        	int idAutor = rs2.getInt(2);
	        	String user = rs2.getString(6);
	        	String pass = rs2.getString(4);
	        	String maill = rs2.getString(3);
	        	int estado = rs2.getInt(5);
	        	String nome = rs2.getString(7);
	        	String nif = rs2.getString(9);
	        	String telefone = rs2.getString(10);
	        	Date data = rs2.getDate("datainicio");
	        	int estilo = rs2.getInt("estilo_literario");
	        	String morada = rs2.getString("morada");
	        	
	        	//System.out.println(idUser);
	        	//if(user.equals(login1) && pass.equals(password1)) {
	        		//System.out.println("Bem-vindo " + login1);
	        	utilizadorNovo.add(new Autor(idAutor, idUser,user, pass, nome, EstadoConta.intToEstado(estado), maill, null, nif, telefone,morada ,data,EstiloLiterario.intToEstilo(estilo)));
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
	        	int idUserN = rs3.getInt(1);
	        	int idRevisor = rs3.getInt(2);
	        	String user = rs3.getString(6);
	        	String pass = rs3.getString(4);
	        	String maill = rs3.getString(3);
	        	int estado = rs3.getInt(5);
	        	String nome = rs3.getString(7);
	        	String formacao = rs3.getString("formacao_academica");
	        	String nif = rs3.getString(9);
	        	String telefone = rs3.getString(10);
	        	String area = rs3.getString("especializacao");
	        	String morada = rs3.getString("morada");
	        	
	        	//System.out.println(nif);
	        	utilizadorNovo.add(new Revisor(idRevisor,
	        			idUserN,
	        			user,
	        			pass,
	        			nome,
	        			EstadoConta.intToEstado(estado),
	        			maill,
	        			null,
	        			nif,
	        			telefone,
	        			morada,
	        			formacao,
	        			area));
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
 	public static  Utilizador encontrarUtilizador(String login1, String password1) { //verifica se existe esse utilizador na base de dados em geral
		
		Utilizador[] utilizadores = listarUtilizadores();
		
		   
		   for(int i = 0; i<utilizadores.length; i++) {
			   
			   if(utilizadores[i].getLogin().contentEquals(login1) && utilizadores[i].getPassword().contentEquals(password1)) {
				   return utilizadores[i];
				   
			   } 
			   
			
		   }
	        	   
		   return null;
	   }

 	public static Revisao[] listarRevisoes() {
 		ArrayList<Revisao> revisoes = new ArrayList<Revisao>();
 		try {
 		PreparedStatement ps = conn.prepareStatement("SELECT * FROM listar_revisoes()");
	    ResultSet rs = ps.executeQuery();
	    Obra[] obras = BDDriver.listarObras();
	    Revisor[] revisores = GestorContas.listarRevisores();
	    while(rs.next()) {
	    int revisaoID = rs.getInt("id_revisao");
	    int revisorResID = rs.getInt(5);
	    int obraID = rs.getInt(6);
	    int gestorID = rs.getInt(7);
	    
	    Obra obra = null;
	    
	    for(var o : obras) {
	    	if(o.getObraId() == obraID) {
	    		obra = o;
	    	}
	    }
	    Revisor revisor = null;
	    for(var rev : revisores) {
	    	if(rev.getIdRevisor() == revisorResID) {
	    		revisor = rev;
	    	}
	    }
	    
	    Revisao rev = new Revisao(
	    		revisaoID,
	    		obra,
	    		gestorID,
	    		revisor,
	    		rs.getString(3),
	    		rs.getDate(2),
	    		rs.getInt(9),
	    		null,
	    		null,
	    		rs.getDouble(1),
	    		null,
	    		null,
	    		null,
	    		null, EstadoRevisao.intToEstado(rs.getInt(8)));
	    
	    
	    //Revisores Recusados
	    PreparedStatement localps = conn.prepareStatement("SELECT * FROM listar_revisor_recusado_revisao(?)");
	    localps.setInt(1, revisaoID);
	    ResultSet localrs = localps.executeQuery();
	    ArrayList<Revisor> revisores1 = new ArrayList<Revisor>();
	    while(localrs.next()) {
	    	int revID = localrs.getInt(1);
	    	for(Revisor rev2 : revisores) {
	    		if(rev2.getIdRevisor() == revID)
	    		revisores1.add(rev2);
	    	}
	    }
	    rev.setRevisoresRec(revisores1.toArray(new Revisor[0]));
	    revisores1.clear();
	    localps.close();
	    
	    //Revisores Confirmados
	    localps = conn.prepareStatement("SELECT * FROM listar_revisores_revisao(?)");
	    localps.setInt(1, revisaoID);
	    localrs = localps.executeQuery();
	    ArrayList<Revisor> naoconfirm = new ArrayList<Revisor>();
	    ArrayList<Revisor> confirm = new ArrayList<Revisor>();
	    while(localrs.next()) {
	    	int revID = localrs.getInt(1);
	    	boolean confirmed = localrs.getBoolean(3);
	    	for(Revisor rev3 : revisores) {
	    		if(rev3.getIdRevisor() == revID) {
		    		if(confirmed) {
		    		confirm.add(rev3);
			    	}
			    	else {
			    		naoconfirm.add(rev3);
			    	}
	    		}
	    	}
	    	
	    }
	    rev.setRevisoresConfirmados(confirm.toArray(new Revisor[0]));
	    rev.setRevisoresNaoConfirmados(naoconfirm.toArray(new Revisor[0]));
	    localps.close();
	    
	    //Observacoes
	    localps = conn.prepareStatement("SELECT * FROM listar_observacao_de_revisao(?)");
	    localps.setInt(1, revisaoID);
	    localrs = localps.executeQuery();
	    
	    ArrayList<String> observacoes = new ArrayList<String>();
	    while(localrs.next()) {
	    	observacoes.add(localrs.getString(3));
	    }
	    rev.setObservacoes(observacoes.toArray(new String[0]));
	    localps.close();
	    
	    //Anotacoes
	    localps = conn.prepareStatement("SELECT * FROM listar_anotacao_de_revisao(?)");
	    localps.setInt(1, revisaoID);
	    localrs = localps.executeQuery();
	    
	    ArrayList<Anotacao> anotacoes = new ArrayList<Anotacao>();
	    while(localrs.next()) {
	    	anotacoes.add(new Anotacao(
	    			localrs.getInt(1),
	    			localrs.getString(4),
	    			localrs.getInt(5),
	    			localrs.getInt(6),
	    			localrs.getDate(3)
	    			));
	    }
	    rev.setAnotacoes(anotacoes.toArray(new Anotacao[0]));
	    localps.close();
	    
	    //Licensas
	    localps = conn.prepareStatement("SELECT * FROM listar_licensas_de_revisao(?);");
	    localps.setInt(1, revisaoID);
	    localrs = localps.executeQuery();
	    ArrayList<Licensa> licensas = new ArrayList<Licensa>();
	    while(localrs.next()) {
	    	licensas.add(new Licensa(
	    			localrs.getInt(1),
	    			localrs.getInt(2),
	    			localrs.getString(3),
	    			localrs.getInt(4),
	    			localrs.getDate(5)
	    			));
	    }
	    rev.setLicensas(licensas.toArray(new Licensa[0]));
	    localps.close();
	    
	    revisoes.add(rev);
	    
	
	    
 		}
	    ps.close();
 		}
 		catch(Exception e) {
 			e.printStackTrace();
 		}
 		
 		return revisoes.toArray(new Revisao[0]);
 	}
 	
 	public static boolean setUtilizadorEstado(int id, int estado) {
 		try {
 	 		PreparedStatement ps = conn.prepareStatement("SELECT * FROM set_estado_utilizador(?, ?)");
 		    
 		    ps.setInt(1, id);
 		    ps.setInt(2, estado);
 		    
 		    ResultSet rs = ps.executeQuery();
 		    rs.next();
 		    ps.close();
 		    
 		    return true;
 	 		}
 	 		catch(Exception e) {
 	 			e.printStackTrace();
 	 		}
		return false;
 	}
 	
 	public static int adicionarObra(Obra obra) {
        
        
        try {
        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM criar_obra(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
    	  	ps.setInt(1, obra.getAutorID());
            ps.setInt(2, obra.getIsbn()); //isbn
            ps.setInt(3, obra.getNumeroPaginas());  								 		
            ps.setInt(4, obra.getNumeroPalavras());  										    
            ps.setString(5, obra.getTitulo());
            ps.setString(6, obra.getSubTitulo());
            ps.setString(7, TipoPublicacao.tipoToString(obra.getTipoPublicacao()));
            ps.setInt(8, EstiloLiterario.estiloToInt(obra.getEstiloLiterario()));
            ps.setInt(9, obra.getNumeroEdicao());
            ps.setDate(10, null);
            ps.setDate(11, null);
        	ps.execute();  
        	ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
 		
 		
 		
 		return 0;
 	}
 	
 	
 	public static Obra[] listarObras() {
 		
 		ArrayList<Obra> obraNova = new ArrayList<Obra>();
    	//String teste = rs.getString(7);w
    	//utilizadorNovo.add(new Gestor(idGestor,idUser,user, pass, nome, EstadoConta.intToEstado(estado), maill, null));
 		
		
		try {
			StringBuffer sqlQuery = new StringBuffer();
    		sqlQuery.append("SELECT * FROM listar_obras()");  
			PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
			ps.clearParameters();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int idObra = rs.getInt(1); //id obra
				int idAutor = rs.getInt(2); //id autor
				Date dataAprovacao = rs.getDate(3); //data
				Date dataSubmissao = rs.getDate(4);
				int isbn = rs.getInt(5);
				int nEdicao = rs.getInt(6);
				int nPaginas = rs.getInt(7);
				int nPalavras = rs.getInt(8);
				String subTitulo = rs.getString(9);
				String titulo = rs.getString(10);
				String tipoPubli = rs.getString(11);
				String estiloLiterario1 = rs.getString(12);
				//System.out.println(estiloLiterario1);
				String autorNome = null;
				Utilizador[] utilizadorBuffer = listarUtilizadores();
				for(int i = 0; i<utilizadorBuffer.length; i++) {
					if(utilizadorBuffer[i] instanceof Autor) {
						if(((Autor) utilizadorBuffer[i]).getIdAutor() == idAutor ) {
							autorNome = utilizadorBuffer[i].getLogin();
						}
						
					}
					
				}
				obraNova.add(new Obra(idObra, autorNome,idAutor, titulo, subTitulo, EstiloLiterario.stringToEstilo(estiloLiterario1), 
						TipoPublicacao.stringToTipo(tipoPubli),
						nPaginas, nPalavras, isbn, nEdicao, dataSubmissao, dataAprovacao));
				
				//int obraId, String autor, String titulo, String subTitulo, EstiloLiterario estiloLiterario,
				//TipoPublicacao tipoPublicacao, int numeroPaginas, int numeroPalavras, int isbn, int numeroEdicao,
				//Date dataSubmissao, Date dataAprovacao
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 		
 		
 		
		return obraNova.toArray(new Obra[0]);
 	}
 	
 	
 	public static void alterarISBN(int isbnNumber, int idObra) {
 		try {
			StringBuffer sqlQuery = new StringBuffer();
    		sqlQuery.append("SELECT * FROM definir_obra_isbn(?,?)");  
			PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
			ps.clearParameters();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 	public static boolean editarUtilizador(int id,String user,String password, String nome, String email,EstadoConta estado, String nif, String telefone, String morada) {
 		
 		try {
 			PreparedStatement ps = conn.prepareStatement("CALL editar_utilizador(?,?,?,?,?,?,?,?,?)");
 			ps.setInt(1, id);
 			ps.setString(2, user);
 			ps.setString(3, password);
 			ps.setString(4, nome);
 			ps.setString(5, email);
 			ps.setInt(6, EstadoConta.estadoToInt(estado));
 			ps.setString(7, nif);
 			ps.setString(8, telefone);
 			ps.setString(9, morada);
 			
 			ps.execute();
 			ps.close();
 			return true;
 		}
 		catch(Exception e) {
 			return false;
 		}
 		
 	}
 	
 	public static void adicionarRevisao(int nSerie, int idObra, int idGestor ) {
 
 		
 		
 		
 		try {
        	//Revisao revisao = (Autor) GestorContas.pesquisarUtilizadoresUserName(user);
        	
        	StringBuffer sqlQuery = new StringBuffer();
        	sqlQuery.append("SELECT * FROM criar_revisao(?, ?, ?)");  
			PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
			 ps.clearParameters();													
	            ps.setInt(1, nSerie);
	            ps.setInt(2, idObra); //isbn
	            ps.setInt(3, idGestor);  								 		
	            //idGestor null por agora
	            ResultSet rs = ps.executeQuery();
	            rs.next();
	          ps.close();
	         
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
 		
 		
 	
 	}
 	
 	public static boolean adicionarAnotacaoRevisao(Revisao rev,Anotacao a) {
 		
 		try {
 			
 			PreparedStatement ps = conn.prepareStatement("SELECT * FROM criar_anotacao(?,?,?,?)");
 			ps.setInt(1, rev.getRevisaoID());
 			ps.setString(2,a.getDescricao());
 			ps.setInt(3,a.getPagina());
 			ps.setInt(4, a.getParagrafo());
 			
 			ps.execute();
 			ps.close();
 			
 			return true;
 		}catch(SQLException e) {
 			e.printStackTrace();
 		}
 		
 		
 		return false;
 	}
 	
 	public static boolean adicionarObservacaoRevisao(int revID, String obs) {
 		
 		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM criar_observacao(?,?)");
			ps.setInt(1,revID);
			ps.setString(2, obs);
			ps.execute();
			ps.close();
			
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		
 		
 		return false;
 	}
 	
 	
 	
 	public static boolean definirRevisorResponsavel(int idRevisao, int idRevisor) {
 		try {
 			PreparedStatement ps = conn.prepareStatement("SELECT * FROM definir_revisor_revisao(?,?)");
 			ps.setInt(1, idRevisao);
 			ps.setInt(2, idRevisor);
 			ResultSet rs = ps.executeQuery();
 			rs.next();
 			return rs.getBoolean(1);
 		}
 		catch(SQLException e) {
 			return false;
 		}
 	}
 	
 	public static void confirmarRevisorResponsavel(int idRevisao, boolean confirm) {
 		try {
        	//Revisao revisao = (Autor) GestorContas.pesquisarUtilizadoresUserName(user);
        	
        	StringBuffer sqlQuery = new StringBuffer();
        	sqlQuery.append("CALL definir_confirmar_revisor_resp_revisao(?, ?)");  
			PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
			 ps.clearParameters();													
	            ps.setInt(1, idRevisao);
	            ps.setBoolean(2, confirm); //isbn
	          ps.execute();  
	          ps.close();
	         
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
 	}
 	
 	public static void confirmarRevisorNormal(int idRevisao,int idRevisor, boolean confirm) {
 		try {
        	//Revisao revisao = (Autor) GestorContas.pesquisarUtilizadoresUserName(user);
        	
        	StringBuffer sqlQuery = new StringBuffer();
        	sqlQuery.append("CALL definir_confirmar_revisor_revisao(?, ?, ?)");  
			PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
			 ps.clearParameters();													
	            ps.setInt(1, idRevisao);
	            ps.setInt(2, idRevisor);
	            ps.setBoolean(3, confirm); //isbn
	          ps.execute();  
	          ps.close();
	         
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 	public static boolean setPagarRevisao(int revID, float preco) {
 		
 		try {
			PreparedStatement ps = conn.prepareStatement("CALL pagar_revisao(?,?)");
			ps.setInt(1, revID);
			ps.setFloat(2, preco);
			ps.execute();
			ps.close();
			
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
 		return false;
 	}
 	
 	public static boolean setAdicionarTempoRevisao(int idRevisao, int minutos) {
 		
 		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM definir_adicionar_tempo_revisao(?,?)");
			ps.setInt(1, idRevisao);
			ps.setInt(2, minutos);
			ps.execute();
			ps.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		
 		
 		return false;
 	}
 	
 	
 	public static void adicionarRevisor(int idRevisao, int idRevisor) {
 		try {
        	//Revisao revisao = (Autor) GestorContas.pesquisarUtilizadoresUserName(user);
        	
        	StringBuffer sqlQuery = new StringBuffer();
        	sqlQuery.append("SELECT * FROM adicionar_revisor_revisao(?, ?)");  
			PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
			 ps.clearParameters();													
	            ps.setInt(1, idRevisao);
	            ps.setInt(2, idRevisor);
	           
	            ResultSet rs = ps.executeQuery();
	            rs.next();
	          ps.close();
	         
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
 	}
 	
 	
 	public static void atualizarEstadoRevisao(int idRevisao, int estado) {
 		try {
        	//Revisao revisao = (Autor) GestorContas.pesquisarUtilizadoresUserName(user);
        	
        	StringBuffer sqlQuery = new StringBuffer();
        	sqlQuery.append("CALL definir_estado_revisao(?, ?)");  
			PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
			 ps.clearParameters();													
	            ps.setInt(1, idRevisao);
	            ps.setInt(2, estado);
	           
	            ResultSet rs = ps.executeQuery();
	            rs.next();
	          ps.close();
	         
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
 	}
 	
 	
 	public static void atualizarIdGestorRevisao(int idRevisao, int idGestor) {
 		try {
        	//Revisao revisao = (Autor) GestorContas.pesquisarUtilizadoresUserName(user);
        	
        	StringBuffer sqlQuery = new StringBuffer();
        	sqlQuery.append("CALL definir_gestor_revisao(?, ?)");  
			PreparedStatement ps = conn.prepareStatement(sqlQuery.toString());
			 ps.clearParameters();													
	            ps.setInt(1, idRevisao);
	            ps.setInt(2, idGestor);
	          ps.close();
	         
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
 	}
 	
 	public static Notificacao[] listarNotificacoes(int userID) {
 		ArrayList<Notificacao> result = new ArrayList<Notificacao>();
 		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM listar_notificacoes(?)");
			ps.setInt(1, userID);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Notificacao not = new Notificacao(
						rs.getInt(3),
						rs.getInt(4),
						rs.getString("descricao"),
						rs.getBoolean("lida"));
				result.add(not);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
		return result.toArray(new Notificacao[0]);
 		
 	}
 	
 	public static boolean setUtilizadorPassword(int idUser, String password) {
 		
 		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM set_password_utilizador(?,?)");
			ps.setInt(1, idUser);
			ps.setString(2, password);
			
			ps.execute();
			ps.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
 		
 		
 		
 		
 		return false;
 	}
 	
 	
 	public static boolean setUtilizadorNome(int idUser,String nome) {
 		try {
 			
 			PreparedStatement ps = conn.prepareStatement("SELECT * FROM set_nome_utilizador(?,?)");
 			ps.setInt(1, idUser);
 			ps.setString(2, nome);
 			ps.execute();
 			ps.close();
 			
 			
 			return true;
 		} catch(SQLException e) {
 			e.printStackTrace();
 		}
 		
 		
 		return false;
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
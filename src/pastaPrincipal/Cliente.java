package pastaPrincipal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.Locale;
import java.util.Scanner;

import sistema.Anotacao;
import sistema.BDDriver;
import sistema.EstadoRevisao;
import sistema.EstiloLiterario;
import sistema.Obra;
import sistema.Revisao;
import sistema.TipoPublicacao;
import users.Autor;
import users.EspecializacaoArea;
import users.EstadoConta;
import users.Gestor;
import users.GestorContas;
import users.Revisor;
import users.Utilizador;

/**
 * Classe responsavel pela parte da aplicacao CLiente
 * @author Andre Martins
 */
	
	
public class Cliente {
	static Socket socket  = null;	
	static PrintWriter out      = null;
	static BufferedReader in    = null;
	//static String serverIP = "192.168.43.155";
	
	//função criar conexão 
	/**
	    * Esta funcao permite ao utilizador alterar os dados tanto do porto como do IP do servidor
	    */
	static boolean configurarConexao() {
		int serverPort = 0;
		String serverIP = null;
		String verificacao = lerDados("Deseja alterar a configuração inicial da ligação?(s/n): ");
		if(verificacao.contentEquals("s") == true) {
			serverPort = lerDadosInt("Insira o valor correspondente ao porto: ");
			serverIP = lerDados("Insira o IP do servidor(ex: yyy.yyy.yyy.yyy): ");
		}else {
			serverPort = 5001;
			serverIP = "192.168.43.155";
		}
		
		
		try {
			socket = new Socket(serverIP, serverPort);
			System.out.println("## Client connected to server "+serverIP+":"+serverPort);
			
			out    = new PrintWriter(socket.getOutputStream(), true);
			in     = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String message = in.readLine();
			out.println("<cliente> <hello>;");
			
			String ack = in.readLine();
			
			return ack.contentEquals("<server> <ack>;");
			
		}catch(Exception e) {
			//System.out.println(e);
		}
		return false;
	}
	
	/**
	    * Esta funcao permite que, o cliente ao receber informacao do servidor responda com <login> <ack>;
	    */
	static String readLine1() {
		
		try {
			String message = in.readLine();
			out.println("<cliente> <ack>;");
			String ack = in.readLine();
			
			if(ack.contentEquals("<server> <ack>;")) {
				System.out.println(ack);
				return message;
			}else {
				System.out.println("Não foi recebida qualquer tipo de resposta!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	/**
	 * 
	 * @param username1 - username/login da conta
	 * @param password1 - password da conta
	 * @return boolean, true se não ocorrer nenhum erro, false se ocorrer algum erro
	 */
	static boolean autenticacaoCliente(String username1, String password1) { //16 cliente
		//int serverPort = 7777;
		
		try {
			//socket = new Socket(serverIP, serverPort);
			
			out.println("<cliente> <autenticar> <"+username1+","+password1+">;");
			//String message = in.readLine();
			
			//String readline()

			
			String ack = readLine1();
			
			if(!ack.contentEquals("<server> <autenticar> <success>;")) {
				return false;
				//Close connection and application
			}
			else {
				return true;
			}
		}
		catch(Exception e) {
			
		}
		return false;
		
	}
	/**
	 * Esta funcao permite ao utilizador consultar dados pessoais atraves de comunicacao com o servidor
	 * E recebida a informacao atraves de uma string e depois atraves de split's e possível retirar e armazenar os dados em variavéis do objeto Utilizador
	 * @return Utilizador com os dados pessoais do cliente
	 */
	public static Utilizador consultaDadosPessoaisCliente() { //17 cliente
		int serverPort = 7777;
		
		try {
			//socket = new Socket(serverIP, serverPort);
			
			out.println("<cliente> <info>;");

			//<login> <info> <username,password,nome,email,estado,nif,telefone,morada>
			String ack = readLine1();
			System.out.println(ack);
			String info = ack.split("<")[3].split(">")[0].split(",")[0];
			
			
			System.out.println(info);
			//System.out.println(userName);
			//System.out.println(pass);
			//System.out.println(nome);
			String userName = ack.split("<")[3].split(">")[0].split(",")[1];
			String pass = ack.split("<")[3].split(">")[0].split(",")[2];
			String nome = ack.split("<")[3].split(">")[0].split(",")[3];
			String email = ack.split("<")[3].split(">")[0].split(",")[4];
			String estadoConta =  ack.split("<")[3].split(">")[0].split(",")[5];
			
			if(info.contentEquals("Gestor")) {
				Gestor novoGestor = new Gestor(0, 0, userName, pass, nome, EstadoConta.stringToEstado(estadoConta), email, null);
				return novoGestor;
			}else if(info.contentEquals("Autor")) {
				//login, pass, nome, email, estado, nif, telefone, morada, data e estilo
				
				
				SimpleDateFormat formatar = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
				String nif = ack.split("<")[3].split(">")[0].split(",")[6];
				String telefone = ack.split("<")[3].split(">")[0].split(",")[7];
				String morada = ack.split("<")[3].split(">")[0].split(",")[8];
				Date data = Date.valueOf(ack.split("<")[3].split(">")[0].split(",")[9]);
				
				String estilo = ack.split("<")[3].split(">")[0].split(",")[10];
				
				System.out.println(data);
				System.out.println(estilo);
				System.out.println(morada);
				
				//LocalDate date1 = LocalDate.parse(data, DateTimeFormatter.ISO_DATE);
				Autor novoAutor = new Autor(0, 0, userName, pass, nome, EstadoConta.stringToEstado(estadoConta), email, null, nif, telefone, morada, data, EstiloLiterario.stringToEstilo(estilo));
				return novoAutor;
			}else if(info.contentEquals(("Revisor"))){
				//Completar parecido a "Autor"
				//String nif = ack.split("<")[3].split(">")[0].split(",")[6];
				//String telefone = ack.split("<")[3].split(">")[0].split(",")[7];
				//String morada = ack.split("<")[3].split(">")[0].split(",")[8];
				String nif = ack.split("<")[3].split(">")[0].split(",")[6];
				String telefone = ack.split("<")[3].split(">")[0].split(",")[7];
				String morada = ack.split("<")[3].split(">")[0].split(",")[8];
				String areaEspecializado = ack.split("<")[3].split(">")[0].split(",")[9];
				String formacaoAcademica = ack.split("<")[3].split(">")[0].split(",")[10];
				//int idRevisor, int idUser,String login, String password, String nome, EstadoConta estado, String email, String tipo, String nif, String telefone, String morada
				Revisor novoRevisor = new Revisor(0, 0, userName, pass, nome, EstadoConta.stringToEstado(estadoConta), email, null, nif, telefone, morada, areaEspecializado, formacaoAcademica);
				return novoRevisor;
			}
			
			//Utilizador userLoginSEstado = new Utilizador();
			
			if(!ack.contentEquals("<server> <info> <success>;")) { //tipo primeiro e depois os restantes argumentos
				//return false;
				//Close connection and application
			}
			else {
				//return true;
			}
		}
		catch(Exception e) {
			
		}
		
		return null;
		
		//return false;
		
	}
	
	/**
	    * Esta funcao permite ao utilizador dados especificos da sua conta
	    * @param user - Utilizador que pretende alterar os dados
	    * @return Utilizador com os dados pessoais do cliente alterados
	    */
	public static Utilizador alterarDadosPessoais(Utilizador user) { //18 cliente
		int serverPort = 7777;
	
		try {
			if(user instanceof Gestor) {
				//fazer interface para utilizador escolher o que quer alterar e a nova alteração a fazer
				while(true) {
					
					String dadosAlterar = lerDados("Escolha o tipo de dado que pretende alterar(username, password, nome ou email): ");
					String novoDado = lerDados("Insira o valor/nome novo: ");
					//|| dadosAlterar.contentEquals("email") == true) 
					if(dadosAlterar.contentEquals("username") == true) {
						user.setLogin(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("password") == true) {
						user.setPassword(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("nome") == true) {
						user.setNome(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("email") == true) {
						user.setEmail(novoDado);
						break;
					}
					else {
						System.out.println("Selecione um tipo de dado válido! (username, password, nome ou email)");
					}
				//	String novoDado = lerDados("Insira o valor/nome novo: ");
					
					
				}
				
				out.println("<cliente> <update> <"+user.getLogin()+","+user.getPassword()+","+user.getNome()+","+user.getEmail()+
						","+EstadoConta.estadoToString(user.getEstado())+">;");
				//System.out.println("Bem-vindo " + login1);
				//Gestor.menuGestor((Gestor)userLoginSEstado);
			}
			else if(user instanceof Autor) {
				Autor userAutor = (Autor) user;
				while(true) {
					
					String dadosAlterar = lerDados("Escolha o tipo de dado que pretende alterar(username, password, nome, email, nif, telefone ou morada): ");
					String novoDado = lerDados("Insira o valor/nome novo: ");
					//|| dadosAlterar.contentEquals("email") == true) 
					if(dadosAlterar.contentEquals("username") == true) {
						userAutor.setLogin(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("password") == true) {
						userAutor.setPassword(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("nome") == true) {
						userAutor.setNome(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("email") == true) {
						userAutor.setEmail(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("nif") == true) {
						userAutor.setNif(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("telefone") == true) {
						userAutor.setTelefone(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("morada") == true) {
						userAutor.setMorada(novoDado);
						break;
					}
					else {
						System.out.println("Selecione um tipo de dado válido! (username, password, nome ou email)");
					}
				//	String novoDado = lerDados("Insira o valor/nome novo: ");
					
					
				}
				
				out.println("<cliente> <update> <"+userAutor.getLogin()+","+userAutor.getPassword()+","+userAutor.getNome()+","+userAutor.getEmail()+
						","+EstadoConta.estadoToString(userAutor.getEstado())+","+userAutor.getNif()+","+userAutor.getTelefone()+","+userAutor.getMorada()+">;");
				//System.out.println("Bem-vindo " + login1);
				//Autor.menuAutor((Autor)userLoginSEstado);
			}
			else if(user instanceof Revisor) {
				Revisor userRevisor = (Revisor) user;
				
				while(true) {
					
					String dadosAlterar = lerDados("Escolha o tipo de dado que pretende alterar(username, password, nome, email, nif, telefone ou morada): ");
					String novoDado = lerDados("Insira o valor/nome novo: ");
					//|| dadosAlterar.contentEquals("email") == true) 
					if(dadosAlterar.contentEquals("username") == true) {
						userRevisor.setLogin(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("password") == true) {
						userRevisor.setPassword(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("nome") == true) {
						userRevisor.setNome(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("email") == true) {
						userRevisor.setEmail(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("nif") == true) {
						userRevisor.setNif(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("telefone") == true) {
						userRevisor.setTelefone(novoDado);
						break;
					}else if(dadosAlterar.contentEquals("morada") == true) {
						userRevisor.setMorada(novoDado);
						break;
					}
					else {
						System.out.println("Selecione um tipo de dado válido! (username, password, nome ou email)");
					}
				//	String novoDado = lerDados("Insira o valor/nome novo: ");
					
					
				}
				
				out.println("<cliente> <update> <"+userRevisor.getLogin()+","+userRevisor.getPassword()+","+userRevisor.getNome()+","+userRevisor.getEmail()+
						","+EstadoConta.estadoToString(userRevisor.getEstado())+","+userRevisor.getNif()+"1000,"+userRevisor.getTelefone()+","+userRevisor.getMorada()+">;");
				//System.out.println("Bem-vindo " + login1);
				//Revisor.menuRevisor((Revisor)userLoginSEstado);
			}
			
			//out.println("<cliente> <update> <"+user.getLogin()+","+user.getPassword()+","+user.getNome()+","+user.getEmail()+
				//	","+EstadoConta.estadoToString(user.getEstado())+",");
			
			//<login> <info> <username,password,nome,email,estado,nif,telefone,morada>
			String ack = readLine1();
			System.out.println(ack);
			
			
			//Utilizador userLoginSEstado = new Utilizador();
			
			if(!ack.contentEquals("<server> <update> <ok>;")) { //tipo primeiro e depois os restantes argumentos
				//return false;
				System.out.println("Dados não foram alterados com sucesso!");
				//Close connection and application
			}
			else {
				System.out.println("Dados alterados com sucesso!");
				//return true;
			}
		}
		catch(Exception e) {
			
		}
		
		return null;
		
	}
	
	public static boolean inserirNovaObra(Autor userAutor) { //19 cliente
		int serverPort = 7777;
		
		try {
			String titulo = lerDados("Insira o titulo da obra: ");
			//String estiloLiterario = lerDados("Insira o seu estilo literario: ");
			String nPaginas = lerDados("Insira o numero de paginas da obra: ");
			String nPalavras = lerDados("Insira o numero de palavras da obra: ");
			String nEdicao = lerDados("Insira o numero da edicao: ");
			String tipoPubli = lerDados("Insira o tipo da obra (capa dura, de bolso e ebook): ");
			//String ISBN
			int isbn = GestorContas.isbnUnico();
			String isbnUnico1 = String.valueOf(isbn);
			//socket = new Socket(serverIP, serverPort);
			//Obra novaObra = new Obra(0, userAutor.getLogin(), 0, titulo);
			//System.out.println(userAutor.getEstilo());
			out.println("<cliente> <inserir> <obra> <"+titulo+","+EstiloLiterario.estiloToString(userAutor.getEstilo())+","+tipoPubli+","+nPaginas+","+nPalavras+","+isbnUnico1+","+nEdicao+">;");
			//String message = in.readLine();
			String ack = readLine1();
			System.out.println(ack);
			if(!ack.contentEquals("<server> <inserir> <obra> <ok>;")) {
				System.out.println("Obra não foi inserida com sucesso!");
				return false;
				//Close connection and application
			}
			else {
				System.out.println("Obra inserida com sucesso!");
				return true;
			}
		}
		catch(Exception e) {
			
		}
		return false;
		
		
		
		
		//return false;
		
	}
	
	public static boolean pesquisarObra(Autor user) { //20 cliente
		
		
		try {
			String tituloObra = lerDados("Insira o titulo da obra: ");
			//socket = new Socket(serverIP, serverPort);
			
			out.println("<cliente> <pesquisa> <obra> <"+tituloObra+">;");
			//String message = in.readLine();
			String ack = readLine1();
			String titulo = ack.split("<")[4].split(">")[0].split(",")[0];
			//System.out.println(titulo);
			String estiloLiterario1 = ack.split("<")[4].split(">")[0].split(",")[1];
			String tipo = ack.split("<")[4].split(">")[0].split(",")[2];
			String numPaginas = ack.split("<")[4].split(">")[0].split(",")[3];
			String numPalavras = ack.split("<")[4].split(">")[0].split(",")[4];
			String isbn =  ack.split("<")[4].split(">")[0].split(",")[5];
			String numEdicao =  ack.split("<")[4].split(">")[0].split(",")[6];
			Date dataSubmissao =  Date.valueOf(ack.split("<")[4].split(">")[0].split(",")[7]);
			
			Obra novaObra = new Obra(0,user.getLogin(),Integer.valueOf(user.getIdAutor()), titulo, null, EstiloLiterario.stringToEstilo(estiloLiterario1), TipoPublicacao.stringToTipo(tipo),
					Integer.valueOf(numPaginas), Integer.valueOf(numPalavras), Integer.valueOf(isbn), Integer.valueOf(numEdicao), dataSubmissao, null);
			System.out.println(novaObra.toString());
			System.out.println(ack);
			if(!ack.contentEquals("<server> <pesquisa> <obra> <fail>;")) {
				System.out.println("Pesquisa bem sucedida!");
				return true;
				//Close connection and application
			}
			else {
				System.out.println("Não foi possível realizar a pesquisa!");
				return false;
			}
		}
		catch(Exception e) {
			
		}
		return false;
		
	}
	
	
	
	/**
	    * Esta funcao permite ao utilizador fazer uma pesquisa de uma revisao em especifico atraves do numero de serie
	    * Posteriormente serao enviados atraves do servidor os dados referentes a essa revisao pesquisada e de seguida apresentado
	    *  ao utilizador a revisao atraves do toString do objeto Revisao
	    */
	public static boolean pesquisarRevisao() { //21 cliente
		
		try {
			String numSerie = lerDados("Insira o numero de serie da revisao a ser pesquisada: ");
			//socket = new Socket(serverIP, serverPort);
			
			out.println("<cliente> <pesquisa> <revisao> <"+numSerie+">;");
			//String message = in.readLine();
			String ack = readLine1();
			
			String gestor = ack.split("<")[4].split(">")[0].split(",")[0];
			//System.out.println(titulo);
			String revisor = ack.split("<")[4].split(">")[0].split(",")[1];
			Date dataRealizacao = Date.valueOf(ack.split("<")[4].split(">")[0].split(",")[2]);
			String tempoPercorrido = ack.split("<")[4].split(">")[0].split(",")[3];
			//String observacoes = ack.split("<")[4].split(">")[0].split(",")[4];
			String custo =  ack.split("<")[4].split(">")[0].split(",")[5];
			String estado =  ack.split("<")[4].split(">")[0].split(",")[6];
			int contador = 0;
			//String listaAnotacao[] = null;
			//while(true) {
				//if(observacoes.split(" - ")[contador] == null) {
					//break;
				//}
				//if(!observacoes.split(" - ")[contador].contentEquals("")) {
				//	String novaAnotacao = observacoes.split(" - ")[contador];
			//		listaAnotacao[contador] = novaAnotacao;
				//	contador++;
			//	}else {
				//	break;
			//	}
		//	}
			//System.out.println(gestor);

			Revisao novaRevisao = new Revisao(0, null, Integer.valueOf(gestor), null, numSerie, dataRealizacao, Integer.valueOf(tempoPercorrido), null,
					null, Double.valueOf(custo), null, null, null, null, EstadoRevisao.stringToEstado(estado));
			System.out.println(novaRevisao.toString());

			System.out.println(ack);
			if(!ack.contentEquals("<server> <pesquisa> <revisao> <fail>;")) {
				System.out.println("Pesquisa bem sucedida!");
				return true;
				//Close connection and application
			}
			else {
				System.out.println("Não foi possível realizar a pesquisa!");
				return false;
			}
		}
		catch(Exception e) {
			
		}
		return false;
		
		
	}
	
	
	public static boolean listarTodasObrasAutor(Autor user) { //22 cliente
		try {
			//String numSerie = lerDados("Insira o numero de serie da obra a ser pesquisada: ");
			//socket = new Socket(serverIP, serverPort);
			
			out.println("<cliente> <listar> <obra>;");
			//String message = in.readLine();
			String ack = readLine1();
			System.out.println(ack);
			int contador = 0;
			
			while(true) {
				if(!ack.split("<")[4].split(">")[0].split(",")[contador].contentEquals("")) {
					String titulo = ack.split("<")[4].split(">")[0].split(",")[contador];
					contador++;
					String estiloLiterario1 = ack.split("<")[4].split(">")[0].split(",")[contador];
					contador++;
					String tipo = ack.split("<")[4].split(">")[0].split(",")[contador];
					contador++;
					String numPaginas = ack.split("<")[4].split(">")[0].split(",")[contador];
					contador++;
					String numPalavras = ack.split("<")[4].split(">")[0].split(",")[contador];
					contador++;
					String isbn =  ack.split("<")[4].split(">")[0].split(",")[contador];
					contador++;
					String numEdicao =  ack.split("<")[4].split(">")[0].split(",")[contador];
					contador++;
					Date dataSubmissao =  Date.valueOf(ack.split("<")[4].split(">")[0].split(",")[contador]);
					contador++;
					
					Obra novaObra = new Obra(0,user.getLogin(),Integer.valueOf(user.getIdAutor()), titulo, null, EstiloLiterario.stringToEstilo(estiloLiterario1), TipoPublicacao.stringToTipo(tipo),
							Integer.valueOf(numPaginas), Integer.valueOf(numPalavras), Integer.valueOf(isbn), Integer.valueOf(numEdicao), dataSubmissao, null);
					System.out.println(novaObra.toString());
				}else {
					break;
				}
				
			}
			
			//String titulo = ack.split("<")[4].split(">")[0].split(",")[0];
			//System.out.println(titulo);
			//String estiloLiterario1 = ack.split("<")[4].split(">")[0].split(",")[1];
			//String tipo = ack.split("<")[4].split(">")[0].split(",")[2];
			//String numPaginas = ack.split("<")[4].split(">")[0].split(",")[3];
			//String numPalavras = ack.split("<")[4].split(">")[0].split(",")[4];
			//String isbn =  ack.split("<")[4].split(">")[0].split(",")[5];
			//String numEdicao =  ack.split("<")[4].split(">")[0].split(",")[6];
			//Date dataSubmissao =  Date.valueOf(ack.split("<")[4].split(">")[0].split(",")[7]);
			
			
			
			if(!ack.contentEquals("<server> <listar> <obra> <fail>;")) {
				System.out.println("Listagem bem sucedida!");
				return true;
				//Close connection and application
			}
			else {
				System.out.println("Listagem não foi possível!");
				return false;
			}
		}
		catch(Exception e) {
			
		}
		return false;
	}
	
	/**
	    * Esta funcao permite que o utilizador possa listar todas as revisoes associadas
	    * Para tal e criada um objeto novaRevisao para posteriormente ser apresentado ao utilizador o resultado atraves do toString presente na classe Revisao
	    */
	public static boolean listarTodasRevisoesAutor() { //23 cliente
		try {
			//String numSerie = lerDados("Insira o numero de serie da obra a ser pesquisada: ");
			//socket = new Socket(serverIP, serverPort);
			
			out.println("<cliente> <listar> <revisao>;");
			//String message = in.readLine();
			String ack = readLine1();
			System.out.println(ack);
			
			int contador2 = 0;
			int contador1 = 0;
			String listaAnotacao[] = null;
			int contador = 0;
			while(true) {
				if(!ack.split("<")[4].split(">")[0].split(",")[contador].contentEquals("")) {
					String gestor = ack.split("<")[4].split(">")[0].split(",")[contador];
					contador++;
					String revisor = ack.split("<")[4].split(">")[0].split(",")[contador];
					contador++;
					Date dataRealizacao =Date.valueOf(ack.split("<")[4].split(">")[0].split(",")[contador]);
					contador++;
					String tempoPercorrido = ack.split("<")[4].split(">")[0].split(",")[contador];
					contador++;
					//String observacoes = ack.split("<")[4].split(">")[0].split(",")[contador];
					contador++;
					String custo =  ack.split("<")[4].split(">")[0].split(",")[contador];
					contador++;
					String estado =  ack.split("<")[4].split(">")[0].split(",")[contador];
					contador++;
					
					contador2++;
					Revisao novaRevisao = new Revisao(contador2, null, 0, null, null, dataRealizacao, Integer.valueOf(tempoPercorrido), null,
							null, Double.valueOf(custo), null, null, null, null, EstadoRevisao.stringToEstado(estado));
					System.out.println(novaRevisao.toString());
				}else {
					break;
				}
				
			}
			
			
			if(!ack.contentEquals("<server> <listar> <revisao> <fail>;")) {
				System.out.println("Listagem bem sucedida!");
				return true;
				//Close connection and application
			}
			else {
				System.out.println("Listagem não foi possível!");
				return false;
			}
		}
		catch(Exception e) {
			
		}
		return false;
	}
	
	public static String lerDados(String aMensagem){
		System.out.print(aMensagem);
		return(new Scanner(System.in)).nextLine();
	}
	
	public static int lerDadosInt(String aMensagem){
		System.out.print(aMensagem);
		return(new Scanner(System.in)).nextInt();
		
	}

}

package sistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import users.Autor;
import users.EstadoConta;
import users.Gestor;
import users.GestorContas;
import users.Revisor;
import users.Utilizador;

public class ServerDriver extends Thread{
	
	Utilizador login = null;
    Socket clientSocket       = null;	
	PrintWriter clientWriter           = null;
	BufferedReader clientBuffer         = null;
	int clientInstanceID;
	
	
	public ServerDriver(Socket clientSocket,int clientID) {
		
		try {
			clientInstanceID = clientID;
			clientWriter          = new PrintWriter(clientSocket.getOutputStream(), true);
			clientBuffer           = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			System.err.println(clientInstanceID + " ## Client Connected...");
			clientWriter.println("<server> <hello>;");
			
			String clientReply = clientBuffer.readLine();
			
			if(clientReply.contentEquals("<cliente> <hello>;")) {
				clientWriter.println("<server> <ack>;");
			}
			else {
				clientWriter.println("<server> <bye>;");
				fechaConexao();
			}
			while(true) {
				System.out.println(" ");
				System.out.println(clientInstanceID + "## Waiting for client message... ");
				System.out.println(" ");
				
				clientReply = clientBuffer.readLine();
				System.out.println(clientReply);
				int commandResult = executeComand(clientReply);
				if(commandResult == 1) {
					System.out.println(clientInstanceID + "## Success");
				}
				else if(commandResult == 0){
					System.out.println(clientInstanceID + "## Fail");
				}
				else if(commandResult == 2) {
					System.out.println(clientInstanceID + "## Disconnect Request.");
					break;
				}
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			fechaConexao();
		}
	}
	
	public void fechaConexao() {
		try {
		    if (clientBuffer != null) 
		    	clientBuffer.close();
		    if (clientWriter != null) 
		    	clientWriter.close();
		    
		    if (clientSocket != null) 
		    	clientSocket.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(clientInstanceID + "## Disconnected.");
	}

	public int executeComand(String message) {
		
		//<login> <autenticar> <username,password>;
		if(message.split("<cliente>").length == 0) {
			return 0;
		}
		//System.out.println("Raw Split: " + message.split("<")[2].split(">")[0]);
		String[] args = message.split("<");
		for(int i = 0; i<args.length; i++) {
			args[i] = args[i].split(">")[0];
		}
		
		//System.out.println("Splitted argument: " + args[2]);
		
		switch(args[2]) { //Primeiro arg
		
			case "bye":
				return bye();
			
			case "hello":
				return ack();
				
			case "ack":
				return ack();
				
			case "autenticar":
				String username = args[3].split(",")[0];
				String password = args[3].split(",")[1];
				
				return autenticar(username,password);
			
			case "info":
				return detalhesUser();
				
			case "update":
				String[] userArgs = args[3].split(",");
				return atualizarUser(userArgs);
				
			case "inserir":
				switch(args[3]){
					case "obra":
						String[] obraArgs = args[4].split(",");
						return inserirObra(obraArgs);
				}
				
				return 0;
			case "pesquisa":
				switch(args[3]) {
					case "obra":
						return pesquisarObra(args[4]);
						
					case "revisao":
						return pesquisaRevisao(args[4]);
				}
			case "listar":
				switch(args[3]) {
				case "obra":
					return listarObras();
				case "revisao":
					return listarRevisoes();
				}
				break;
		}
		
		return 0;
	}
	int bye() {
		return 2;
	}
	
	int ack() {
		clientWriter.println("<server> <ack>;");
		return 1;
	}
	int listarObras() {
		if(!(login instanceof Autor)) {
			clientWriter.println("<server> <listar> <obra> <fail>;");
			return 0;
		}
		String message = "<server> <listar> <obra> <";
		
		Obra[] obras = GestorObras.listarObrasAutor((Autor)login);
		for(int i = 0; i< obras.length ; i++) {
			Obra obra = obras[i];
			message = message + String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s " + (i == obras.length-1 ? ">;" : ","), 
					obra.getTitulo(),
					EstiloLiterario.estiloToString(obra.getEstiloLiterario()),
					TipoPublicacao.tipoToString(obra.getTipoPublicacao()),
					Integer.toString(obra.getNumeroPaginas()),
					Integer.toString(obra.getNumeroPalavras()),
					Integer.toString(obra.getIsbn()),
					Integer.toString(obra.getNumeroEdicao()),
					obra.getDataSubmissao().toString(),
					(obra.getDataAprovacao() != null ? obra.getDataAprovacao().toString() : "null")
					);
		
		}
		if(obras.length > 0) {
			clientWriter.println(message);
			return 1;
		}
		
		clientWriter.println("<server> <listar> <obra> <fail>;");
		return 0;
	}
	
	int listarRevisoes() {
		if(!(login instanceof Autor)) {
			clientWriter.println("<server> <listar> <revisao> <fail>;");
			return 0;
		}
		
		Revisao[] revisoes = GestorRevisoes.listarRevisoes(); //TODO usar listarRevisoesAutor()
		if(revisoes.length > 0) {
			String message = "<server> <listar> <revisao> <";
		for(int x=0; x<revisoes.length; x++) {
			
			Revisao revisao = revisoes[x];
			Revisor resp = revisao.getRevisorResponsavel();
			int respID = (resp != null ? resp.getIdRevisor() : 0);
			String[] observacoes = revisao.getObservacoes();
			String stringifiedObservacoes = "";
			
			for(int i=0; i<observacoes.length;i++) {
				stringifiedObservacoes = stringifiedObservacoes + observacoes[i] + (i == observacoes.length-1 ? "" : " - ");
			}
			
			if(x != 0) {
				message = message + ", ";
			}
			
			message = message + String.format("%s,%s,%s,%s,(%s),%s,%s",
					Integer.toString(revisao.getGestorID()),
					respID,
					revisao.getDataRealizacao().toString(),
					Integer.toString(revisao.getTempoDecorrido()),
					stringifiedObservacoes,
					Double.toString(revisao.getCusto()),
					revisao.getEstado().toString()
					);
		}
		message = message + ">;";
		
		clientWriter.println(message);
		}
		else
		{
			clientWriter.println("<server> <listar> <revisao> <fail>;");
			return 0;
		}
		
		
		return 1;
		
	}
	
	int pesquisaRevisao(String numSerie) {
		Revisao rev = GestorRevisoes.peesquisarRevisaoPorSerie(numSerie);
		
		if(rev != null) {
			String message = "<server> <pesquisa> <revisao>";
			Revisor resp = rev.getRevisorResponsavel();
			int respID = (resp != null ? resp.getIdRevisor() : 0);
			String[] observacoes = rev.getObservacoes();
			
			String stringifiedObservacoes = "";
			
			for(int i=0; i<observacoes.length;i++) {
				if(i == observacoes.length-1)
				stringifiedObservacoes = stringifiedObservacoes + observacoes[i] + (i == observacoes.length-1 ? "" : ",");
				
			}
			
			message = message + String.format(" <%s,%s,%s,%s,(%s),%s,%s>;",
					Integer.toString(rev.getGestorID()),
					respID,
					rev.getDataRealizacao().toString(),
					Integer.toString(rev.getTempoDecorrido()),
					stringifiedObservacoes,
					Double.toString(rev.getCusto()),
					rev.getEstado().toString()
					);
			
		   clientWriter.println(message);
		   return 1;
		}
		
		clientWriter.println("<server> <pesquisa> <revisao> <fail>;");
		
		return 0;
	}
	
	
	int pesquisarObra(String titulo) {
		Obra obra = GestorObras.pesquisarObraPorTitulo(titulo);
		if(obra != null) {
			String message = "<server> <pesquisa> <obra>";
			message = message + String.format(" <%s,%s,%s,%s,%s,%s,%s,%s,%s>;", 
					obra.getTitulo(),
					EstiloLiterario.estiloToString(obra.getEstiloLiterario()),
					TipoPublicacao.tipoToString(obra.getTipoPublicacao()),
					Integer.toString(obra.getNumeroPaginas()),
					Integer.toString(obra.getNumeroPalavras()),
					Integer.toString(obra.getIsbn()),
					Integer.toString(obra.getNumeroEdicao()),
					obra.getDataSubmissao().toString(),
					(obra.getDataAprovacao() != null ? obra.getDataAprovacao().toString() : "null")
					);
			
			clientWriter.println(message);
			return 1;
		}
		
		clientWriter.println("<server> <pesquisa> <obra> <fail>;");
		return 0;
	}
	
	int inserirObra(String[] args) {
		if(!(login instanceof Autor)) {
			clientWriter.println("<server> <inserir> <obra> <fail>;");
			return 0;
		}
		Autor autor = (Autor)login;
		Obra obra = new Obra(
				0,
				autor.getLogin(),
				autor.getIdAutor(),
				args[0],
				"Sub Titulo",
				EstiloLiterario.stringToEstilo(args[1]),
				TipoPublicacao.stringToTipo(args[2]),
				Integer.parseInt(args[3]),
				Integer.parseInt(args[4]),
				Integer.parseInt(args[5]),
				Integer.parseInt(args[6]),
				null,
				null
				);
		
		if(BDDriver.adicionarObra(obra) == 0) {
			clientWriter.println("<server> <inserir> <obra> <ok>;");
			return 1;
		}
		else {
			clientWriter.println("<server> <inserir> <obra> <fail>;");
			return 0;
		}
	}
	
	int atualizarUser(String[] args) {
		
		if(BDDriver.editarUtilizador(login.getIdUser(),
				args[0],
				args[1],
				args[2],
				args[3],
				EstadoConta.valueOf(args[4]),
				(login instanceof Gestor ? null : args[5]),
				(login instanceof Gestor ? null : args[6]),
				(login instanceof Gestor ? null : args[7]))) {
			clientWriter.println("<server> <update> <ok>;");
			return 1;
		}
		
		clientWriter.println("<server> <update> <fail>;");
		return 0;
		
	}
	
	int detalhesUser() {
		String tipo = "";
		if(login instanceof Gestor) {
			tipo = "Gestor"; 
		}
		else if(login instanceof Revisor) {
			tipo = "Revisor";
		}
		else if(login instanceof Autor) {
			tipo = "Autor";
		}
		
		int result = 1;
		
		String message = "<server> <info>";
		switch(tipo) {
		case "Gestor":{
			Gestor u = (Gestor)login;
			message = message + String.format(" <%s,%s,%s,%s,%s,%s>;",
					tipo,
					u.getLogin(),
					u.getPassword(),
					u.getNome(),
					u.getEmail(),
					u.getEstado());
			break;
		}
			
		case "Revisor":{
			Revisor u = (Revisor)login;
			message = message + String.format(" <%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s>;",
					tipo,
					u.getLogin(),
					u.getPassword(),
					u.getNome(),
					u.getEmail(),
					u.getEstado(),
					u.getNif(),
					u.getTelefone(),
					u.getMorada(),
					u.getArea().toString(),
					u.getFormacao());
			break;
		}
			
		case "Autor":
			Autor u = (Autor)login;
			message = message + String.format(" <%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s>;",
					tipo,
					u.getLogin(),
					u.getPassword(),
					u.getNome(),
					u.getEmail(),
					u.getEstado(),
					u.getNif(),
					u.getTelefone(),
					u.getMorada(),
					u.getDataInicioAtividade().toString(),
					u.getEstilo());
			break;
			
		default:
			message = message + " <fail>;";
			result = 0;
			break;
		}
		clientWriter.println(message);
		return result;
	}
	
	//TODO <- adicionar check de conta que pode ser usada em login
	int autenticar(String user,String password) {
		Utilizador u = BDDriver.encontrarUtilizador(user, password);
		
		if(u != null) {
			login = u;
			clientWriter.println("<server> <autenticar> <success>;");
			return 1;
		}
		else {
			clientWriter.println("<server> <autenticar> <fail>;");
			return 0;
		}
	}
}

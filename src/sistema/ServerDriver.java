package sistema;

import java.io.PrintWriter;

import users.Autor;
import users.EstadoConta;
import users.Gestor;
import users.GestorContas;
import users.Revisor;
import users.Utilizador;

public class ServerDriver {
	
	Utilizador login = null;
	PrintWriter clientWriter = null;
	
	
	public ServerDriver(PrintWriter pw) {
		clientWriter = pw;
	}

	public boolean executeComand(String message) {
		
		//<login> <autenticar> <username,password>;
		if(message.split("<cliente>").length == 0) {
			return false;
		}
		System.out.println("Raw Split: " + message.split("<")[2].split(">")[0]);
		String[] args = message.split("<");
		for(int i = 0; i<args.length; i++) {
			args[i] = args[i].split(">")[0];
		}
		
		System.out.println("Splitted argument: " + args[2]);
		
		switch(args[2]) { //Primeiro arg
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
				
				return false;
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
				}
				break;
		}
		
		return true;
	}
	
	boolean listarObras() {
		if(!(login instanceof Autor)) {
			clientWriter.println("<server> <listar> <obra> <fail>;");
			return false;
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
			return true;
		}
		
		clientWriter.println("<server> <listar> <obra> <fail>;");
		return false;
	}
	
	boolean listarRevisoes() {
		if(!(login instanceof Autor)) {
			clientWriter.println("<server> <listar> <revisao> <fail>;");
			return false;
		}
		
		String message = "<server> <listar> <revisao> <";
		
		Revisao[] revisoes = GestorRevisoes.listarRevisoesAutor((Autor)login);
		
		
		
	}
	
	boolean pesquisaRevisao(String numSerie) {
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
		   return true;
		}
		
		clientWriter.println("<server> <pesquisa> <revisao> <fail>;");
		
		return false;
	}
	
	
	boolean pesquisarObra(String titulo) {
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
			return true;
		}
		
		clientWriter.println("<server> <pesquisa> <obra> <fail>;");
		return false;
	}
	
	boolean inserirObra(String[] args) {
		if(!(login instanceof Autor)) {
			clientWriter.println("<server> <inserir> <obra> <fail>;");
			return false;
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
			return true;
		}
		else {
			clientWriter.println("<server> <inserir> <obra> <fail>;");
			return false;
		}
	}
	
	boolean atualizarUser(String[] args) {
		
		if(BDDriver.editarUtilizador(login.getIdUser(),
				args[0],
				args[1],
				args[2],
				args[3],
				EstadoConta.valueOf(args[4]),
				args[5],
				args[6],
				args[7])) {
			clientWriter.println("<server> <update> <ok>;");
			return true;
		}
		
		clientWriter.println("<server> <update> <fail>;");
		return false;
		
	}
	
	boolean detalhesUser() {
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
		
		boolean result = true;
		
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
			result = false;
			break;
		}
		clientWriter.println(message);
		return result;
	}
	
	//TODO <- adicionar check de conta que pode ser usada em login
	boolean autenticar(String user,String password) {
		Utilizador u = BDDriver.encontrarUtilizador(user, password);
		
		if(u != null) {
			login = u;
			clientWriter.println("<server> <autenticar> <success>;");
			return true;
		}
		else {
			clientWriter.println("<server> <autenticar> <fail>;");
			return false;
		}
	}
}

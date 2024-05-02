package sistema;

import java.io.PrintWriter;

import users.Autor;
import users.EstadoConta;
import users.Gestor;
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
		System.out.println(message.split("<")[2].split(">")[0]);
		switch(message.split("<")[2].split(">")[0]) { //Primeiro arg
			case "autenticar":
				String username = message.split("<")[3].split(">")[0].split(",")[0];
				String password = message.split("<")[3].split(">")[0].split(",")[1];
				
				return autenticar(username,password);
			
			case "info":
				return detalhesUser();
				
			case "update":
				String[] args = message.split("<")[3].split(">")[0].split(",");
				return atualizarUser(args);
		}
		
		return true;
	}
	
	boolean atualizarUser(String[] args) {
		clientWriter.println("<server> <update> <ok>;");
		return BDDriver.editarUtilizador(login.getIdUser(),
				args[0],
				args[1],
				args[2],
				args[3],
				EstadoConta.valueOf(args[4]),
				args[5],
				args[6],
				args[7]);
		
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

package gestao;

import java.util.ArrayList;

import sistema.BDDriver;
import users.Utilizador;

/**
 * Classe responsavel por mandar e listar os logs do sistema
 * @author Andre Rios
 */
public class GestorLogs {
	
	static Utilizador session;
	
	public static void setUpLogger(Utilizador u) {
		 u = session;
	}
	
	/**
	 * 
	 * @param u - Utilizador Responsavel pelo log
	 * @param mensagem - String Com a sua mensagem
	 */
	public static void adicionarLog(String mensagem) {
		if(session != null)
		BDDriver.adicionarLog(session,mensagem);
		else
			System.err.println("Nao foi possivel realizar logs pois o utilizador nao foi definido no GestorLogs.setUpLogger()");
	}
	
	public static Log[] listarLogs() {
		return BDDriver.listarLogs();
	}
	/**
	 * 
	 * @param u - Utilizador Dos logs
	 * @return Log[] do Utilizador
	 */
	public static Log[] listarLogs(Utilizador u) {
		
		Log[] logs = listarLogs();
		
		ArrayList<Log> result = new ArrayList<Log>();
		for(var log : logs){
			if(log.getIdUser() == u.getIdUser()) {
				result.add(log);
			}
		}
		return result.toArray(new Log[0]);
	}
	/**
	 * As execucoes são contadas para cada execucao de Login feita com sucesso, isto quer dizer que a mensagem tem que ter incluida "Login"
	 * @return int total de execuções (logins)
	 */
	public static int nExecucoes() {
		Log[] logs = listarLogs();
	
		return nExecucoesDeLogs(logs);
	}
	/**
	 * As execucoes são contadas para cada execução de Login feita com sucesso, isto quer dizer que a mensagem tem que ter incluida "Login"
	 * @param u - Utilizador responsavel pelos logs
	 * @return int total de execucoes (logins) deste utilizador
	 */
	public static int nExecucoes(Utilizador u) {
		Log[] logs = listarLogs(u);
		
		return nExecucoesDeLogs(logs);
	}
	
	static int nExecucoesDeLogs(Log[] logs) {
		int count = 0;
		for(var log : logs) {
			if(log.getMensagem().contains("Login")) { //<- Modifica isto como quiseres, contamos todos os logs que digam que houve login
				count++;
			}
		}
		return count;
	}

	
}

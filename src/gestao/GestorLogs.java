package gestao;

import java.util.ArrayList;

import sistema.BDDriver;
import users.Utilizador;

public class GestorLogs {
	
	public void adicionarLog(Utilizador u, String mensagem) {
		BDDriver.adicionarLog(u,mensagem);
	}
	
	public Log[] listarLogs() {
		return BDDriver.listarLogs();
	}
	public Log[] listarLogs(Utilizador u) {
		
		Log[] logs = listarLogs();
		
		ArrayList<Log> result = new ArrayList<Log>();
		for(var log : logs){
			if(log.getIdUser() == u.getIdUser()) {
				result.add(log);
			}
		}
		return result.toArray(new Log[0]);
	}
	public int nExecucoes() {
		Log[] logs = listarLogs();
	
		return nExecucoesDeLogs(logs);
	}
	public int nExecucoes(Utilizador u) {
		Log[] logs = listarLogs(u);
		
		return nExecucoesDeLogs(logs);
	}
	
	int nExecucoesDeLogs(Log[] logs) {
		int count = 0;
		for(var log : logs) {
			if(log.getMensagem().contains("Teste")) { //<- Modifica isto como quiseres, contamos todos os logs que digam que houve login
				count++;
			}
		}
		return count;
	}

	
}

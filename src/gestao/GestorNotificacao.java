package gestao;

import java.util.ArrayList;

import sistema.BDDriver;
import users.Utilizador;

public class GestorNotificacao {
	
	public boolean adicionarNotificacao(Notificacao noti) {
		return BDDriver.adicionarNotificacao(noti);
	}
	public boolean setLidaNotificacao(int notID, boolean val) {
		return BDDriver.setNotificacaoLida(notID,val);
	}
	public Notificacao[] listarNotificacoes(Utilizador u, boolean val) {
		Notificacao[] nots = BDDriver.listarNotificacoes(u.getIdUser());
		ArrayList<Notificacao> result = new ArrayList<Notificacao>();
		
		for(var noti : nots) {
			if(noti.getLida() == val) {
				result.add(noti);
			}
		}
		
		return result.toArray(new Notificacao[0]);
	}
}

package gestao;

import users.Utilizador;

/**
 * Classe responsavel por guardar dados relevantes sobre notificacoes
 * @author Andre Rios
 */

public class Notificacao implements Comparable<Notificacao>{
	private int notID;
	private Utilizador utilizador;
	private String descricao;
	private boolean lida;
	@Override
	public int compareTo(Notificacao o) {
		if(notID > o.notID)
			return 1;
		if(notID < o.notID)
			return -1;
		return 0;
	}
	
}

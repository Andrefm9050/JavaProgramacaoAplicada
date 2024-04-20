package gestao;

import users.Utilizador;

/**
 * Classe responsavel por guardar dados relevantes sobre notificacoes
 * @author Andre Rios
 */

public class Notificacao implements Comparable<Notificacao>{
	private int notID;
	private int utilizadorID;
	private String descricao;
	private boolean lida;
	
	public Notificacao(int notID, int userID,String desc,boolean lido) {
		this.notID = notID;
		this.utilizadorID = userID;
		this.descricao = desc;
		this.lida = lido;
	}
	
	@Override
	public int compareTo(Notificacao o) {
		if(notID > o.notID)
			return 1;
		if(notID < o.notID)
			return -1;
		return 0;
	}
	
	public int getUtilizadorID() {
		return utilizadorID;
	}
	public String getDescricao() {
		return descricao;
	}
	
}

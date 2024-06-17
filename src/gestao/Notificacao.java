package gestao;

import users.Listable;
import users.Utilizador;

/**
 * Classe responsavel por guardar dados relevantes sobre notificacoes
 * @author Andre Rios
 */

public class Notificacao implements Listable<Notificacao>{
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
	public boolean getLida() {
		return lida;
	}

	public String[][] filtragensDisponiveis() {
		String[][] options = new String[1][2];
		options[0][0] = "default";
		options[0][1] = "";
		
		
		return options;
	}
	@Override
	public void setOrdenacao(String ordenacao) {
		// TODO Auto-generated method stub
		
	}
	
}

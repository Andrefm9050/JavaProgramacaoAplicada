package sistema;

import java.sql.Date;

import users.Listable;
import users.Revisor;

public class Observacao implements Listable<Observacao>{
	private String texto;
	private Date data;
	private int revisor;
	
	public Observacao(String texto, Date data, int revID) {
		this.texto = texto;
		this.data = data;
		this.revisor = revID;
	}
	
	
	public String getTexto() {
		return texto;
	}
	public Date getData() {
		return data;
	}
	
	public int getRevisor() {
		return revisor;
	}


	@Override
	public int compareTo(Observacao o) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String[][] filtragensDisponiveis() {
		String[][] options = new String[2][2];
		options[0][0] = "default";
		options[0][1] = "";
		
		return options;
	}


	@Override
	public void setOrdenacao(String ordenacao) {
		// TODO Auto-generated method stub
		
	}
	
}

package sistema;

import java.sql.Date;

import users.Revisor;

public class Observacao {
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
	
}

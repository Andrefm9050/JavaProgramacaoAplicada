package gestao;

import java.sql.Date;

public class Log {
	private int idUser;
	private String mensagem;
	private Date hora;
	
	public Log(int idUser, String mensagem, Date hora) {
		this.idUser = idUser;
		this.mensagem = mensagem;
		this.hora = hora;
	}
	public int getIdUser() {
		return idUser;
	}
	public String getMensagem() {
		return mensagem;
	}
	public Date getHora() {
		return hora;
	}
}

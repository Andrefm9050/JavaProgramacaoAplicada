package gestao;

import java.sql.Date;

import users.Listable;

/**
 * Classe Log usada para representar um log feito por um utilizador
 * @author Andre Rios
 */
public class Log implements Listable<Log>{
	private int idUser;
	private String mensagem;
	private Date hora;
	
	/**
	 * 
	 * @param idUser - ID to utilizador que fez este log
	 * @param mensagem - mensagem do log
	 * @param hora - de quando o log foi registado no sistema
	 */
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
	@Override
	public int compareTo(Log o) {
		return hora.compareTo(o.hora);
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

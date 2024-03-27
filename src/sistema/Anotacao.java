package sistema;

import java.util.Date;

public class Anotacao {
	private int anotID;
	private String descricao;
	private int pagina;
	private int paragrafo;
	private Date data;
	
	public Anotacao(int anotID, String descricao, int pagina, int paragrafo, Date data) {
		this.anotID = anotID;
		this.descricao = descricao;
		this.pagina = pagina;
		this.paragrafo = paragrafo;
		this.data = data;
	}
}

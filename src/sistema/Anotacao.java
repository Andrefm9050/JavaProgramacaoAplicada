package sistema;

import java.util.Date;

import users.Listable;
import users.Revisor;

/**
 * Classe responsavel por guardar dados relevantes de uma anotacao, normalmente dados deste tipo so sao acessiveis a partir da sua revisao
 * @author Andre Rios, Andre Martins
 */
public class Anotacao implements Listable<Anotacao> {
	private int anotID;
	private String descricao;
	private int pagina;
	private int paragrafo;
	private Date data;
	private Revisor revisor;
	
	/**
	 * @param anotID - ID da anotacao
	 * @param descricao - Descricao ou conteudo da anotacao
	 * @param pagina - Pagina onde esta descricao e aplicada
	 * @param paragrafo - Paragrafo onde esta descricao e aplicada
	 * @param data - Data de quando esta anotacao foi criada
	 */
	public Anotacao(int anotID, String descricao, int pagina, int paragrafo, Date data,Revisor rev) {
		this.anotID = anotID;
		this.descricao = descricao;
		this.pagina = pagina;
		this.paragrafo = paragrafo;
		this.data = data;
		this.revisor = rev;
	}

	@Override
	public int compareTo(Anotacao o) {
		return this.data.compareTo(o.data);
	}

	public int getAnotID() {
		return anotID;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getPagina() {
		return pagina;
	}

	public int getParagrafo() {
		return paragrafo;
	}
	public Revisor getRevisor() {
		return revisor;
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

package sistema;

import java.util.Date;

/**
 * Classe responsavel por guardar dados relevantes de uma anotacao, normalmente dados deste tipo so sao acessiveis a partir da sua revisao
 * @author Andre Rios, Andre Martins
 */
public class Anotacao implements Comparable<Anotacao> {
	private int anotID;
	private String descricao;
	private int pagina;
	private int paragrafo;
	private Date data;
	
	/**
	 * @param anotID - ID da anotacao
	 * @param descricao - Descricao ou conteudo da anotacao
	 * @param pagina - Pagina onde esta descricao e aplicada
	 * @param paragrafo - Paragrafo onde esta descricao e aplicada
	 * @param data - Data de quando esta anotacao foi criada
	 */
	public Anotacao(int anotID, String descricao, int pagina, int paragrafo, Date data) {
		this.anotID = anotID;
		this.descricao = descricao;
		this.pagina = pagina;
		this.paragrafo = paragrafo;
		this.data = data;
	}

	@Override
	public int compareTo(Anotacao o) {
		return this.data.compareTo(o.data);
	}
}

package sistema;

import java.sql.Date;


/**
 * Classe responsavel por guardar dados relevantes sobre uma licensa
 * @author Andre Rios
 */
public class Licensa implements Comparable<Licensa>{
	private int idLicensa;
	private int idRevisao;
	private String nomeLicensa;
	private int numeroSerie;
	private Date expiracao;
	
	/**
	 * @param ID da licensa (gerado pela base de dados)
	 * @param ID da revisao onde esta licensa esta a ser usada
	 * @param Nome da licensa
	 * @param Numero de serie da licensa
	 * @param Expiracao da licensa
	 */
	public Licensa(int idLicensa, int idRevisao, String nomeLicensa, int numeroSerie, Date expiracao) {
		this.idLicensa = idLicensa;
		this.idRevisao = idRevisao;
		this.nomeLicensa = nomeLicensa;
		this.numeroSerie = numeroSerie;
		this.expiracao = expiracao;
	}
	@Override
	public int compareTo(Licensa o) {
		return expiracao.compareTo(o.expiracao);
	}
	
}

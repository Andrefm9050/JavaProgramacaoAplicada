package sistema;

import java.sql.Date;

public class Licensa {
	private int idLicensa;
	private int idRevisao;
	private String nomeLicensa;
	private int numeroSerie;
	private Date expiracao;
	public Licensa(int idLicensa, int idRevisao, String nomeLicensa, int numeroSerie, Date expiracao) {
		this.idLicensa = idLicensa;
		this.idRevisao = idRevisao;
		this.nomeLicensa = nomeLicensa;
		this.numeroSerie = numeroSerie;
		this.expiracao = expiracao;
	}
}

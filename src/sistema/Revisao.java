package sistema;

import java.util.ArrayList;
import java.sql.Date;

import users.Gestor;
import users.Revisor;

public class Revisao implements Comparable<Revisao>{
	private int revisaoID;
	private int obraID;
	//private Autor autor; <- Ja temos um autor a partir da obra
	private int gestorID;
	private int revisorResponsavel;
	private String numeroSerie; // SEQAAAAMMDDHHMMSS (SEQ = Nrevisoes + 1, AAAAMMDDHHMMSS = Instante)
	private Date dataRealizacao;
	private Date tempoDecorrido;
	private Anotacao[] anotacoes; //ou getAnotacoes() seria melhor
	private String[] observacoes; //ou getObservacoes() seria melhor
	private double custo;
	private Integer[] revisoresRecusados; //ou getRevisorRecusados() seria melhor
	private EstadoRevisao estado;
	private Licensa[] licensas;
	
	public Revisao(int revID, int obraID, int gestorID, int revisorResponsavel, String numeroSerie, Date dataRealizacao,
			Date tempoDecorrido, Anotacao[] anotacoes, String[] observacoes, double custo,
			Integer[] revisoresRecusados,Licensa[] licensas, EstadoRevisao estado) {
		this.revisaoID = revID;
		this.obraID = obraID;
		this.gestorID = gestorID;
		this.revisorResponsavel = revisorResponsavel;
		this.numeroSerie = numeroSerie;
		this.dataRealizacao = dataRealizacao;
		this.tempoDecorrido = tempoDecorrido;
		this.anotacoes = anotacoes;
		this.observacoes = observacoes;
		this.custo = custo;
		this.revisoresRecusados = revisoresRecusados;
		this.estado = estado;
		this.licensas = licensas;
	}
	public void setAnotacoes(Anotacao[] an) {
		anotacoes = an;
	}
	public void setObservacoes(String[] obs) {
		observacoes = obs;
	}
	public void setRevisoresRec(Integer[] rev) {
		revisoresRecusados = rev;
	}
	public void setLicensas(Licensa[] lic) {
		licensas = lic;
	}
	@Override
	public int compareTo(Revisao o) {
		if(revisaoID > o.revisaoID)
			return 1;
		if(revisaoID < o.revisaoID)
			return -1;
		
		return 0;
	}
}

package sistema;

import java.util.ArrayList;
import java.util.Arrays;
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
	private int tempoDecorrido;
	private Anotacao[] anotacoes; //ou getAnotacoes() seria melhor
	private String[] observacoes; //ou getObservacoes() seria melhor
	private double custo;
	private Integer[] revisoresRecusados; //ou getRevisorRecusados() seria melhor
	private EstadoRevisao estado;
	private Licensa[] licensas;

	public Revisao(int revID, int obraID, int gestorID, int revisorResponsavel, String numeroSerie, Date dataRealizacao,
			int tempoDecorrido, Anotacao[] anotacoes, String[] observacoes, double custo,
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
		this.revisaoID = revID;
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
	public String toString() {
		return "Revisao [revisaoID=" + revisaoID + ", obraID=" + obraID + ", gestorID=" + gestorID
				+ ", revisorResponsavel=" + revisorResponsavel + ", numeroSerie=" + numeroSerie + ", dataRealizacao="
				+ dataRealizacao + ", tempoDecorrido=" + tempoDecorrido + ", anotacoes=" + Arrays.toString(anotacoes)
				+ ", observacoes=" + Arrays.toString(observacoes) + ", custo=" + custo + ", revisoresRecusados="
				+ Arrays.toString(revisoresRecusados) + ", estado=" + estado + ", licensas=" + Arrays.toString(licensas)
				+ "]";
	}
	public int getRevisaoID() {
		return revisaoID;
	}
	public void setRevisaoID(int revisaoID) {
		this.revisaoID = revisaoID;
	}
	public int getObraID() {
		return obraID;
	}
	public void setObraID(int obraID) {
		this.obraID = obraID;
	}
	public int getGestorID() {
		return gestorID;
	}
	public void setGestorID(int gestorID) {
		this.gestorID = gestorID;
	}
	public int getRevisorResponsavel() {
		return revisorResponsavel;
	}
	public void setRevisorResponsavel(int revisorResponsavel) {
		this.revisorResponsavel = revisorResponsavel;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public Date getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	public int getTempoDecorrido() {
		return tempoDecorrido;
	}
	public void setTempoDecorrido(int tempoDecorrido) {
		this.tempoDecorrido = tempoDecorrido;
	}
	public double getCusto() {
		return custo;
	}
	public void setCusto(double custo) {
		this.custo = custo;
	}
	public Integer[] getRevisoresRecusados() {
		return revisoresRecusados;
	}
	public void setRevisoresRecusados(Integer[] revisoresRecusados) {
		this.revisoresRecusados = revisoresRecusados;
	}
	public EstadoRevisao getEstado() {
		return estado;
	}
	public void setEstado(EstadoRevisao estado) {
		this.estado = estado;
	}
	public Anotacao[] getAnotacoes() {
		return anotacoes;
	}
	public String[] getObservacoes() {
		return observacoes;
	}
	public Licensa[] getLicensas() {
		return licensas;
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

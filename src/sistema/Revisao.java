package sistema;

import java.util.ArrayList;
import java.util.Arrays;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Date;

import users.Gestor;
import users.Revisor;

public class Revisao implements Comparable<Revisao>{
	private int revisaoID;
	private Obra obra;
	//private Autor autor; <- Ja temos um autor a partir da obra
	private int gestorID;
	private Revisor revisorResponsavel;
	private String numeroSerie; // SEQAAAAMMDDHHMMSS (SEQ = Nrevisoes + 1, AAAAMMDDHHMMSS = Instante)
	private Date dataRealizacao;
	private int tempoDecorrido;
	private Anotacao[] anotacoes; //ou getAnotacoes() seria melhor
	private String[] observacoes; //ou getObservacoes() seria melhor
	private double custo;
	private Revisor[] revisoresConfirmados;
	private Revisor[] revisoresNaoConfirmados;
	private Revisor[] revisoresRecusados; //ou getRevisorRecusados() seria melhor
	private EstadoRevisao estado;
	private Licensa[] licensas;
	private String ordenacao;
	private String[] eventos;
	
/**
 * 
 * @param revID - ID da revisao
 * @param obra - ID da obra a ser revista
 * @param gestorID - ID do gestor responsavel (0 = nao atribuido)
 * @param revisorResponsavel - Revisor responsavel (null = nao atribuido; ou estado = EstadoRevisao.iniciada = nao confirmado;)
 * @param numeroSerie - Numero serie da revisao
 * @param dataRealizacao - Data de realizacao da revisao
 * @param tempoDecorrido - Tempo total (em minutos) gastos na revisao
 * @param anotacoes - Anotacoes da revisao
 * @param observacoes - Observacoes da revisao
 * @param custo - Custo da revisao 
 * @param revisoresRecusados - Lista de revisores que recusaram a revisao
 * @param licensas - Lista de licensas da revisao
 * @param revisoresConfirmados - Lista de revisores confirmados
 * @param revisoresNaoConfirmados - Lista de revisores com confirmacao pendente
 * @param estado - Estado da revisao
 */
	public Revisao(int revID, Obra obra, int gestorID, Revisor revisorResponsavel, String numeroSerie, Date dataRealizacao,
			int tempoDecorrido, Anotacao[] anotacoes, String[] observacoes, double custo,
			Revisor[] revisoresRecusados,Licensa[] licensas,Revisor[] revisoresConfirmados,Revisor[] revisoresNaoConfirmados, EstadoRevisao estado) {
		this.revisaoID = revID;
		this.obra = obra;
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
		this.revisoresConfirmados = revisoresConfirmados;
		this.revisoresNaoConfirmados = revisoresNaoConfirmados;
		ordenacao = "";
	}
	public void setEventos(String[] ev) {
		eventos = ev;
	}
	public void setAnotacoes(Anotacao[] an) {
		anotacoes = an;
	}
	public void setObservacoes(String[] obs) {
		observacoes = obs;
	}
	public void setRevisoresRec(Revisor[] rev) {
		revisoresRecusados = rev;
	}
	public void setLicensas(Licensa[] lic) {
		licensas = lic;
	}
	public void setRevisoresConfirmados(Revisor[] rev) {
		revisoresConfirmados = rev;
	}
	public void setRevisoresNaoConfirmados(Revisor[] rev) {
		revisoresNaoConfirmados = rev;
	}
	@Override
	public String toString() {
		return "Revisao [revisaoID=" + revisaoID + " NomeObra= "+ obra.getTitulo() +", obraID=" + obra.getObraId() + ", gestorID=" + gestorID
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
		return obra.getObraId();
	}
	public Obra getObra(){
		return obra;
	}
	public void setObra(Obra obra) {
		this.obra = obra;
	}
	public int getGestorID() {
		return gestorID;
	}
	public void setGestorID(int gestorID) {
		this.gestorID = gestorID;
	}
	public Revisor getRevisorResponsavel() {
		return revisorResponsavel;
	}
	public void setRevisorResponsavel(Revisor revisorResponsavel) {
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
	public Revisor[] getRevisoresRecusados() {
		return revisoresRecusados;
	}
	public void setRevisoresRecusados(Revisor[] revisoresRecusados) {
		this.revisoresRecusados = revisoresRecusados;
	}
	public Revisor[] getRevisoresConfirmados() {
		return revisoresNaoConfirmados;
	}
	public Revisor[] getRevisoresNaoConfirmados() {
		return revisoresNaoConfirmados;
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
	public void setOrdenacao(String ordenacao) {
		//d -data criacao
		//t -titulo obra
		//a - autor
		//n - numero serie
		this.ordenacao = ordenacao;
		
	}
	
	public void ImprimirExtracto() {
		JTextArea texto = new JTextArea();
		
		String textoFinal = " Estrato para revisao com ID: " + revisaoID;
		for(var evento : eventos) {
			textoFinal += "\n " + evento;
		}
		try {
			texto.setText(textoFinal);
			texto.print();
		} catch (PrinterException e) {
			e.printStackTrace();
		}

		
	}
	@Override
    public int compareTo(Revisao o) {
		
		switch(ordenacao) {
		case "":
			if(revisaoID > o.revisaoID)
            return 1;
        if(revisaoID < o.revisaoID)
            return -1;

        return 0;			
		
		case "d":
			return dataRealizacao.compareTo(o.dataRealizacao);
			
		case "t":
			return obra.getTitulo().compareTo(o.obra.getTitulo());
			
		case "a":
			return obra.getAutor().compareTo(obra.getAutor());
		case "n":
			return numeroSerie.compareTo(o.numeroSerie);
        
		}
        
		return 0;
    }

}

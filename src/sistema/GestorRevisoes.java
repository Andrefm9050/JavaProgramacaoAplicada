package sistema;

import java.sql.Date;

import gestao.GestorLogs;
import users.Revisor;

public class GestorRevisoes {
	
	public boolean adicionarRevisao(Revisao rev) {
		try {
		BDDriver.adicionarRevisao(Integer.parseInt(rev.getNumeroSerie()), rev.getObraID(), rev.getGestorID());
		}
		catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public boolean adicionarAnotacaoRevisao(Revisor revisor,int revisaoID, Anotacao anot) {		
		
		GestorLogs.adicionarLog(" adicionado Anotacao na revisao: " + revisaoID);
		
		return BDDriver.adicionarAnotacaoRevisao(revisor,revisaoID, anot);
	}
	
	public boolean adicionarObservacaoRevisao(Revisor revisor,int revID, String obs) {
		
		GestorLogs.adicionarLog(" adicionado Observacao na revisao: " + revID);
		
		return BDDriver.adicionarObservacaoRevisao(revisor,revID, obs);
	}
	
	public boolean adicionarLicensaRevisao(Revisor revisor,int revID, Licensa lic) {
		
		GestorLogs.adicionarLog(" adicionado Licensa na revisao: " + revID);
		
		return BDDriver.adicionarLicensaRevisao(lic, revID);
	}
	
	public boolean setRevisaoEstado(int revID, EstadoRevisao estado) {
		
		GestorLogs.adicionarLog(" adicionado Licensa na revisao: " + revID);
		
		return BDDriver.atualizarEstadoRevisao(revID, EstadoRevisao.estadoToInt(estado));
	}
	
	public boolean setPagarRevisao(int revID, float valor) {
		GestorLogs.adicionarLog(" pagou "+ valor +"€ na revisao: " + revID);
		
		return BDDriver.setPagarRevisao(revID, valor);
	}
	public boolean setAdicionarTempoRevisao(int revID, int minutes) {
		GestorLogs.adicionarLog(" adicionou "+ minutes + " minutos de tempo na revisao: " + revID);
		
		return BDDriver.setAdicionarTempoRevisao(revID, minutes);
	}
	public boolean setRevisorResponsavelRevisao(int revID, int revisorID) {
		GestorLogs.adicionarLog(" definiu o revisor com ID " + revID + " como responsavel na revisao: " + revisorID);
		
		return BDDriver.definirRevisorResponsavel(revID, revisorID);
	}
	
	public boolean setRevisorResponsavelConfirmarRevisao(int revID, boolean valor) {
		GestorLogs.adicionarLog(" confirmou como revisor responsavel com resposta: "+ valor + "na revisao: " + revID);
		
		return BDDriver.confirmarRevisorResponsavel(revID, valor);
	}
	
	public boolean setAdicionarRevisorRevisao(int revID, int revisorID) {
		GestorLogs.adicionarLog(" adicionou o revisor com ID " + revisorID + " na revisao: " + revID);
		
		return BDDriver.adicionarRevisor(revID, revisorID);
	}
	
	public boolean setRevisorConfirmarRevisao(int revID, int revisorID, boolean valor) {
		GestorLogs.adicionarLog(" confirmou a sua presenca na revisao: " + revID + " com resposta: " + valor);
		
		return BDDriver.confirmarRevisorNormal(revID, revisorID, valor);
	}
	
	
	public Revisao[] listarRevisoes() {
		return BDDriver.listarRevisoes();
	}
	public Revisao[] listarRevisoesPorData() {
		return null;
	}
	
	public Revisao[] listarRevisoesPorTitulo() {
		return null;
	}
	public Revisao[] listarRevisoesPorAutor() {
		return null;
	}
	public Revisao[] pesquisarRevisoesEstado(EstadoRevisao estado) {
		return null;
	}
	public Revisao[] pesquisarRevisoesISBN(int ISBN) {
		return null;
	}

	public Revisao[] pesquisarRevisoesAutor(String autor) {
		return null;
	}
	
	public Revisao[] pesquisarRevisoesEntre(Date primeira, Date segunda) {
		return null;
	}
	
	public Revisao[] pesquisarRevisoesCriacao(Date criacao) {
		return null;
	}
	public Revisao[] pesquisarRevisoesObra(int obraID) {
		return null;
	}
	
	
	
}

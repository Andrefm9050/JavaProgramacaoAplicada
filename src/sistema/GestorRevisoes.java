package sistema;

import java.sql.Date;

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
	
	public boolean adicionarAnotacaoRevisao(int revID, Anotacao anot) {
		return BDDriver.adicionarAnotacaoRevisao(revID, anot);
	}
	
	public boolean adicionarObservacaoRevisao(int revID, String obs) {
		return BDDriver.adicionarObservacaoRevisao(revID, obs);
	}
	
	public boolean adicionarLicensaRevisao(int revID, Licensa lic) {
		return BDDriver.adicionarLicensaRevisao(lic, revID);
	}
	
	public boolean setRevisaoEstado(int revID, EstadoRevisao estado) {
		return BDDriver.atualizarEstadoRevisao(revID, EstadoRevisao.estadoToInt(estado));
	}
	
	public boolean setPagarRevisao(int revID, float valor) {
		return BDDriver.setPagarRevisao(revID, valor);
	}
	public boolean setAdicionarTempoRevisao(int revID, int minutes) {
		return BDDriver.setAdicionarTempoRevisao(revID, minutes);
	}
	public boolean setRevisorResponsavelRevisao(int revID, int revisorID) {
		return BDDriver.definirRevisorResponsavel(revID, revisorID);
	}
	
	public boolean setRevisorResponsavelConfirmarRevisao(int revID, boolean valor) {
		return BDDriver.confirmarRevisorResponsavel(revID, valor);
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

package sistema;

import java.sql.Date;

public class GestorRevisoes {
	
	public boolean adicionarRevisao(Revisao rev) {
		return false;
	}
	
	public boolean adicionarAnotacaoRevisao(int revID, Anotacao anot) {
		return false;
	}
	
	public boolean adicionarObservacaoRevisao(int revID, String obs) {
		return false;
	}
	
	public boolean adicionarLicensaRevisao(int revID, int licID) {
		return false;
	}
	
	public boolean setRevisaoEstado(int revID, EstadoRevisao estado) {
		return false;
	}
	
	public boolean setPagarRevisao(int revID, float valor) {
		return false;
	}
	public boolean setAdicionarTempoRevisao(int revID, int minutes) {
		return false;
	}
	public boolean setRevisorResponsavelRevisao(int revID, int revisorID) {
		return false;
	}
	
	public boolean setRevisorResponsavelConfirmarRevisao(int revID, boolean valor) {
		return false;
	}
	
	public Revisao[] listarRevisoes() {
		return null;
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

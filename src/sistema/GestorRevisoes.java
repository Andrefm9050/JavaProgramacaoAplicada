package sistema;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

import gestao.GestorLogs;
import users.Revisor;

public class GestorRevisoes {
	
	public boolean adicionarRevisao(Revisao rev) {
		try {
			GestorLogs.adicionarLog(" registou uma nova revisao para a obra: " + rev.getObraID());
		BDDriver.adicionarRevisao(Integer.parseInt(rev.getNumeroSerie()), rev.getObraID(), rev.getGestorID());
		}
		catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public boolean adicionarAnotacaoRevisao(Revisor revisor,int revisaoID, Anotacao anot) {		
		
		GestorLogs.adicionarLog(" adicionado Anotacao na revisao: " + revisaoID);
		
		BDDriver.registarRevisaoEvento(revisaoID,revisor.getIdUser()," adicionou uma anotacao");
		
		return BDDriver.adicionarAnotacaoRevisao(revisor,revisaoID, anot);
	}
	
	public boolean adicionarObservacaoRevisao(Revisor revisor,int revID, String obs) {
		
		GestorLogs.adicionarLog(" adicionado Observacao na revisao: " + revID);
		
		BDDriver.registarRevisaoEvento(revID,revisor.getIdUser()," adicionou uma observacao na revisao");
		
		return BDDriver.adicionarObservacaoRevisao(revisor,revID, obs);
	}
	
	public boolean adicionarLicensaRevisao(Revisor revisor,int revID, Licensa lic) {
		
		GestorLogs.adicionarLog(" adicionado Licensa na revisao: " + revID);
		
		BDDriver.registarRevisaoEvento(revID,revisor.getIdUser()," adicionou uma licensa");
		
		return BDDriver.adicionarLicensaRevisao(lic, revID);
	}
	
	public boolean setRevisaoEstado(int userID,int revID, EstadoRevisao estado) {
		
		GestorLogs.adicionarLog(" definiu estado "+ estado +" na revisao: " + revID);
		
		BDDriver.registarRevisaoEvento(revID,userID," modou o estado da revisao para " + estado);
		
		return BDDriver.atualizarEstadoRevisao(revID, EstadoRevisao.estadoToInt(estado));
	}
	
	public boolean setPagarRevisao(int userID,int revID, float valor) {
		GestorLogs.adicionarLog(" pagou "+ valor +"€ na revisao: " + revID);
		
		BDDriver.registarRevisaoEvento(revID,userID," pagou a revisao com o valor de " + valor);
		
		return BDDriver.setPagarRevisao(revID, valor);
	}
	public boolean setAdicionarTempoRevisao(int userID,int revID, int minutes) {
		GestorLogs.adicionarLog(" adicionou "+ minutes + " minutos de tempo na revisao: " + revID);
		
		BDDriver.registarRevisaoEvento(revID,userID," adicionou " + minutes + " minutos ao tempo total");
		
		return BDDriver.setAdicionarTempoRevisao(revID, minutes);
	}
	public boolean setRevisorResponsavelRevisao(int userID,int revID, int revisorID) {
		
		GestorLogs.adicionarLog(" definiu o revisor com ID " + revID + " como responsavel na revisao: " + revisorID);
		
		
		BDDriver.registarRevisaoEvento(revID,userID," definiu revisor responsavel com ID " + revisorID);
		
		
		return BDDriver.definirRevisorResponsavel(revID, revisorID);
	}
	
	public boolean setRevisorResponsavelConfirmarRevisao(int userID,int revID, boolean valor) {
		GestorLogs.adicionarLog(" confirmou como revisor responsavel com resposta: "+ valor + "na revisao: " + revID);
		
		BDDriver.registarRevisaoEvento(revID,userID," confirmou como revisor responsavel");
		
		return BDDriver.confirmarRevisorResponsavel(revID, valor);
	}
	
	public boolean setAdicionarRevisorRevisao(int userID,int revID, int revisorID) {
		GestorLogs.adicionarLog(" adicionou o revisor com ID " + revisorID + " na revisao: " + revID);
		
		BDDriver.registarRevisaoEvento(revID,userID," adicionou revisor com ID "+revisorID+" á revisão");
		
		return BDDriver.adicionarRevisor(revID, revisorID);
	}
	
	public boolean setRevisorConfirmarRevisao(int userID,int revID, int revisorID, boolean valor) {
		GestorLogs.adicionarLog(" confirmou a sua presenca na revisao: " + revID + " com resposta: " + valor);
		
		BDDriver.registarRevisaoEvento(revID,userID," revisor com ID: "+revisorID+" confirmou a sua precensa como revisor");
		
		return BDDriver.confirmarRevisorNormal(revID, revisorID, valor);
	}
	
	
	public Revisao[] listarRevisoes() {
		return BDDriver.listarRevisoes();
	}
	public Revisao[] listarRevisoesPorData() {
		ArrayList<Revisao> result = new ArrayList<Revisao>();
		Revisao[] list = listarRevisoes();
		for(var res : list) {
			res.setOrdenacao("d");
			result.add(res);
		}

		Collections.sort(result);
		return result.toArray(new Revisao[0]);
	}
	
	public Revisao[] listarRevisoesPorTitulo() {
		ArrayList<Revisao> result = new ArrayList<Revisao>();
		Revisao[] list = listarRevisoes();
		for(var res : list) {
			res.setOrdenacao("t");
			result.add(res);
		}

		Collections.sort(result);
		return result.toArray(new Revisao[0]);
	}
	public Revisao[] listarRevisoesPorAutor() {
		ArrayList<Revisao> result = new ArrayList<Revisao>();
		Revisao[] list = listarRevisoes();
		for(var res : list) {
			res.setOrdenacao("a");
			result.add(res);
		}

		Collections.sort(result);
		return result.toArray(new Revisao[0]);
	}
	public Revisao[] pesquisarRevisoesEstado(EstadoRevisao estado) {
		ArrayList<Revisao> result = new ArrayList<Revisao>();
		Revisao[] list = listarRevisoes();
		for(var res : list) {
			if(res.getEstado() == estado)
				result.add(res);
		}

		return result.toArray(new Revisao[0]);
	}
	public Revisao[] pesquisarRevisoesISBN(String ISBN) {
		ArrayList<Revisao> result = new ArrayList<Revisao>();
		Revisao[] list = listarRevisoes();
		for(var res : list) {
			if(res.getNumeroSerie() == ISBN)
				result.add(res);
		}

		return result.toArray(new Revisao[0]);
	}

	public Revisao[] pesquisarRevisoesAutor(String autor) {
		ArrayList<Revisao> result = new ArrayList<Revisao>();
		Revisao[] list = listarRevisoes();
		for(var res : list) {
			if(res.getObra().getAutor() == autor)
				result.add(res);
		}

		return result.toArray(new Revisao[0]);
	}
	
	public Revisao[] pesquisarRevisoesEntre(Date primeira, Date segunda) {
		ArrayList<Revisao> result = new ArrayList<Revisao>();
		Revisao[] list = listarRevisoes();
		for(var res : list) {
			if(res.getDataRealizacao().after(primeira) && res.getDataRealizacao().before(segunda))
				result.add(res);
		}

		return result.toArray(new Revisao[0]);
	}
	
	public Revisao[] pesquisarRevisoesCriacao(Date criacao) {
		ArrayList<Revisao> result = new ArrayList<Revisao>();
		Revisao[] list = listarRevisoes();
		for(var res : list) {
			if(res.getDataRealizacao().equals(criacao))
				result.add(res);
		}

		return result.toArray(new Revisao[0]);
	}
	public Revisao[] pesquisarRevisoesObra(int obraID) {
		ArrayList<Revisao> result = new ArrayList<Revisao>();
		Revisao[] list = listarRevisoes();
		for(var res : list) {
			if(res.getObra().getObraId() == obraID)
				result.add(res);
		}

		return result.toArray(new Revisao[0]);
	}
	
	
	
}

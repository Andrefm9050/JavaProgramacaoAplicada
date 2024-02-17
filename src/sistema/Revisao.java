package sistema;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

import users.Gestor;
import users.Revisor;

public class Revisao {
	private Obra obra;
	//private Autor autor; <- Ja temos um autor a partir da obra
	private Gestor gestor;
	private Revisor revisorResponsavel;
	private String numeroSerie; // SEQAAAAMMDDHHMMSS (SEQ = Nrevisoes + 1, AAAAMMDDHHMMSS = Instante)
	private Date dataRealizacao;
	private Timestamp tempoDecorrido;
	private ArrayList<Anotacao> anotacoes; //ou getAnotacoes() seria melhor
	private ArrayList<String> observacoes; //ou getObservacoes() seria melhor
	private double custo;
	private ArrayList<Revisor> revisoresRecusados; //ou getRevisorRecusados() seria melhor
	private EstadoRevisao estado;
	
}

package sistema;

import java.util.ArrayList;

import users.Autor;

public class GestorRevisoes {
	
	public static Revisao[] listarRevisoes() {
		return BDDriver.listarRevisoes();
	}
	
	public static Revisao peesquisarRevisaoPorSerie(String num_serie) {
		Revisao[] lista = listarRevisoes();
		
		for(var revisao : lista) {
			if(revisao.getNumeroSerie().contentEquals(num_serie)) {
				return revisao;
			}
		}
		
		return null;
	}
	
	public static Revisao[] listarRevisoesAutor(Autor a) {
		ArrayList<Revisao> result = new ArrayList<Revisao>();
		Revisao[] revisoes = listarRevisoes();
		
		for(var rev : revisoes) {
			if(rev.getObra().getAutorID() == a.getIdAutor())
				result.add(rev);
		}
		
		return result.toArray(new Revisao[0]);
	}
}

package sistema;

import java.util.ArrayList;

import users.Autor;

public class GestorRevisoes {
	
	public static Revisao[] listarRevisoes() {
		return BDDriver.listarRevisoes();
	}
	
	/**
	 * 
	 * @param num_serie - numero serie da revisao
	 * @return revisao com o numero de serie dado se este existir
	 */
	public static Revisao peesquisarRevisaoPorSerie(String num_serie) {
		Revisao[] lista = listarRevisoes();
		
		for(var revisao : lista) {
			if(revisao.getNumeroSerie().contentEquals(num_serie)) {
				return revisao;
			}
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @param a - Autor da revisao
	 * @return lista de revisoes das obras do autor dado
	 */
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

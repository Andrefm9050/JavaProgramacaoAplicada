package sistema;

import java.util.ArrayList;

import users.Autor;

public class GestorObras {
	
	public static Obra[] listarObras() {
		return BDDriver.listarObras();
	}
	
	/**
	 * 
	 * @param titulo - Titulo da obra
	 * @return obra com o titulo dado se existente
	 */
	public static Obra pesquisarObraPorTitulo(String titulo) {
		Obra[] lista = listarObras();
		for(var obra : lista) {
			if(obra.getTitulo().contentEquals(titulo)){
				return obra;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param a - Autor
	 * @return lista de obras do autor
	 */
	public static Obra[] listarObrasAutor(Autor a) {
		Obra[] list = listarObras();
		ArrayList<Obra> result = new ArrayList<Obra>();
		
		for(Obra obra : list) {
			if(obra.getAutorID() == a.getIdAutor())
			result.add(obra);
		}
		return result.toArray(new Obra[0]);
		
	}
}

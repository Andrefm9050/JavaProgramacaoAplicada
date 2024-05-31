package sistema;

import java.sql.Date;
import java.util.ArrayList;

public class GestorObras {
	
	
	
	public boolean adicionarObra(Obra obra) {
		return BDDriver.adicionarObra(obra);
	}
	
	public Obra[] listarObras() {
		return BDDriver.listarObras();
	}
	
	public Obra[] pesquisarObrasCriacao(Date data) {
		Obra[] lista = listarObras();
		
		ArrayList<Obra> result = new ArrayList<Obra>();
		
		
		for(var obra : lista) {
			result.add(obra);
		}
		
		return result.toArray(new Obra[0]);
	}
	
	public Obra[] pesquisarObrasTitulo(String titulo) {
		return null;
	}
	
	
	public Obra[] pesquisarObrasISBN(int ISBN) {
		return null;
	}
	
	
	public Obra encontrarObraTitulo(String titulo) {
		return null;
	}
	
	
	
}

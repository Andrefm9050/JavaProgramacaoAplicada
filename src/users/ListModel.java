package users;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractListModel;

public class ListModel extends AbstractListModel{

	ArrayList<Listable> lista;
	
	public ListModel(ArrayList<Listable> array) {
		lista = array;
	}
	
	@Override
	public int getSize() {
		
		return lista.size();
	}
	
	public void sort(){
	    Collections.sort(lista);
	    fireContentsChanged(this, 0, lista.size());
	}


	@Override
	public Object getElementAt(int index) {
		
		return (Listable)lista.get(index);
	}

}

package users;

/**
 * Interface necessária para uso com o SelectObj
 * @author andre
 *
 * @param <T>
 */
public interface Listable<T> extends Comparable<T>{
	/**
	 * Segue a estrutura [nome na UI][nome no compareTo]
	 * @return lista de filtragens disponiveis para T objeto
	 */
	public String[][] filtragensDisponiveis(); 
	public void setOrdenacao(String ordenacao);
}

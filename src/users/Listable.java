package users;

public interface Listable<T> extends Comparable<T>{
	public String[][] filtragensDisponiveis();
	public void setOrdenacao(String ordenacao);
}

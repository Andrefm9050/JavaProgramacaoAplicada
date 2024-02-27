package users;

public class UniqueUtilizador extends Utilizador{
	
	private String nif; //max 9 caracteres, unico
	private String telefone; //max 9 caracteres, comeca por 9,2 ou 3
	private String morada;
	/*
	 * Esta classe foi criada para guardar dados e funcoes comuns entre os Autores e Revisores
	 * Estes dados nao estao no Gestor
	 */
	
	public UniqueUtilizador(String login, String password, String nome, EstadoConta estado, String email, String tipo) {
		super(login, password, nome, estado, email, tipo);
		// TODO Auto-generated constructor stub
	}
}

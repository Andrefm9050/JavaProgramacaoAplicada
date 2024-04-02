package users;

public class UniqueUtilizador extends Utilizador{
	
	

	private String nif; //max 9 caracteres, unico
	protected String telefone; //max 9 caracteres, comeca por 9,2 ou 3
	private String morada;
	/*
	 * Esta classe foi criada para guardar dados e funcoes comuns entre os Autores e Revisores
	 * Estes dados nao estao no Gestor
	 */
	
	public UniqueUtilizador(int idUser,String login, String password, String nome, EstadoConta estado, String email, String tipo, String nif, String telefone, String Morada) {
		super(idUser,login, password, nome, estado, email, tipo);
		this.nif = nif;
		this.telefone = telefone;
		this.morada = Morada;
		
		// TODO Auto-generated constructor stub
	}
	
	
	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}
}

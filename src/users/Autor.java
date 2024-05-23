package users;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

import pastaPrincipal.Cliente;
import pastaPrincipal.Main;
import sistema.BDDriver;
import sistema.EstiloLiterario;
import sistema.Obra;
import sistema.Revisao;

import java.util.Random;

public class Autor extends UniqueUtilizador{
	private EstiloLiterario estilo;
	private Date dataInicioAtividade;
	private int idAutor;
	
	public Autor(int idAutor,int idUser,String login, String password, String nome, EstadoConta estado, String email, String tipo, String nif, String telefone, String morada, Date data, EstiloLiterario estilo) {
		super(idUser,login, password, nome, estado, email, tipo, nif, telefone, morada);
		// TODO Auto-generated constructor stub
		this.idAutor = idAutor;
		this.dataInicioAtividade = data;
		this.estilo = estilo;
	}
	
	
	
public int getIdAutor() {
		return idAutor;
	}

public Date getDataInicioAtividade() {
	return dataInicioAtividade;
}
public EstiloLiterario getEstilo() {
	return estilo;
}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}



public static void menuAutor(Autor login1) {
		
		while(true) {
		System.out.println("1- Submeter obra para Revisão \n2- Estado da Revisão \n3- Inserir Obra \n4- Pedido Remover Conta");
		System.out.println("5- Listar Pedidos Revisao das minhas obras");
		System.out.println("6- Listar as minhas obras");
		System.out.println("7- Visualizar dados pessoais");
		System.out.println("8- ALterar dados pessoais");
		System.out.println("9- Pesquisar Obras");
		System.out.println("10- Listar todas as revisões associadas ao utilizador");
		System.out.println("11- Sair");
		
		int opcao = lerDadosInt("Escolha uma das seguintes opções: ");
		
		executaOpcao(opcao, login1);
		
	}
	
}

public static void executaOpcao(int aOpcao, Autor user){
	
	
	switch(aOpcao) {
	case 1: submeterObraRevisao(user.getLogin()); break;
	case 2: estadoRevisao(); break;
	case 3: Cliente.inserirNovaObra(user); break; 
	case 4: GestorContas.pedidoRemoverConta(user.getLogin()); break;
	case 5: listarPedidosRevisao(user); break;
	case 6: Cliente.listarTodasObrasAutor(user); break;
	case 7: Cliente.consultaDadosPessoaisCliente(); break;
	case 8: Cliente.alterarDadosPessoais(user); break;
	case 9: Cliente.pesquisarObra(user); break;
	case 10: Cliente.listarTodasRevisoesAutor(); break;
	case 11: sair(user.getLogin()); break;
	default: erro();
	}
}

static void listarObras(Autor user) {
	String choice = "";
	while(!(choice.contains("d") || choice.contains("t"))) {
		choice = Main.lerDados("Ordenar por Data de submissao(d) ou Titulo(t): ");
	}
	Obra[] list = BDDriver.listarObras();
	ArrayList<Obra> actualList = new ArrayList<Obra>();
	for(var obr : list) {
		if(obr.getAutorID() == user.getIdAutor()) {
			obr.setOrdenacao(choice);
			actualList.add(obr);
		}
	}
	
	Main.SelectionarObjetoMenu(list);
}
static void listarPedidosRevisao(Autor user) {
	String choice = "";
	while(!(choice.contains("d") || choice.contains("n"))) {
		choice = Main.lerDados("Ordenar por Data de criacao(d) ou Numero de serie(n): ");
	}
	
	Revisao[] list = BDDriver.listarRevisoes();
	ArrayList<Revisao> actualList = new ArrayList<Revisao>();
	for(var rev : list) {
		if(rev.getObra().getAutorID() == user.getIdAutor()) {
			rev.setOrdenacao(choice);
			actualList.add(rev);
		}
	}
	Main.SelectionarObjetoMenu(actualList.toArray(new Revisao[0]));
	
}
private static void submeterObraRevisao(String login1) {
	//BDDriver.listarObras();
	int tamanhoArray;
		
	tamanhoArray = BDDriver.listarObras().length;
	Obra[] obrasBuffer = new Obra[tamanhoArray];
	obrasBuffer = BDDriver.listarObras();
	ArrayList<Obra> obraNovo = new ArrayList<Obra>();
	
	for(int i=0; i<tamanhoArray; i++) {
		if(obrasBuffer[i].getAutor().contentEquals(login1) ) {
			obraNovo.add(obrasBuffer[i]);
		}
		
		
	}
	System.out.println("Escolha uma obra para revisão");
	Obra obraN = Main.SelectionarObjetoMenu(obraNovo.toArray(new Obra[0]));
	
	int isbn1 =GestorContas.isbnUnico();
	obraN.setIsbn(isbn1);
	
	int obraID = obraN.getObraId();
	//obraN;
	BDDriver.alterarISBN(isbn1, obraID);
	BDDriver.adicionarRevisao(0, obraID, 0); //numero de Serie, idObra, idGestor
	//Main.SelectionarObjetoMenu(BDDriver.listarRevisoes());
	
	System.out.println("Obra submetida para revisão com sucesso!");		
	
}

//TODO
private static void inserirObra(String login1, Autor userAutor) {
	System.out.println("Inserir Obra");
	while(true) {
		String verify1 = lerDados("Deseja inserir uma obra(s/n): ");
		if(verify1.contentEquals("s")) {
			
			
			//BDDriver.adicionarObra(null);
			
			if(Cliente.inserirNovaObra(userAutor)==true) {
				System.out.println("Obra inserida com sucesso!");
			} else {
				System.out.println("Obra não foi inserida com sucesso!");
			}
			break;
		} else if(verify1.contentEquals("n")) {
			break;
		}
		else {
			System.out.println("Resposta inválida. Insira novamente (s-sim, n-não)");
		}
	}
	
	
	
	
	
}

private static void estadoRevisao() {
	
	
}




private static void pedidoRemoverConta() {
	
}


private static void sair(String login1) {
	System.out.println("Adeus " + login1);
	Main.main(null);							
}

private static void erro() {
	System.out.println("Opção inválida!");
}






private static int lerDadosInt(String aMensagem){
	System.out.println(aMensagem);
	return(new Scanner(System.in)).nextInt();
	
}

public static String lerDados(String aMensagem){
	System.out.print(aMensagem);
	return(new Scanner(System.in)).nextLine();
}

}

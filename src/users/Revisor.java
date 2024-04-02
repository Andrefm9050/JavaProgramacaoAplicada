package users;

import java.util.ArrayList;
import java.util.Scanner;

import pastaPrincipal.Main;
import sistema.BDDriver;
import sistema.EstadoRevisao;
import sistema.GestorContas;
import sistema.Revisao;

public class Revisor extends UniqueUtilizador {
	private EspecializacaoArea areaEspecializado;
	private String formacaoAcademica;
	private int idRevisor;
	
	public Revisor(int idRevisor, int idUser,String login, String password, String nome, EstadoConta estado, String email, String tipo, String nif, String telefone, String morada) {
		super(idUser,login, password, nome, estado, email, tipo, nif, telefone, morada);
		// TODO Auto-generated constructor stub
		this.idRevisor = idRevisor;
	}
	
public static void menuRevisor(String login1) {
		
		while(true) {
		System.out.println("1-Notificações de Revisão \n2-Revisões \n3-Pedido Remover Conta \n4-Sair");
		
		int opcao = lerDadosInt("Escolha uma das seguintes opções: ");
		
		executaOpcao(opcao, login1);
		
	}
	
}

public static void executaOpcao(int aOpcao, String login1){
	
	
	switch(aOpcao) {
	case 1: notificacaoRevisao(login1); break;
	case 2: revisoes(); break;
	case 3: GestorContas.pedidoRemoverConta(login1); break;
	case 4: sair(login1); break;
	default: erro();
	}
}



private static void gerirRevisao() {
	
	
}

private static void notificacaoRevisao(String login1) {
	int tamanhoArray2 = GestorContas.listarRevisores().length;
	Revisor[] revisorBuffer = new Revisor[tamanhoArray2];
	revisorBuffer = GestorContas.listarRevisores();
	int idRevisor = 0;
	for(int i = 0; i<tamanhoArray2; i++) {
		if(revisorBuffer[i].getLogin().contentEquals(login1)) {
			idRevisor = revisorBuffer[i].getIdRevisor();
		}
			
	}
	int tamanhoArray;
		
		tamanhoArray = BDDriver.listarRevisoes().length;
		Revisao[] revisaoBuffer = new Revisao[tamanhoArray];
		revisaoBuffer = BDDriver.listarRevisoes();
		ArrayList<Revisao> revisoes = new ArrayList<Revisao>();
		for(int i = 0; i<tamanhoArray; i++) {
			if(revisaoBuffer[i].getEstado()==EstadoRevisao.aceite ) {
				if(revisaoBuffer[i].getRevisoresRecusados().length!=0) {
					for(int j=0; j<revisaoBuffer[i].getRevisoresRecusados().length;j++) {
						if(revisaoBuffer[i].getRevisoresRecusados()[j] != idRevisor) {
							revisoes.add(revisaoBuffer[i]);	
						}
					}
				} else if(revisaoBuffer[i].getRevisoresRecusados().length==0){
					revisoes.add(revisaoBuffer[i]);
				}
			}
		}
		revisoes.toArray(new Revisao[0]);
		
		//if(revisaoBuffer[i].getRevisoresRecusados()[j]!=idRevisor) {
		System.out.println("Selecione uma revisão para aceitar/rejeitar: ");
		Revisao rev = Main.SelectionarObjetoMenu(revisoes.toArray(new Revisao[0]));
		
		
		
		String verify1 = lerDados("Deseja aceitar fazer esta revisão(s/n): ");
		if(verify1.equalsIgnoreCase("s")) {
			if(rev.getRevisorResponsavel()>0) {
				BDDriver.confirmarRevisorNormal(rev.getRevisaoID(), true);
			} else {
				BDDriver.confirmarRevisorResponsavel(rev.getRevisaoID(), true);
			}
				
			 
			
		} else if(verify1.equalsIgnoreCase("n")){
			BDDriver.confirmarRevisorResponsavel(rev.getRevisaoID(), false);
		}
		//BDDriver.listarRevisoes()
		revisoes.toArray(new Revisao[0]);
	//return utilizadores.toArray(new Gestor[0]);
	//BDDriver.listarRevisoes();
}

private static void revisoes() {
	
}

private static void pedidoRemoverConta() {
	
}




@Override
public String toString() {
	return "Revisor [areaEspecializado=" + areaEspecializado + ", formacaoAcademica=" + formacaoAcademica
			+ ", idRevisor=" + idRevisor + ", telefone=" + telefone + ", getIdRevisor()=" + getIdRevisor()
			+ ", getNif()=" + getNif() + ", getTelefone()=" + getTelefone() + ", getMorada()=" + getMorada()
			+ ", getIdUser()=" + getIdUser() + ", getLogin()=" + getLogin() + ", getNome()=" + getNome()
			+ ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail() + ", getEstado()=" + getEstado()
			+ ", getTipo()=" + getTipo() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
			+ ", hashCode()=" + hashCode() + "]";
}

public int getIdRevisor() {
	return idRevisor;
}

public void setIdRevisor(int idRevisor) {
	this.idRevisor = idRevisor;
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

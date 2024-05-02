package users;

import java.util.ArrayList;
import java.util.Scanner;

import pastaPrincipal.Main;
import sistema.BDDriver;
import sistema.EstadoRevisao;
import sistema.Revisao;

public class Revisor extends UniqueUtilizador {
	private String areaEspecializado;
	private String formacaoAcademica;
	private int idRevisor;
	
	public Revisor(int idRevisor, int idUser,String login, String password, String nome, EstadoConta estado, String email, String tipo, String nif, String telefone, String morada,String formacao,String esp) {
		super(idUser,login, password, nome, estado, email, tipo, nif, telefone, morada);
		// TODO Auto-generated constructor stub
		this.idRevisor = idRevisor;
		this.formacaoAcademica = formacao;
		this.areaEspecializado = esp;
	}
	
	public String getFormacao() {
		return formacaoAcademica;
	}
	public String getArea() {
		return areaEspecializado;
	}
	
public static void menuRevisor(Revisor login1) {
		
		while(true) {
		System.out.println("1-Notificações de Revisão \n2-Revisões \n3-Pedido Remover Conta");
		System.out.println("4-Listar meus pedidos de revisao");
		System.out.println("5-Sair");
		int opcao = lerDadosInt("Escolha uma das seguintes opções: ");
		
		executaOpcao(opcao, login1);
		
	}
	
}

public static void executaOpcao(int aOpcao, Revisor login1){
	
	
	switch(aOpcao) {
	case 1: notificacaoRevisao(login1.getLogin()); break;
	case 2: revisoes(); break;
	case 3: GestorContas.pedidoRemoverConta(login1.getLogin()); break;
	case 4: listarPedidosRevisao(login1); break;
	case 5: sair(login1.getLogin()); break;
	default: erro();
	}
}


static void listarPedidosRevisao(Revisor user) {
	String choice = "";
	while(!(choice.contentEquals("d") || choice.contentEquals("t"))) {
		choice = Main.lerDados("Ordenar listagem por Data de criacao (d) ou Titulo de Obra (t):");
	}
	Revisao[] list = BDDriver.listarRevisoes();
	ArrayList<Revisao> actualList = new ArrayList<Revisao>();
	
	for(var rev : list) {
		if(rev.getRevisorResponsavel() != null && rev.getRevisorResponsavel().getIdRevisor() != user.getIdRevisor()) {
			for(var revisorConf :rev.getRevisoresConfirmados()) {
				if(revisorConf.getIdRevisor() == user.getIdRevisor()) {
					rev.setOrdenacao(choice);
					actualList.add(rev);
				}
			}
		}
		else if(rev.getRevisorResponsavel() != null) {
			rev.setOrdenacao(choice);
			actualList.add(rev);
		}
	}
	
	Main.SelectionarObjetoMenu(actualList.toArray(new Revisao[0]));
	
}

private static void gerirRevisao() {
	
	
}

private static void notificacaoRevisao(String login1) {
	int tamanhoArray2 = GestorContas.listarRevisores().length;
	Revisor[] revisorBuffer = new Revisor[tamanhoArray2];
	revisorBuffer = GestorContas.listarRevisores();
	Revisor Revisor = null;
	for(int i = 0; i<tamanhoArray2; i++) {
		if(revisorBuffer[i].getLogin().contentEquals(login1)) {
			Revisor = revisorBuffer[i];
		}
			
	}
	if(Revisor == null) return;
	
	int tamanhoArray;
		
		tamanhoArray = BDDriver.listarRevisoes().length;
		Revisao[] revisaoBuffer = new Revisao[tamanhoArray];
		revisaoBuffer = BDDriver.listarRevisoes();
		ArrayList<Revisao> revisoes = new ArrayList<Revisao>();
		
		for(int i = 0; i<tamanhoArray; i++) {
			boolean added = false;
			if(revisaoBuffer[i].getEstado()==EstadoRevisao.aceite) {
				if(revisaoBuffer[i].getRevisoresRecusados().length!=0) {
					
					for(int j=0; j<revisaoBuffer[i].getRevisoresRecusados().length;j++) {
						if(revisaoBuffer[i].getRevisoresRecusados()[j].getIdRevisor() != Revisor.getIdRevisor()) {
							revisoes.add(revisaoBuffer[i]);	
							added = true;
						}
					}
				}
				
				if(added) continue;
				
				if(revisaoBuffer[i].getRevisorResponsavel() != null && revisaoBuffer[i].getRevisorResponsavel().getIdRevisor() == Revisor.getIdRevisor()) {
					revisoes.add(revisaoBuffer[i]);
				}
				else {
					Revisor[] revsnaoconfirm = revisaoBuffer[i].getRevisoresNaoConfirmados();
					boolean ishere = false;
					for(int j = 0; j<revsnaoconfirm.length;j++) {
						if(revsnaoconfirm[j].getIdRevisor() == Revisor.getIdRevisor()) {
							ishere = true;
						}
					}
					if(ishere) {
						revisoes.add(revisaoBuffer[i]);
					}
				}
				
				
			}
		}
		revisoes.toArray(new Revisao[0]);
		
		//if(revisaoBuffer[i].getRevisoresRecusados()[j]!=idRevisor) {
		System.out.println("Selecione uma revisão para aceitar/rejeitar: ");
		Revisao rev = Main.SelectionarObjetoMenu(revisoes.toArray(new Revisao[0]));
		
		
		
		String verify1 = lerDados("Deseja aceitar fazer esta revisão(s/n): ");
		if(verify1.equalsIgnoreCase("s")) {
			if(rev.getRevisorResponsavel().getIdRevisor() != Revisor.getIdRevisor()) {
				
				BDDriver.confirmarRevisorNormal(rev.getRevisaoID(),Revisor.getIdRevisor(), true);
			} else {
				BDDriver.confirmarRevisorResponsavel(rev.getRevisaoID(), true);
			}
				
			 System.out.println("Confirmado");
			
		} else if(verify1.equalsIgnoreCase("n")){
			if(rev.getRevisorResponsavel().getIdRevisor() != Revisor.getIdRevisor()) {
				BDDriver.confirmarRevisorNormal(rev.getRevisaoID(),Revisor.getIdRevisor(), false);
			} else {
				BDDriver.confirmarRevisorResponsavel(rev.getRevisaoID(), false);
			}
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

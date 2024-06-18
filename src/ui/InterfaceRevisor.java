package ui;

import javax.swing.*;

import sistema.BDDriver;
import sistema.EstadoRevisao;
import sistema.GestorRevisoes;
import sistema.Revisao;
import users.Gestor;
import users.GestorContas;
import users.Revisor;
import users.Utilizador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterfaceRevisor extends JFrame implements ActionListener,ObjectSelector{
	Revisor userBuffer;
    public InterfaceRevisor(Revisor user){
    	userBuffer = user;
        setVisible(true);
        setSize(800, 600);
        setTitle("Menu Revisor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel jLabel = new JLabel("Menu Revisor");
        jLabel.setBounds(317, 10, 190, 30);
        jLabel.setFont(new Font("Arial", Font.BOLD, 25));
        add(jLabel);

        JButton jButton1 = new JButton("Aceitar/Rejeitar Revisao");
        jButton1.setBounds(245, 60, 302, 40);
        jButton1.setFont(new Font("Arial", Font.BOLD,16));
        jButton1.setForeground(new Color(255,255,255));
        jButton1.setBackground(new Color(0,0,0));
        jButton1.setToolTipText("Visualizar notificações de revisões");
        jButton1.addActionListener(this::AceitarRevisao);
        add(jButton1);

        JButton jButton2 = new JButton("Revisões");
        jButton2.setBounds(245, 100, 302, 40);
        jButton2.setFont(new Font("Arial", Font.BOLD,16));
        jButton2.setForeground(new Color(255,255,255));
        jButton2.setBackground(new Color(0,0,0));
        jButton2.setToolTipText("Visualizar todas as revisões");
        jButton2.addActionListener(this::ListarRevisoes);
        add(jButton2);

        JButton jButton3 = new JButton("Pedido Remover Conta");
        jButton3.setBounds(245, 140, 302, 40);
        jButton3.setFont(new Font("Arial", Font.BOLD,16));
        jButton3.setForeground(new Color(255,255,255));
        jButton3.setBackground(new Color(0,0,0));
        jButton3.setToolTipText("Realizar pedido de remoção de conta");
        jButton3.addActionListener(this::RemoverContaPopUp);
        add(jButton3);
        

        JButton jButton5 = new JButton("Sair");
        jButton5.setBounds(245, 220, 302, 40);
        jButton5.setFont(new Font("Arial", Font.BOLD,16));
        jButton5.setForeground(new Color(255,255,255));
        jButton5.setBackground(new Color(0,0,0));
        jButton5.setToolTipText("Voltar para a página principal");
        add(jButton5);


        JButton perfil = new JButton("Perfil");
        perfil.setBounds(800-150,0,150,150);
        perfil.addActionListener(this::VerPerfil);
        add(perfil);


        jButton5.addActionListener(this::sair);


    }

    void RemoverContaPopUp(ActionEvent e) {
    	int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo pedir a remoção da sua conta?");
    	if(option == 0) {
    		GestorContas.pedidoRemoverConta(userBuffer.getLogin());
    		JOptionPane.showMessageDialog(this, "Sucesso! Ainda pode aceder ao sistema até a remoçao ser confirmada");
    	}
    }
    
    private void sair(ActionEvent actionEvent) {
        getContentPane().removeAll();
        revalidate();
        repaint();
        this.dispose();
        new InterfaceGrafica();
    }

    void VerPerfil(ActionEvent e) {
    	new PaginaPerfil(userBuffer);
    	dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void OnObjectSelected(SelectObj component, Object object) {
    	if(aceitaRejeitar == component) {
    		if(object != null) {
    			Revisao rev = ((Revisao)object);
    			if(JOptionPane.showConfirmDialog(this, "Deseja aceitar fazer esta revisão?") == 0) {
    				if(rev.getRevisorResponsavel().getIdRevisor() != userBuffer.getIdRevisor()) {
    					BDDriver.confirmarRevisorNormal(rev.getRevisaoID(),userBuffer.getIdRevisor(), true);
    				} else {
    					BDDriver.confirmarRevisorResponsavel(rev.getRevisaoID(), true);
    				}
    			}
    			else {
    				if(rev.getRevisorResponsavel().getIdRevisor() != userBuffer.getIdRevisor()) {
    					BDDriver.confirmarRevisorNormal(rev.getRevisaoID(),userBuffer.getIdRevisor(), false);
    				} else {
    					BDDriver.confirmarRevisorResponsavel(rev.getRevisaoID(), false);
    				}
    			}
    		}
    	}
    }
    
    void ListarRevisoes(ActionEvent e) {
    	Revisao[] lista = GestorRevisoes.listarRevisoes(userBuffer);
    	if(lista.length > 0)
    		new SelectObj(this,lista);
    	else
			JOptionPane.showMessageDialog(this, "Nao existe revisoes associadas a esta conta para serem revistas!");
    }
    
    
    SelectObj aceitaRejeitar;

	private void AceitarRevisao(ActionEvent actionevent1) {
		Revisor[] revisorBuffer = GestorContas.listarRevisores();
		Revisor Revisor = null;
		for(int i = 0; i<revisorBuffer.length; i++) {
			if(revisorBuffer[i].getLogin().contentEquals(userBuffer.getLogin())) {
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
			
			if(revisoes.size() > 0)
				aceitaRejeitar = new SelectObj(this,revisoes.toArray(new Revisao[0]));
			else
				JOptionPane.showMessageDialog(this, "Nao existe revisoes associadas a esta conta para serem revistas!");
	}
}

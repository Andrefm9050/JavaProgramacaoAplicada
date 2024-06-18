package ui;

import javax.swing.*;

import sistema.BDDriver;
import sistema.GestorObras;
import sistema.GestorRevisoes;
import users.Autor;
import users.GestorContas;
import sistema.Obra;
import sistema.Revisao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceAutor extends JFrame implements ActionListener,ObjectSelector{
	
	Autor userBuffer;
    public InterfaceAutor(Autor user){
    	userBuffer = user;
        setVisible(true);
        setSize(800, 600);
        setTitle("Menu Autor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel jLabel = new JLabel("Menu Autor");
        jLabel.setBounds(326, 10, 190, 30);
        jLabel.setFont(new Font("Arial", Font.BOLD, 25));
        add(jLabel);

        JButton jButton1 = new JButton("Submeter Obra Para Revisão");
        jButton1.setBounds(237, 60, 318, 40);
        jButton1.setFont(new Font("Arial", Font.BOLD,16));
        jButton1.setForeground(new Color(255,255,255));
        jButton1.setBackground(new Color(0,0,0));
        jButton1.setToolTipText("Realizar o processo de submeter uma obra para revisão");
        jButton1.addActionListener(this::SubmeterObraRevisao);
        add(jButton1);

        JButton jButton2 = new JButton("Estado Da Revisão");
        jButton2.setBounds(237, 100, 318, 40);
        jButton2.setFont(new Font("Arial", Font.BOLD,16));
        jButton2.setForeground(new Color(255,255,255));
        jButton2.setBackground(new Color(0,0,0));
        jButton2.setToolTipText("Visualizar estado de uma revisão");
        jButton2.addActionListener(this::VerEstadoRevisao);
        add(jButton2);

        JButton jButton3 = new JButton("Inserir Obra");
        jButton3.setBounds(237, 140, 318, 40);
        jButton3.setFont(new Font("Arial", Font.BOLD,16));
        jButton3.setForeground(new Color(255,255,255));
        jButton3.setBackground(new Color(0,0,0));
        jButton3.setToolTipText("Realizar o processo de inserir uma nova obra");
        add(jButton3);

        JButton jButton4 = new JButton("Pedido Remover Conta");
        jButton4.setBounds(237, 180, 318, 40);
        jButton4.setFont(new Font("Arial", Font.BOLD,16));
        jButton4.setForeground(new Color(255,255,255));
        jButton4.setBackground(new Color(0,0,0));
        jButton4.setToolTipText("Realizar o processo de pedido de remoção de conta");
        jButton4.addActionListener(this::RemoverContaPopUp);
        add(jButton4);

        JButton jButton5 = new JButton("Listar Pedidos Revisao Minhas Obras");
        jButton5.setBounds(237, 220, 318, 40);
        jButton5.setFont(new Font("Arial", Font.BOLD,16));
        jButton5.setForeground(new Color(255,255,255));
        jButton5.setBackground(new Color(0,0,0));
        jButton5.setToolTipText("Visualizar todos os pedidos de revisao das obras da conta logada");
        add(jButton5);

        JButton jButton6 = new JButton("Listar Minhas Obras");
        jButton6.setBounds(237, 260, 318, 40);
        jButton6.setFont(new Font("Arial", Font.BOLD,16));
        jButton6.setForeground(new Color(255,255,255));
        jButton6.setBackground(new Color(0,0,0));
        jButton6.setToolTipText("Visualizar todas as obras da conta logada");
        add(jButton6);

        JButton jButton7 = new JButton("Sair");
        jButton7.setBounds(237, 300, 318, 40);
        jButton7.setFont(new Font("Arial", Font.BOLD,16));
        jButton7.setForeground(new Color(255,255,255));
        jButton7.setBackground(new Color(0,0,0));
        jButton7.setToolTipText("Volta para a página principal");
        add(jButton7);
        JButton perfil = new JButton("Perfil");
        perfil.setBounds(800-150,0,150,150);
        perfil.addActionListener(this::VerPerfil);
        add(perfil);
        
        
    }
    
    SelectObj estadoRevisao;
    
    void VerEstadoRevisao(ActionEvent e) {
    	estadoRevisao = new SelectObj(this,GestorRevisoes.listarRevisoes(userBuffer));
    }
    
    void RemoverContaPopUp(ActionEvent e) {
    	int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo pedir a remoção da sua conta?");
    	if(option == 0) {
    		GestorContas.pedidoRemoverConta(userBuffer.getLogin());
    		JOptionPane.showMessageDialog(this, "Sucesso! Ainda pode aceder ao sistema até a remoçao ser confirmada");
    	}
    }
    
    void SubmeterObraRevisao(ActionEvent e) {
    	Obra[] list = GestorObras.listarObras();
    	if(list.length > 0) {
    		new SelectObj(this,GestorObras.listarObras(userBuffer));
    	}
    	else {
    		JOptionPane.showMessageDialog(this, "Nao existe obras associadas a esta conta!");
    	}
    	
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
    	if(component == estadoRevisao) {
    		if(object != null)
    		JOptionPane.showMessageDialog(this, "Esta revisão está no estado: " + ((Revisao)object).getEstado());
    		else
    			JOptionPane.showMessageDialog(this, "Opção cancelada");


    		return;
    	}
    	int isbn1 =GestorContas.isbnUnico();
    	((Obra)object).setIsbn(isbn1);
    	
    	int obraID = ((Obra)object).getObraId();
    	//obraN;
    	BDDriver.alterarISBN(isbn1, obraID);
    	BDDriver.adicionarRevisao(0, obraID, 0);
    	JOptionPane.showMessageDialog(this, "Sucesso!");
    }
}

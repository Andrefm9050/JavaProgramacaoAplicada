package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Observacoes extends JFrame implements ActionListener{
	
	JLabel observacoes;
	JTextArea observacoesField;
	
	
	JButton btnMandar;
	JButton btnCancelar;
	
	JFrame context;
	
	public Observacoes(JFrame context){
		setVisible(true);
		setTitle("Observacoes");
		this.context = context;
		Container cont = getContentPane();
		cont.setLayout(new BorderLayout());
		
		JPanel inputs = new JPanel();
		inputs.setLayout(new GridLayout(2,1));
		
		observacoesField = new JTextArea(5,35);
		observacoesField.setFont(new Font("Arial",Font.PLAIN,20));
		
		
		observacoes = new JLabel("Observacoes: ");
		observacoes.setFont(new Font("Arial",Font.PLAIN,20));
		
		inputs.add(observacoes);
		inputs.add(observacoesField);
		
		cont.add(inputs,BorderLayout.CENTER);
		
		JPanel btns = new JPanel();
		btns.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnMandar = new JButton("Mandar");
		btnMandar.addActionListener(this::MandarBtnEvent);
		btnCancelar = new JButton("Sair");
		btnCancelar.addActionListener(this::CancelarBtnEvent);
		btns.add(btnMandar);
		btns.add(btnCancelar);
		
		cont.add(btns,BorderLayout.SOUTH);
		
		pack();
		
	}
	boolean alreadysending = false;
	void MandarBtnEvent(ActionEvent event) {
		if(alreadysending) return;
		
		alreadysending = true;
		
		String content = "Na janela: "+ context.getClass().getSimpleName() + "; Observacao: " +observacoesField.getText(); 
	}
	
	void CancelarBtnEvent(ActionEvent event) {
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

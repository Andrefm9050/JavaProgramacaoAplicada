package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGrafica extends JFrame implements ActionListener,ObjectSelector {

	public InterfaceGrafica() {
		
		setVisible(true);
		setSize(800, 500);
		setTitle("Página Inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);

		JButton jButton = new JButton("Login");
		jButton.setBounds(430, 300, 130, 40);
		jButton.setFont(new Font("Arial", Font.BOLD,20));
		jButton.setForeground(new Color(255,255,255));
		jButton.setBackground(new Color(0,0,0));
		add(jButton);

		JButton jButton1 = new JButton("Registo");
		jButton1.setBounds(230, 300, 130, 40);
		jButton1.setFont(new Font("Arial", Font.BOLD,20));
		jButton1.setForeground(new Color(255,255,255));
		jButton1.setBackground(new Color(0,0,0));


		add(jButton1);


		jButton.addActionListener(this::teste);
		//setVisible(true);


	}

	private void teste(ActionEvent actionEvent) {
		getContentPane().removeAll();
		revalidate();
		repaint();

		setVisible(true);
		setSize(800, 500);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		
		//this.dispose();
		// Opcional: Fechar a janela atual
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null,
				"tudo certo");
		
	}

	@Override
	public void OnObjectSelected(SelectObj comp,Object object) {
		JOptionPane.showMessageDialog(null,
				object != null ? object : "null");
	}
}

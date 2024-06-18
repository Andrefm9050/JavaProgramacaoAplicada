package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sistema.BDDriver;
import sistema.EstiloLiterario;
import sistema.Obra;
import sistema.TipoPublicacao;
import users.EstadoConta;
import users.Utilizador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConfigurarBD extends JFrame implements ActionListener,ObjectSelector{

	JLabel error;

	JLabel ip;
	JLabel port;
	JLabel bd;
	JLabel login;
	JLabel password;

	JTextField ipField;
	JTextField portField;
	JTextField bdField;
	JTextField loginField;
	JTextField passwordField;

    JButton btnMudar;
    JButton btnSair;

	public ConfigurarBD() {
		String[] valores = BDDriver.getConfigValores();
		setVisible(true);
		setSize(800,500);
		setLocationRelativeTo(null);
		setTitle("Página Configuração");
		Container cont = getContentPane();
		cont.setLayout(new BorderLayout());

		JPanel inputs = new JPanel();
		inputs.setLayout(new GridLayout(6,1));

		setBackground(Color.white);
		Font fontL = new Font("Arial",Font.PLAIN,20);

		JPanel inputsIP = new JPanel();
		inputsIP.setLayout(new FlowLayout(FlowLayout.LEFT));

		ip = new JLabel("IP:",SwingConstants.LEFT);
		ip.setFont(fontL);
		ipField = new JTextField(9);
		ipField.setText(valores[0]); //IP

		inputsIP.add(ip);
		inputsIP.add(ipField);

		JPanel inputsPort = new JPanel();
		inputsPort.setLayout(new FlowLayout(FlowLayout.LEFT));

		port = new JLabel("Port:");
		port.setFont(fontL);
		portField = new JTextField(7);
		portField.setText(valores[1]); //port
		inputsPort.add(port);
		inputsPort.add(portField);

		JPanel inputsBD = new JPanel();
		inputsBD.setLayout(new FlowLayout(FlowLayout.LEFT));

		bd = new JLabel("BD: ");
		bd.setFont(fontL);
		bdField = new JTextField(12);
		bdField.setText(valores[2]);
		inputsBD.add(bd);
		inputsBD.add(bdField);

		JPanel inputsLogin = new JPanel();
		inputsLogin.setLayout(new FlowLayout(FlowLayout.LEFT));

		login = new JLabel("Login: ");
		login.setFont(fontL);
		loginField = new JTextField(24);
		loginField.setText(valores[3]);
		inputsLogin.add(login);
		inputsLogin.add(loginField);

		JPanel inputsPassword = new JPanel();
		inputsPassword.setLayout(new FlowLayout(FlowLayout.LEFT));

		password = new JLabel("Password: ");
		password.setFont(fontL);
		passwordField = new JTextField(24);
		passwordField.setText(valores[4]);
		inputsPassword.add(password);
		inputsPassword.add(passwordField);

		error = new JLabel();
		inputs.add(inputsIP);
		inputs.add(inputsPort);
		inputs.add(inputsBD);
		inputs.add(inputsLogin);
		inputs.add(inputsPassword);
		inputs.add(error);

		cont.add(inputs,"West");

		JPanel buttonsMenu = new JPanel();
		buttonsMenu.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btnMudar = new JButton("Aceitar");
		btnMudar.addActionListener(this::AceitarBtnEvent);
		btnSair = new JButton("Sair (Fechar Aplicação)");
		btnSair.addActionListener(this::SairAplicacao);

		buttonsMenu.add(btnMudar);
		buttonsMenu.add(btnSair);

		cont.add(buttonsMenu,"South");


		cont.revalidate();
		cont.repaint();

		addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {

		    	System.exit(0);

		    }
		});



	}

	void AceitarBtnEvent(ActionEvent event) {
		BDDriver.saveConfigValues(ipField.getText(),portField.getText(),loginField.getText(),passwordField.getText(),bdField.getText());
		if(BDDriver.configurarDriver(ipField.getText(),portField.getText(),loginField.getText(),passwordField.getText(),bdField.getText())) {
			//Obra[] list = new Obra[2];
			//list[0] = new Obra(1,"autor",2,"titulo","sub",EstiloLiterario.drama,TipoPublicacao.capaDura,5,6,2,6,new Date(100), new Date(200));
			//list[1] = new Obra(12,"autorB",2,"tituloB","sub",EstiloLiterario.drama,TipoPublicacao.capaDura,5,6,2,6,new Date(25000), new Date(25000));

			//new SelectObj(this,list);

			new InterfaceGrafica();

			dispose();
		}
		else {
			error.setText("Erro, não foi possivel realizar uma conexão á BD configurada");
			error.setFont(new Font("Arial",Font.PLAIN,20));
			error.setForeground(Color.RED);

			getContentPane().revalidate();
			getContentPane().repaint();
		}
	}

	void SairAplicacao(ActionEvent event) {
		dispose();
		System.exit(0);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnObjectSelected(SelectObj component, Object object) {
		// TODO Auto-generated method stub

	}


}

package ui;


import pastaPrincipal.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class InterfaceGrafica extends JFrame implements ActionListener,ObjectSelector {

	JTextField text;
	JTextField text1;
	JTextField text2;
	JTextField text3;
	JTextField text4;
	JTextField text5;
	JTextField text6;
	JTextField text7;
	JTextField text8;
	JTextField text9;
	JTextField text10;

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
		jButton.setToolTipText("Ao pressionar o botão Login irá ser redirecionado para página de Login");
		add(jButton);

		JButton jButton1 = new JButton("Registo");
		jButton1.setBounds(230, 300, 130, 40);
		jButton1.setFont(new Font("Arial", Font.BOLD,20));
		jButton1.setForeground(new Color(255,255,255));
		jButton1.setBackground(new Color(0,0,0));
		jButton1.setToolTipText("Ao pressionar o botão Registo irá ser redirecionado  para página de Registo");

		JLabel jLabel = new JLabel("Escolha uma das opções - Registo ou Login");
		jLabel.setBounds(200, 10, 400, 400);
		jLabel.setFont(new Font("Arial", Font.BOLD, 18));

		add(jLabel);
		add(jButton1);


		//jButton.addActionListener(this::teste);

		jButton1.addActionListener(this::registo);
		jButton.addActionListener(this::login);

		//setVisible(true);


	}
	
	/*
	@Override
	public void paint(Graphics g) { //<- Este código é o codigo teste para mostrar uma imagem
		super.paint(g);
			g.drawImage(new ImageIcon(BDDriver.listarUtilizadores()[0].getImage()).getImage(),
					HEIGHT,
					WIDTH,
					120,
					120,
					this);	
	}
	*/

	/*
	@Override
	public void paint(Graphics g) { //<- Este código é o codigo teste para mostrar uma imagem
		super.paint(g);
			g.drawImage(new ImageIcon(BDDriver.listarUtilizadores()[0].getImage()).getImage(),
					HEIGHT,
					WIDTH,
					120,
					120,
					this);
	}
	*/

	/*
	@Override
	public void paint(Graphics g) { //<- Este código é o codigo teste para mostrar uma imagem
		super.paint(g);
			g.drawImage(new ImageIcon(BDDriver.listarUtilizadores()[0].getImage()).getImage(),
					HEIGHT,
					WIDTH,
					120,
					120,
					this);
	}
	*/

	private void registo(ActionEvent actionEvent) {
		getContentPane().removeAll();
		revalidate();
		repaint();

		setVisible(true);
		setSize(800, 500);
		setTitle("Registo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);

		JLabel jLabel = new JLabel("Registo");
		jLabel.setBounds(350, 0, 200, 50);
		jLabel.setFont(new Font("Arial", Font.BOLD, 25));
		add(jLabel);

		JLabel jLabel1 = new JLabel("Selecione Tipo de conta");
		jLabel1.setBounds(300, 60, 300, 100);
		jLabel1.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel1);


		JButton jButton = new JButton("Autor");
		jButton.setBounds(260, 170, 130, 40);
		jButton.setFont(new Font("Arial", Font.BOLD,20));
		jButton.setForeground(new Color(255,255,255));
		jButton.setBackground(new Color(0,0,0));
		jButton.setToolTipText("Escolher tipo de conta Autor");
		add(jButton);

		JButton jButton1 = new JButton("Revisor");
		jButton1.setBounds(400, 170, 130, 40);
		jButton1.setFont(new Font("Arial", Font.BOLD,20));
		jButton1.setForeground(new Color(255,255,255));
		jButton1.setBackground(new Color(0,0,0));
		jButton1.setToolTipText("Escolher tipo de conta Revisor");
		add(jButton1);

		JButton jButton2 = new JButton("Back");
		jButton2.setBounds(330, 220, 130, 40);
		jButton2.setFont(new Font("Arial", Font.BOLD,20));
		jButton2.setForeground(new Color(255,255,255));
		jButton2.setBackground(new Color(0,0,0));
		jButton2.setToolTipText("Voltar para página principal");
		add(jButton2);

		//text.getText();
		//text1.getText();
		//text2.getText();
		//text3.getText();
		//text4.getText();


		jButton.addActionListener(this::registoAutor);
		jButton1.addActionListener(this::registoRevisor);
		jButton2.addActionListener(this::voltarPaginaInicial);


		//Main.registo(text.getText(), false, text1.getText(), text2.getText(), text3.getText(), text4.getText());

	}

	private void registoRevisor(ActionEvent actionEvent) {
		getContentPane().removeAll();
		revalidate();
		repaint();

		setVisible(true);
		setSize(800, 500);
		setTitle("Registo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);

		JLabel jLabel = new JLabel("Registo");
		jLabel.setBounds(350, 0, 200, 50);
		jLabel.setFont(new Font("Arial", Font.BOLD, 25));
		add(jLabel);


		JLabel jLabel2 = new JLabel("Username: ");
		jLabel2.setBounds(307, 30, 300, 100);
		jLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel2);

		text1 = new JTextField("username");
		text1.setBounds(400, 70, 140, 20);
		text1.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text1.setToolTipText("Insira o seu username");
		add(text1);

		JLabel jLabel3 = new JLabel("Password: ");
		jLabel3.setBounds(309, 50, 300, 100);
		jLabel3.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel3);

		text2 = new JTextField("password");
		text2.setBounds(400, 90, 140, 20);
		text2.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text2.setToolTipText("Insira a sua password");
		add(text2);

		JLabel jLabel4 = new JLabel("Nome: ");
		jLabel4.setBounds(341, 70, 300, 100);
		jLabel4.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel4);

		text3 = new JTextField("nome");
		text3.setBounds(400, 110, 140, 20);
		text3.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text3.setToolTipText("Insira o seu nome");
		add(text3);

		JLabel jLabel5 = new JLabel("Email: ");
		jLabel5.setBounds(344, 90, 300, 100);
		jLabel5.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel5);

		text4 = new JTextField("email");
		text4.setBounds(400, 130, 140, 20);
		text4.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text4.setToolTipText("Insira o seu email no formato axzc@exmail.com");
		add(text4);

		JLabel jLabel6 = new JLabel("Morada: ");
		jLabel6.setBounds(329, 110, 300, 100);
		jLabel6.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel6);

		text5 = new JTextField("morada");
		text5.setBounds(400, 150, 140, 20);
		text5.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text5.setToolTipText("Insira a sua morada");
		add(text5);

		JLabel jLabel7 = new JLabel("NIF: ");
		jLabel7.setBounds(360, 130, 300, 100);
		jLabel7.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel7);

		text6 = new JTextField("nif");
		text6.setBounds(400, 170, 140, 20);
		text6.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text6.setToolTipText("Insira o seu numero nif, deverá ter 9 algarismos");
		add(text6);

		JLabel jLabel8 = new JLabel("Telefone: ");
		jLabel8.setBounds(320, 150, 300, 100);
		jLabel8.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel8);

		text7 = new JTextField("telefone");
		text7.setBounds(400, 190, 140, 20);
		text7.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text7.setToolTipText("Insira o seu numero de telefone, deverá começar por 9, 2 ou 3 e ter 9 algarismos");
		add(text7);

		JLabel jLabel9 = new JLabel("Formacao: ");
		jLabel9.setBounds(305, 170, 300, 100);
		jLabel9.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel9);

		text8 = new JTextField("formacao");
		text8.setBounds(400, 210, 140, 20);
		text8.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text8.setToolTipText("Insira a sua formacao");
		add(text8);

		JLabel jLabel11 = new JLabel("Area: ");
		jLabel11.setBounds(350, 190, 300, 100);
		jLabel11.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel11);

		text9 = new JTextField("area especializacao");
		text9.setBounds(400, 230, 140, 20);
		text9.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text9.setToolTipText("Insira a sua area de especializacao");
		add(text9);

		JButton jButton = new JButton("Back");
		jButton.setBounds(260, 300, 130, 40);
		jButton.setFont(new Font("Arial", Font.BOLD,20));
		jButton.setForeground(new Color(255,255,255));
		jButton.setBackground(new Color(0,0,0));
		jButton.setToolTipText("Voltar para página principal");
		add(jButton);

		JButton jButton1 = new JButton("Ok");
		jButton1.setBounds(400, 300, 130, 40);
		jButton1.setFont(new Font("Arial", Font.BOLD,20));
		jButton1.setForeground(new Color(255,255,255));
		jButton1.setBackground(new Color(0,0,0));
		jButton1.setToolTipText("Confirmar registo");
		add(jButton1);
		
		JButton imagem = new JButton("Selecionar Imagem");
		imagem.setBounds(400, 350, 130, 40);
		imagem.setFont(new Font("Arial", Font.BOLD,10));
		imagem.setForeground(new Color(255,255,255));
		imagem.setBackground(new Color(0,0,0));
		imagem.setToolTipText("Confirmar registo");
		imagem.addActionListener(this::SelectImage);
		add(imagem);
		
		try {
			pic = new JLabel(new ImageIcon(ImageIO.read(new File("Images/Default.png")).getScaledInstance(100, 100, Image.SCALE_FAST)));
			pic.setBounds(300, 350, 100, 100);
			add(pic);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//text.getText();
		//text1.getText();
		//text2.getText();
		//text3.getText();
		//text4.getText();

		jButton.addActionListener(this::voltarPaginaInicial);
		jButton1.addActionListener(this::realizarRegisto1);
		///this.dispose();
		//Main.registo(text.getText(), false, text1.getText(), text2.getText(), text3.getText(), text4.getText());
		//Main.registo("Revisor", false, text1.getText(), text2.getText(), text3.getText(), text4.getText(), text5.getText(), text6.getText(),text7.getText(), text8.getText(), text9.getText(), text10.getText());

	}
	JLabel pic;
	byte[] imageSelected;
	void SelectImage(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		int response = fileChooser.showOpenDialog(null);
		
		if(response == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			try {
				BufferedImage img = ImageIO.read(file);
				if(img != null) {
					//Imagem!
					//System.out.println("Image!");
					imageSelected = Files.readAllBytes(file.toPath());
					pic.setIcon(new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_FAST)));
					super.update(getGraphics());

				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void realizarRegisto1(ActionEvent actionEvent) {
		Main.registo("Revisor", false, text1.getText(),text2.getText(), text3.getText(), text4.getText(), text5.getText(), text6.getText(),text7.getText(), text8.getText(), text9.getText(),null, imageSelected);
		getContentPane().removeAll();
		revalidate();
		repaint();
		this.dispose();
		new InterfaceGrafica();
	}

	private void registoAutor(ActionEvent actionEvent) {
		getContentPane().removeAll();
		revalidate();
		repaint();

		setVisible(true);
		setSize(800, 500);
		setTitle("Registo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);

		JLabel jLabel = new JLabel("Registo");
		jLabel.setBounds(350, 0, 200, 50);
		jLabel.setFont(new Font("Arial", Font.BOLD, 25));
		add(jLabel);


		JLabel jLabel2 = new JLabel("Username: ");
		jLabel2.setBounds(307, 30, 300, 100);
		jLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel2);

		text1 = new JTextField("username");
		text1.setBounds(400, 70, 140, 20);
		text1.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text1.setToolTipText("Insira o seu username");
		add(text1);

		JLabel jLabel3 = new JLabel("Password: ");
		jLabel3.setBounds(309, 50, 300, 100);
		jLabel3.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel3);

		text2 = new JTextField("password");
		text2.setBounds(400, 90, 140, 20);
		text2.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text2.setToolTipText("Insira a sua password");
		add(text2);

		JLabel jLabel4 = new JLabel("Nome: ");
		jLabel4.setBounds(341, 70, 300, 100);
		jLabel4.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel4);

		text3 = new JTextField("nome");
		text3.setBounds(400, 110, 140, 20);
		text3.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text3.setToolTipText("Insira o seu nome");
		add(text3);

		JLabel jLabel5 = new JLabel("Email: ");
		jLabel5.setBounds(344, 90, 300, 100);
		jLabel5.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel5);

		text4 = new JTextField("email");
		text4.setBounds(400, 130, 140, 20);
		text4.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text4.setToolTipText("Insira o seu email no formato axzc@exmail.com");
		add(text4);

		JLabel jLabel6 = new JLabel("Morada: ");
		jLabel6.setBounds(329, 110, 300, 100);
		jLabel6.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel6);

		text5 = new JTextField("morada");
		text5.setBounds(400, 150, 140, 20);
		text5.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text5.setToolTipText("Insira a sua morada");
		add(text5);

		JLabel jLabel7 = new JLabel("NIF: ");
		jLabel7.setBounds(360, 130, 300, 100);
		jLabel7.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel7);

		text6 = new JTextField("nif");
		text6.setBounds(400, 170, 140, 20);
		text6.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text6.setToolTipText("Insira o seu numero nif, deverá ter 9 algarismos");
		add(text6);

		JLabel jLabel8 = new JLabel("Telefone: ");
		jLabel8.setBounds(320, 150, 300, 100);
		jLabel8.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel8);

		text7 = new JTextField("telefone");
		text7.setBounds(400, 190, 140, 20);
		text7.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text7.setToolTipText("Insira o seu numero de telefone, deverá começar por 9, 2 ou 3 e ter 9 algarismos");
		add(text7);

		JLabel jLabel9 = new JLabel("Data Inicio Atividade: ");
		jLabel9.setBounds(225, 170, 300, 100);
		jLabel9.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel9);

		text8 = new JTextField("aaaa-mm-dd");
		text8.setBounds(400, 210, 140, 20);
		text8.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text8.setToolTipText("Insira a data de inicio de atividade no formato aaaa-mm-dd");
		add(text8);

		JLabel jLabel11 = new JLabel("Estilo Literario: ");
		jLabel11.setBounds(275, 190, 300, 100);
		jLabel11.setFont(new Font("Arial", Font.PLAIN, 18));
		add(jLabel11);

		text10 = new JTextField("estilo literario");
		text10.setBounds(400, 230, 140, 20);
		text10.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text10.setToolTipText("Insira o seu estilo literario");
		add(text10);


		JButton jButton = new JButton("Back");
		jButton.setBounds(260, 300, 130, 40);
		jButton.setFont(new Font("Arial", Font.BOLD,20));
		jButton.setForeground(new Color(255,255,255));
		jButton.setBackground(new Color(0,0,0));
		jButton.setToolTipText("Voltar para página principal");
		add(jButton);



		JButton jButton1 = new JButton("Ok");
		jButton1.setBounds(400, 300, 130, 40);
		jButton1.setFont(new Font("Arial", Font.BOLD,20));
		jButton1.setForeground(new Color(255,255,255));
		jButton1.setBackground(new Color(0,0,0));
		jButton.setToolTipText("Confirmar registo");
		add(jButton1);
		
		JButton imagem = new JButton("Selecionar Imagem");
		imagem.setBounds(400, 350, 130, 40);
		imagem.setFont(new Font("Arial", Font.BOLD,10));
		imagem.setForeground(new Color(255,255,255));
		imagem.setBackground(new Color(0,0,0));
		imagem.setToolTipText("Confirmar registo");
		imagem.addActionListener(this::SelectImage);
		add(imagem);
		
		try {
			pic = new JLabel(new ImageIcon(ImageIO.read(new File("Images/Default.png")).getScaledInstance(100, 100, Image.SCALE_FAST)));
			pic.setBounds(300, 350, 100, 100);
			add(pic);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//text.getText();
		//text1.getText();
		//text2.getText();
		//text3.getText();
		//text4.getText();

		//Main.registo(text.getText(), false, text1.getText(), text2.getText(), text3.getText(), text4.getText());
		jButton.addActionListener(this::voltarPaginaInicial);
		jButton1.addActionListener(this::realizarRegisto);
		///this.dispose();
		//Main.registo("Autor", false, text1.getText(), text2.getText(), text3.getText(), text4.getText(), text5.getText(), text6.getText(),text7.getText(), text8.getText(), text9.getText(), text10.getText());

	}

	private void realizarRegisto(ActionEvent actionEvent) {
		Main.registo("Autor", false, text1.getText(),text2.getText(), text3.getText(), text4.getText(), text5.getText(), text6.getText(),text7.getText(), text8.getText(), null, text10.getText(),imageSelected);
		//Main.registo("Revisor", false, text1.getText(),text2.getText(), text3.getText(), text4.getText(), text5.getText(), text6.getText(),text7.getText(), text8.getText(), text9.getText(), null);
		getContentPane().removeAll();
		revalidate();
		repaint();
		this.dispose();
		new InterfaceGrafica();

	}

	private void login(ActionEvent actionEvent) {

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

		JButton jButton = new JButton("Ok");
		jButton.setBounds(420, 240, 130, 40);
		jButton.setFont(new Font("Arial", Font.BOLD,20));
		jButton.setForeground(new Color(255,255,255));
		jButton.setBackground(new Color(0,0,0));
		jButton.setToolTipText("Confirmar Login");

		JButton jButton1 = new JButton("Back");
		jButton1.setBounds(240, 240, 130, 40);
		jButton1.setFont(new Font("Arial", Font.BOLD,20));
		jButton1.setForeground(new Color(255,255,255));
		jButton1.setBackground(new Color(0,0,0));
		jButton1.setToolTipText("Voltar para página principal");


		JLabel jLabel = new JLabel("Username: ");
		jLabel.setBounds(300, 10, 300, 300);
		jLabel.setFont(new Font("Arial", Font.PLAIN, 15));

		text = new JTextField("username");
		text.setBounds(380, 152, 100, 17);
		text.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text.setToolTipText("Insira o seu username");


		JLabel jLabel1 = new JLabel("Password:");
		jLabel1.setBounds(300, 50, 300, 300);
		jLabel1.setFont(new Font("Arial", Font.PLAIN, 15));

		text1 = new JTextField("password");
		text1.setBounds(380, 190, 100, 17);
		text1.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
		text1.setToolTipText("Insira a sua password");


		//text.getText();
		//text1.getText();

		add(jButton1);
		add(jButton);
		add(text);
		add(text1);
		add(jLabel);
		add(jLabel1);

		jButton.addActionListener(this::dadosObtidos);
		jButton1.addActionListener(this::voltarPaginaInicial);


	}

	private void dadosObtidos(ActionEvent actionEvent) {
		getContentPane().removeAll();
		revalidate();
		repaint();

		Main.login(text.getText(), text1.getText());
		System.out.println(text.getText());
		System.out.println(text1.getText());

		this.dispose();


	}


	public void voltarPaginaInicial(ActionEvent actionEvent){
		getContentPane().removeAll();
		revalidate();
		repaint();
		this.dispose();
		new InterfaceGrafica();
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

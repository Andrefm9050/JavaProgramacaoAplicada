package ui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import users.Autor;
import users.Gestor;
import users.GestorContas;
import users.Revisor;
import users.Utilizador;

/**
 * Classe tipo JFrame responsavel como página de perfil e configuração de 3 detalhes de conta de utilizador, ao sair da página, este é redirecionado para a pagina principal do utilizador
 * @author andre
 *
 */
public class PaginaPerfil extends JFrame{
	JLabel currentImage;
	JTextField passInput;
	JTextField userInput;
	Utilizador uBuffer;
	Gestor gestorMode;
	
	public PaginaPerfil(Gestor g, Utilizador u) {
		this(u);
		gestorMode = g;
	}
	
	public PaginaPerfil(Utilizador u) {
		uBuffer = u;
        setVisible(true);
        setSize(800, 600);
        setLayout(new GridLayout(4,3));
        JLabel title = new JLabel("Página da Conta");
        title.setFont(new Font("Arial", Font.PLAIN,24));
        add(title);
        JPanel textInputs = new JPanel();
        textInputs.setLayout(new GridLayout(1,2));
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout());
        JLabel username = new JLabel("Nome");
        usernamePanel.add(username);
        userInput = new JTextField(16);
        userInput.setText(u.getNome());
        usernamePanel.add(userInput);
        JButton userBtn = new JButton("Mudar");
        userBtn.addActionListener(this::mudarnome);
        usernamePanel.add(userBtn);
        
        textInputs.add(usernamePanel);
        
        JPanel passPanel = new JPanel();
        passPanel.setLayout(new FlowLayout());
        JLabel password = new JLabel("Password");
        passPanel.add(password);
        passInput = new JTextField(16);
        passInput.setText(u.getPassword());
        JButton passBtn = new JButton("Mudar");
        passBtn.addActionListener(this::mudarpassword);
        passPanel.add(passInput);
        passPanel.add(passBtn);
        textInputs.add(passPanel);
        
        JPanel imageInput = new JPanel();
        imageInput.setLayout(new FlowLayout());
        currentImage = new JLabel(new ImageIcon(u.getImage().getScaledInstance(100, 100, Image.SCALE_FAST)));
        imageInput.add(currentImage);
        JButton selecionarImagem = new JButton("Editar Imagem");
        selecionarImagem.addActionListener(this::SelecionarImagem);
        imageInput.add(selecionarImagem);
        
        add(textInputs);
        add(imageInput);
        JButton sair = new JButton("Sair");
        sair.addActionListener(this::sair);
        add(sair);
        
		addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		      if(alreadyclosing) return; //<- Nao queremos processar este dispose event
		      //Se já estamos a fechar por outros meios
		      
		      if(gestorMode != null) {
		    	  new InterfaceGestor(gestorMode);
		    	  return;
		      }

				if(uBuffer instanceof Gestor) {
					new InterfaceGestor((Gestor)uBuffer);
				}
				else if(uBuffer instanceof Autor) {
					new InterfaceAutor((Autor)uBuffer);
				}
				else if(uBuffer instanceof Revisor) {
					new InterfaceRevisor((Revisor)uBuffer);
				}

		    }
		});
        
	}
	boolean alreadyclosing;
	
	/**
	 * Evento para confirmar a mudança de nome
	 * @param e
	 */
	void mudarnome(ActionEvent e) {
		if(GestorContas.definirNomeUtilizador(uBuffer.getIdUser(),userInput.getText())) {
			uBuffer.setNome(userInput.getText());
			JOptionPane.showMessageDialog(this, "Sucesso!");
		}
	}
	
	/**
	 * Evento para confirmar a mudança de password
	 * @param e
	 */
	void mudarpassword(ActionEvent e) {
		if(GestorContas.definirPasswordUtilizador(uBuffer.getIdUser(),passInput.getText())) {
			uBuffer.setPassword(passInput.getText());
			JOptionPane.showMessageDialog(this, "Sucesso!");
		}
	}
	/**
	 * Evento para sair da janela
	 * @param e
	 */
	void sair(ActionEvent e) {
		alreadyclosing = true;
		
	      if(gestorMode != null) {
	    	  new InterfaceGestor(gestorMode);
	    	  dispose(); 
	    	  return;
	      }
		
		if(uBuffer instanceof Gestor) {
			new InterfaceGestor((Gestor)uBuffer);
		}
		else if(uBuffer instanceof Autor) {
			new InterfaceAutor((Autor)uBuffer);
		}
		else if(uBuffer instanceof Revisor) {
			new InterfaceRevisor((Revisor)uBuffer);
		}
		dispose();
	}
	
	/**
	 * Evento para selecionar imagem
	 * @param e
	 */
	void SelecionarImagem(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		int response = fileChooser.showOpenDialog(null);
		
		if(response == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			try {
				BufferedImage img = ImageIO.read(file);
				if(img != null) {
					
					if(GestorContas.definirImagemUtilizador(uBuffer.getIdUser(),Files.readAllBytes(file.toPath()))){
						uBuffer.setImage(Files.readAllBytes(file.toPath()));
						JOptionPane.showMessageDialog(this, "Sucesso!");
						currentImage.setIcon(new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_FAST)));
						super.update(getGraphics());
					}
					
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}

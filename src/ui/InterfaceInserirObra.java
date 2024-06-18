package ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sistema.TipoPublicacao;
import users.Autor;
import sistema.EstiloLiterario;
import sistema.GestorObras;
import sistema.Obra;

public class InterfaceInserirObra extends JFrame{
	
	Autor userBuffer;
	JTextField nPaginasField;
	JTextField nPalavrasField;
	JTextField tituloField;
	JTextField subTituloField;
	JComboBox tipoField;
	JComboBox estiloField;
	JTextField nEdicaoField;
	
	public InterfaceInserirObra(Autor user) {
		userBuffer = user;
		setVisible(true);
		setSize(800,500);
		setLocationRelativeTo(null);
		setLayout(new GridLayout (9,1));
		JLabel title = new JLabel("Inserir Obra");
		add(title);
		
		JPanel paginasPanel = new JPanel();
		paginasPanel.setLayout(new FlowLayout());
		JLabel paginasL = new JLabel("N Páginas");
		paginasPanel.add(paginasL);
		nPaginasField = new JTextField(8);
		paginasPanel.add(nPaginasField);
		add(paginasPanel);
		
		JPanel palavrasPanel = new JPanel();
		palavrasPanel.setLayout(new FlowLayout());
		JLabel palavrasL = new JLabel("N Palavras");
		palavrasPanel.add(palavrasL);
		nPalavrasField = new JTextField(8);
		palavrasPanel.add(nPalavrasField);
		add(palavrasPanel);
		
		JPanel tituloPanel = new JPanel();
		tituloPanel.setLayout(new FlowLayout());
		JLabel tituloL = new JLabel("Titulo");
		tituloPanel.add(tituloL);
		tituloField = new JTextField(8);
		tituloPanel.add(tituloField);
		add(tituloPanel);
		
		JPanel subtituloPanel = new JPanel();
		subtituloPanel.setLayout(new FlowLayout());
		JLabel subTituloL = new JLabel("SubTitulo");
		subtituloPanel.add(subTituloL);
		subTituloField = new JTextField(8);
		subtituloPanel.add(subTituloField);
		add(subtituloPanel);
		
		JPanel tipoPanel = new JPanel();
		tipoPanel.setLayout(new FlowLayout());
		JLabel tipoL = new JLabel("Tipo");
		tipoPanel.add(tipoL);
		tipoField = new JComboBox(new String[] {TipoPublicacao.capaDura.toString(),
				TipoPublicacao.deBolso.toString(),
				TipoPublicacao.ebook.toString()});
		tipoPanel.add(tipoField);
		add(tipoPanel);
		
		JPanel estiloPanel = new JPanel();
		estiloPanel.setLayout(new FlowLayout());
		JLabel estiloL = new JLabel("Estilo");
		estiloPanel.add(estiloL);
		estiloField = new JComboBox(new String[] {EstiloLiterario.drama.toString(),
				EstiloLiterario.ficcao.toString(),
				EstiloLiterario.lirico.toString(),
				EstiloLiterario.narrativo.toString(),
				EstiloLiterario.thriller.toString()
		});
		estiloPanel.add(estiloField);
		add(estiloPanel);
		
		JPanel edicaoPanel = new JPanel();
		edicaoPanel.setLayout(new FlowLayout());
		JLabel edicaoL = new JLabel("Edicao");
		edicaoPanel.add(edicaoL);
		nEdicaoField = new JTextField(8);
		edicaoPanel.add(nEdicaoField);
		add(edicaoPanel);
		
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new FlowLayout());
		JButton confirm = new JButton("Adicionar");
		JButton cancelar = new JButton("Cancelar");
		confirm.addActionListener(this::AdicionarEvento);
		cancelar.addActionListener(this::CancelarEvento);
		optionsPanel.add(confirm);
		optionsPanel.add(cancelar);
		add(optionsPanel);
		
	}
	
	
	void AdicionarEvento(ActionEvent e) {
		int option = JOptionPane.showConfirmDialog(this, "Tem a certeza?");
    	if(option == 0) {
    		Obra obj = new Obra(
    				0,
    				userBuffer.getNome(),
    				userBuffer.getIdAutor(),
    				tituloField.getText(),
    				subTituloField.getText(),
    				EstiloLiterario.stringToEstilo(estiloField.getSelectedItem().toString()),
    				TipoPublicacao.stringToTipo(tipoField.getSelectedItem().toString()),
    				Integer.parseInt(nPaginasField.getText()),
    				Integer.parseInt(nPalavrasField.getText()),
    				0,
    				Integer.parseInt(nEdicaoField.getText()),
    				null,
    				null
    				);
    		
    		if(GestorObras.adicionarObra(obj)) {
    			JOptionPane.showMessageDialog(this, "Sucesso!");
    			new InterfaceAutor(userBuffer);
    			dispose();
    		}
    		else {
    			JOptionPane.showMessageDialog(this, "Erro ao adicionar Obra!");
    		}
    	}
	}
	void CancelarEvento(ActionEvent e) {
		int option = JOptionPane.showConfirmDialog(this, "Tem a certeza?");
    	if(option == 0) {
    		new InterfaceAutor(userBuffer);
    		dispose();
    	}
	}
}

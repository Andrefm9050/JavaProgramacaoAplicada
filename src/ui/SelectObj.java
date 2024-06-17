package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import sistema.Obra;

public class SelectObj extends JFrame implements ActionListener{

	ObjectSelector o;
	JButton teste = new JButton("Test");
	JList<?> list;
	
	public <T> SelectObj(ObjectSelector o, Comparable<T>[] objects) {

		setVisible(true);
		setSize(800, 500);
		setTitle("Selecionar Objeto");
		setLayout(new BorderLayout());
		
		JLabel label = new JLabel("Selecionar Objeto do tipo: " + objects[0].getClass().getSimpleName());
		
		add(label,BorderLayout.NORTH);
		
		list = new JList(objects);
		list.setVisibleRowCount(10);
		JScrollPane jcp = new JScrollPane(list);
		add(jcp,BorderLayout.CENTER);
		
		this.o = o;
		teste.setFont(new Font("Arial", Font.BOLD,20));
		teste.setSize(130,40);

		add(teste,BorderLayout.SOUTH);
		teste.addActionListener(this::selecionado);
		SelectObj comp = this;
		addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		      if(alreadyclosing) return; //<- Nao queremos processar este dispose event
		      //Se já estamos a fechar por outros meios
		      
		      o.OnObjectSelected(comp, null);
		      
		    }
		});
		
	}
	boolean alreadyclosing;
	private void selecionado(ActionEvent e) {
		alreadyclosing = true;
		Object result;
		if(list != null)
			result = list.getSelectedValue();
		else
			result = null;
		o.OnObjectSelected(this,result);
		
		this.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
	
	

}

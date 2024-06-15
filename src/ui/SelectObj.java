package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class SelectObj extends JFrame implements ActionListener{

	ObjectSelector o;
	JButton teste = new JButton("Test");
	JList<?> list;
	
	public SelectObj(ObjectSelector o) {

		setVisible(true);
		setSize(800, 500);
		setTitle("Teste");
		setLayout(new FlowLayout());
		
		
		String[] objects = {"Teste1","Teste2","Teste1","Teste2","Teste1","Teste2","Teste1","Teste2","Teste1","Teste2","Teste1","Teste2","Teste1","Teste2","Teste1","Teste2","Teste1","Teste2"};
		list = new JList(objects);
		list.setVisibleRowCount(10);
		JScrollPane jcp = new JScrollPane(list);
		add(jcp);
		
		this.o = o;
		teste.setBounds(430, 300, 130, 40);
		teste.setFont(new Font("Arial", Font.BOLD,20));
		teste.setForeground(new Color(255,255,255));
		teste.setBackground(new Color(0,0,0));
		add(teste);
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

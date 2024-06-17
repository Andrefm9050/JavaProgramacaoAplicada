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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import sistema.Obra;
import users.Listable;

public class SelectObj extends JFrame implements ActionListener{

	ObjectSelector o;
	JButton teste = new JButton("Test");
	JList<Listable> list;
	ListModel lista;
	ArrayList<Listable> data;
	
	public SelectObj(ObjectSelector o, Listable[] objects) {
		
		data = new ArrayList<Listable>();
		for(var obj : objects) {
			data.add(obj);
		}
		System.out.println("Test");
		setVisible(true);
		setSize(800, 500);
		setTitle("Selecionar Objeto");
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel("Selecionar Objeto do tipo: " + objects[0].getClass().getSimpleName());
		
		add(label,BorderLayout.NORTH);
		for(var obj : objects) {
			System.out.println(obj.toString());
		}
		
		list = new JList<Listable>(objects);
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
		
		ButtonGroup group = new ButtonGroup();
		JPanel filtragem = new JPanel();
		filtragem.setLayout(new GridLayout(objects[0].filtragensDisponiveis().length,1));
		String[][] filtragens = objects[0].filtragensDisponiveis();
		for(int x = 0; x<filtragens.length; x++) {
			
			JRadioButtonCustom btn = new JRadioButtonCustom(filtragens[x][0]);
			group.add(btn);
			btn.valor = filtragens[x][1];
			filtragem.add(btn);
			btn.addActionListener(this::radioboxsel);
			
		}
		add(filtragem,BorderLayout.EAST);
		
		
	}
	
	void radioboxsel(ActionEvent e) {
		if(e.getSource() instanceof JRadioButtonCustom) {
			JRadioButtonCustom selected = (JRadioButtonCustom)e.getSource();
			for(var obj : data) {
				obj.setOrdenacao(selected.valor);
			}
			System.out.println(data);
			
			ListModel model = list.getModel();
			List<Listable> data_ = new ArrayList<Listable>();
			for(int i=0;i<model.getSize();i++) {
				data_.add((Listable)model.getElementAt(i));
			}
	
			Collections.sort(data_,Collections.reverseOrder());
			
			list.setListData(data_.toArray(new Listable[0]));
			
			System.out.println(data_);
			
		}
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

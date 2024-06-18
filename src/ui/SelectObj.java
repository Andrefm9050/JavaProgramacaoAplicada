package ui;

import users.Listable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe tipo JFrame reponsável por oferecer uma interface facil para selecionar e devolver um objeto de uma lista
 * @author Andre Rios
 */
public class SelectObj extends JFrame implements ActionListener{

	ObjectSelector o;
	JButton teste = new JButton("Test");
	JList<Listable> list;
	ListModel lista;
	ArrayList<Listable> data;
	JCheckBox sortOrder;
	JRadioButtonCustom lastRadio;
	JTextField pesquisa;

	public SelectObj(ObjectSelector o, Listable[] objects) {

		data = new ArrayList<Listable>();
		for(var obj : objects) {
			data.add(obj);
		}
		setVisible(true);
		setSize(800, 500);
		setLocationRelativeTo(null);
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
		filtragem.setLayout(new GridLayout(objects[0].filtragensDisponiveis().length + 2,1));
		String[][] filtragens = objects[0].filtragensDisponiveis();
		JPanel pesquisapanel = new JPanel();
		pesquisapanel.setLayout(new FlowLayout());
		pesquisapanel.add(new JLabel("Termo pesquisa"));
		pesquisa = new JTextField(16);
		pesquisa.addActionListener(this::termoPesquisaMudado);
		pesquisapanel.add(pesquisa);

		filtragem.add(pesquisapanel);
		for(int x = 0; x<filtragens.length; x++) {

			JRadioButtonCustom btn = new JRadioButtonCustom(filtragens[x][0]);
			if(x == 0) {
				lastRadio = btn;
				btn.setSelected(true);
			}
			group.add(btn);



			btn.valor = filtragens[x][1];
			filtragem.add(btn);
			btn.addActionListener(this::radioboxsel);

		}
		sortOrder = new JCheckBox("Ordem crescente");
		sortOrder.addActionListener(this::sortOrder);
		filtragem.add(sortOrder);

		add(filtragem,BorderLayout.EAST);


	}

	/**
	 * Evento quando o termo de pesquisa é atualizado
	 * @param e
	 */
	void termoPesquisaMudado(ActionEvent e) {
		sort(lastRadio,sortOrder.isSelected());
	}
	
	/**
	 * Evento quando a ordem de sorting muda
	 * @param e
	 */
	void sortOrder(ActionEvent e) {
		sort(lastRadio,((JCheckBox)e.getSource()).isSelected());
	}
	
	/**
	 * Funcao para atualizar a lista com as configuracoes atuais
	 * @param filter
	 * @param val
	 */
	void sort(JRadioButtonCustom filter,boolean val) {
		JRadioButtonCustom selected = (JRadioButtonCustom)filter;
		//System.out.println(data);

		ListModel model = list.getModel();
		List<Listable> data_ = new ArrayList<Listable>();
		for(int i=0;i<data.size();i++) {

			Listable item = (Listable)data.get(i);

			if(pesquisa.getText() != "") {
				if(item.toString().contains(pesquisa.getText())) {
					data_.add(item);
				}
			}
			else {
			data_.add(item);
			}

			item.setOrdenacao(selected.valor);
		}

		if(val)
		Collections.sort(data_);
		else
			Collections.sort(data_,Collections.reverseOrder());

		list.setListData(data_.toArray(new Listable[0]));

		//System.out.println(data_);
	}
	
	/**
	 * Evento para quando um filtro é selecionado
	 * @param e
	 */
	void radioboxsel(ActionEvent e) {
		if(e.getSource() instanceof JRadioButtonCustom) {
			lastRadio = (JRadioButtonCustom)e.getSource();
			sort((JRadioButtonCustom)e.getSource(),sortOrder.isSelected());
		}
	}

	boolean alreadyclosing;
	/**
	 * Evento para quando um objeto é selecionado
	 * @param e
	 */
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

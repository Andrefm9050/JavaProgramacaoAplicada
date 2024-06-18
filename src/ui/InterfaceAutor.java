package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceAutor extends JFrame implements ActionListener,ObjectSelector{

    public InterfaceAutor(){
        setVisible(true);
        setSize(800, 600);
        setTitle("Menu Autor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel jLabel = new JLabel("Menu Autor");
        jLabel.setBounds(326, 10, 190, 30);
        jLabel.setFont(new Font("Arial", Font.BOLD, 25));
        add(jLabel);

        JButton jButton1 = new JButton("Submeter Obra Para Revisão");
        jButton1.setBounds(237, 60, 318, 40);
        jButton1.setFont(new Font("Arial", Font.BOLD,16));
        jButton1.setForeground(new Color(255,255,255));
        jButton1.setBackground(new Color(0,0,0));
        jButton1.setToolTipText("Realizar o processo de submeter uma obra para revisão");
        add(jButton1);

        JButton jButton2 = new JButton("Estado Da Revisão");
        jButton2.setBounds(237, 100, 318, 40);
        jButton2.setFont(new Font("Arial", Font.BOLD,16));
        jButton2.setForeground(new Color(255,255,255));
        jButton2.setBackground(new Color(0,0,0));
        jButton2.setToolTipText("Visualizar estado de uma revisão");
        add(jButton2);

        JButton jButton3 = new JButton("Inserir Obra");
        jButton3.setBounds(237, 140, 318, 40);
        jButton3.setFont(new Font("Arial", Font.BOLD,16));
        jButton3.setForeground(new Color(255,255,255));
        jButton3.setBackground(new Color(0,0,0));
        jButton3.setToolTipText("Realizar o processo de inserir uma nova obra");
        add(jButton3);

        JButton jButton4 = new JButton("Pedido Remover Conta");
        jButton4.setBounds(237, 180, 318, 40);
        jButton4.setFont(new Font("Arial", Font.BOLD,16));
        jButton4.setForeground(new Color(255,255,255));
        jButton4.setBackground(new Color(0,0,0));
        jButton4.setToolTipText("Realizar o processo de pedido de remoção de conta");
        add(jButton4);

        JButton jButton5 = new JButton("Listar Pedidos Revisao Minhas Obras");
        jButton5.setBounds(237, 220, 318, 40);
        jButton5.setFont(new Font("Arial", Font.BOLD,16));
        jButton5.setForeground(new Color(255,255,255));
        jButton5.setBackground(new Color(0,0,0));
        jButton5.setToolTipText("Visualizar todos os pedidos de revisao das obras da conta logada");
        add(jButton5);

        JButton jButton6 = new JButton("Listar Minhas Obras");
        jButton6.setBounds(237, 260, 318, 40);
        jButton6.setFont(new Font("Arial", Font.BOLD,16));
        jButton6.setForeground(new Color(255,255,255));
        jButton6.setBackground(new Color(0,0,0));
        jButton6.setToolTipText("Visualizar todas as obras da conta logada");
        add(jButton6);

        JButton jButton7 = new JButton("Sair");
        jButton7.setBounds(237, 300, 318, 40);
        jButton7.setFont(new Font("Arial", Font.BOLD,16));
        jButton7.setForeground(new Color(255,255,255));
        jButton7.setBackground(new Color(0,0,0));
        jButton7.setToolTipText("Volta para a página principal");
        add(jButton7);

        jButton7.addActionListener(this::sair);



    }

    private void sair(ActionEvent actionEvent) {
        getContentPane().removeAll();
        revalidate();
        repaint();
        this.dispose();
        new InterfaceGrafica();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void OnObjectSelected(SelectObj component, Object object) {

    }
}

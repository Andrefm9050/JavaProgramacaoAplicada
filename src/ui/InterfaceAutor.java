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
        add(jButton1);

        JButton jButton2 = new JButton("Estado Da Revisão");
        jButton2.setBounds(237, 100, 318, 40);
        jButton2.setFont(new Font("Arial", Font.BOLD,16));
        jButton2.setForeground(new Color(255,255,255));
        jButton2.setBackground(new Color(0,0,0));
        add(jButton2);

        JButton jButton3 = new JButton("Inserir Obra");
        jButton3.setBounds(237, 140, 318, 40);
        jButton3.setFont(new Font("Arial", Font.BOLD,16));
        jButton3.setForeground(new Color(255,255,255));
        jButton3.setBackground(new Color(0,0,0));
        add(jButton3);

        JButton jButton4 = new JButton("Pedido Remover Conta");
        jButton4.setBounds(237, 180, 318, 40);
        jButton4.setFont(new Font("Arial", Font.BOLD,16));
        jButton4.setForeground(new Color(255,255,255));
        jButton4.setBackground(new Color(0,0,0));
        add(jButton4);

        JButton jButton5 = new JButton("Listar Pedidos Revisao Minhas Obras");
        jButton5.setBounds(237, 220, 318, 40);
        jButton5.setFont(new Font("Arial", Font.BOLD,16));
        jButton5.setForeground(new Color(255,255,255));
        jButton5.setBackground(new Color(0,0,0));
        add(jButton5);

        JButton jButton6 = new JButton("Listar Minhas Obras");
        jButton6.setBounds(237, 260, 318, 40);
        jButton6.setFont(new Font("Arial", Font.BOLD,16));
        jButton6.setForeground(new Color(255,255,255));
        jButton6.setBackground(new Color(0,0,0));
        add(jButton6);

        JButton jButton7 = new JButton("Sair");
        jButton7.setBounds(237, 300, 318, 40);
        jButton7.setFont(new Font("Arial", Font.BOLD,16));
        jButton7.setForeground(new Color(255,255,255));
        jButton7.setBackground(new Color(0,0,0));
        add(jButton7);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void OnObjectSelected(SelectObj component, Object object) {

    }
}

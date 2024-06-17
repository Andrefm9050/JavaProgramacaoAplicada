package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGestor extends JFrame implements ActionListener,ObjectSelector{

    public InterfaceGestor(){

        setVisible(true);
        setSize(800, 600);
        setTitle("Menu Gestor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel jLabel = new JLabel("Menu Gestor");
        jLabel.setBounds(325, 10, 150, 30);
        jLabel.setFont(new Font("Arial", Font.BOLD, 25));
        add(jLabel);

        JButton jButton1 = new JButton("Aprovar Pedidos de Registo");
        jButton1.setBounds(245, 60, 302, 40);
        jButton1.setFont(new Font("Arial", Font.BOLD,16));
        jButton1.setForeground(new Color(255,255,255));
        jButton1.setBackground(new Color(0,0,0));
        add(jButton1);

        JButton jButton2 = new JButton("Criar conta Gestor");
        jButton2.setBounds(245, 100, 302, 40);
        jButton2.setFont(new Font("Arial", Font.BOLD,16));
        jButton2.setForeground(new Color(255,255,255));
        jButton2.setBackground(new Color(0,0,0));
        add(jButton2);

        JButton jButton3 = new JButton("Ativar/Inativar Conta");
        jButton3.setBounds(245, 140, 302, 40);
        jButton3.setFont(new Font("Arial", Font.BOLD,16));
        jButton3.setForeground(new Color(255,255,255));
        jButton3.setBackground(new Color(0,0,0));
        add(jButton3);

        JButton jButton4 = new JButton("Pedido Remover Conta");
        jButton4.setBounds(245, 180, 302, 40);
        jButton4.setFont(new Font("Arial", Font.BOLD,16));
        jButton4.setForeground(new Color(255,255,255));
        jButton4.setBackground(new Color(0,0,0));
        add(jButton4);

        JButton jButton5 = new JButton("Pedidos  Remoção Conta");
        jButton5.setBounds(245, 220, 302, 40);
        jButton5.setFont(new Font("Arial", Font.BOLD,16));
        jButton5.setForeground(new Color(255,255,255));
        jButton5.setBackground(new Color(0,0,0));
        add(jButton5);

        JButton jButton6 = new JButton("Pedidos Revisao");
        jButton6.setBounds(245, 260, 302, 40);
        jButton6.setFont(new Font("Arial", Font.BOLD,16));
        jButton6.setForeground(new Color(255,255,255));
        jButton6.setBackground(new Color(0,0,0));
        add(jButton6);


        JButton jButton7 = new JButton("Listar Utilizadores");
        jButton7.setBounds(245, 300, 302, 40);
        jButton7.setFont(new Font("Arial", Font.BOLD,16));
        jButton7.setForeground(new Color(255,255,255));
        jButton7.setBackground(new Color(0,0,0));
        add(jButton7);

        JButton jButton8 = new JButton("Listar Pedidos Revisao");
        jButton8.setBounds(245, 340, 302, 40);
        jButton8.setFont(new Font("Arial", Font.BOLD,16));
        jButton8.setForeground(new Color(255,250,255));
        jButton8.setBackground(new Color(0,0,0));
        add(jButton8);


        JButton jButton9 = new JButton("Listar Pedidos Revisao por finalizar");
        jButton9.setBounds(245, 380, 302, 40);
        jButton9.setFont(new Font("Arial", Font.BOLD,16));
        jButton9.setForeground(new Color(255,255,255));
        jButton9.setBackground(new Color(0,0,0));
        add(jButton9);


        JButton jButton10 = new JButton("Listar Pedidos Revisao de Obra");
        jButton10.setBounds(245, 420, 302, 40);
        jButton10.setFont(new Font("Arial", Font.BOLD,16));
        jButton10.setForeground(new Color(255,255,255));
        jButton10.setBackground(new Color(0,0,0));
        add(jButton10);


        JButton jButton11 = new JButton("Sair");
        jButton11.setBounds(245, 460, 302, 40);
        jButton11.setFont(new Font("Arial", Font.BOLD,16));
        jButton11.setForeground(new Color(255,255,255));
        jButton11.setBackground(new Color(0,0,0));
        add(jButton11);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void OnObjectSelected(SelectObj component, Object object) {

    }
}

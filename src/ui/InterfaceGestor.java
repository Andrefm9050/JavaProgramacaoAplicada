package ui;

//import jdk.internal.icu.impl.UBiDiProps;

import sistema.BDDriver;
import users.Gestor;
import users.Utilizador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class InterfaceGestor extends JFrame implements ActionListener,ObjectSelector{

    String loginBuffer;
    String passwordBuffer;
    Gestor gestorOutro;
    Utilizador novoBuffer;
    JTextField text1;

    public InterfaceGestor(Gestor user){

        getContentPane().removeAll();
        revalidate();
        repaint();

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
        jButton1.setToolTipText("Aprovar/Reprovar pedidos de registo de utilizadores");
        add(jButton1);

        JButton jButton2 = new JButton("Criar conta Gestor");
        jButton2.setBounds(245, 100, 302, 40);
        jButton2.setFont(new Font("Arial", Font.BOLD,16));
        jButton2.setForeground(new Color(255,255,255));
        jButton2.setBackground(new Color(0,0,0));
        jButton2.setToolTipText("Criar uma conta do tipo gestor");
        add(jButton2);

        JButton jButton3 = new JButton("Ativar/Inativar Conta");
        jButton3.setBounds(245, 140, 302, 40);
        jButton3.setFont(new Font("Arial", Font.BOLD,16));
        jButton3.setForeground(new Color(255,255,255));
        jButton3.setBackground(new Color(0,0,0));
        jButton3.setToolTipText("Ativar/Inativar uma conta de qualquer tipo");
        add(jButton3);

        JButton jButton4 = new JButton("Pedido Remover Conta");
        jButton4.setBounds(245, 180, 302, 40);
        jButton4.setFont(new Font("Arial", Font.BOLD,16));
        jButton4.setForeground(new Color(255,255,255));
        jButton4.setBackground(new Color(0,0,0));
        jButton4.setToolTipText("Fazer um pedido de remoção de conta (conta logada)");
        add(jButton4);

        JButton jButton5 = new JButton("Pedidos Remoção Conta");
        jButton5.setBounds(245, 220, 302, 40);
        jButton5.setFont(new Font("Arial", Font.BOLD,16));
        jButton5.setForeground(new Color(255,255,255));
        jButton5.setBackground(new Color(0,0,0));
        jButton5.setToolTipText("Aprovar/Reprovar pedidos de registo de utilizadores");
        add(jButton5);

        JButton jButton6 = new JButton("Pedidos Revisao");
        jButton6.setBounds(245, 260, 302, 40);
        jButton6.setFont(new Font("Arial", Font.BOLD,16));
        jButton6.setForeground(new Color(255,255,255));
        jButton6.setBackground(new Color(0,0,0));
        jButton6.setToolTipText("Visualizar pedidos de revisao");
        add(jButton6);


        JButton jButton7 = new JButton("Listar Utilizadores");
        jButton7.setBounds(245, 300, 302, 40);
        jButton7.setFont(new Font("Arial", Font.BOLD,16));
        jButton7.setForeground(new Color(255,255,255));
        jButton7.setBackground(new Color(0,0,0));
        jButton7.setToolTipText("Visualizar todos os utilizadores");
        add(jButton7);

        JButton jButton8 = new JButton("Listar Pedidos Revisao");
        jButton8.setBounds(245, 340, 302, 40);
        jButton8.setFont(new Font("Arial", Font.BOLD,16));
        jButton8.setForeground(new Color(255,250,255));
        jButton8.setBackground(new Color(0,0,0));
        jButton8.setToolTipText("Visualizar todos os pedidos de revisao de obra");
        add(jButton8);


        JButton jButton9 = new JButton("Listar Pedidos Revisao por finalizar");
        jButton9.setBounds(245, 380, 302, 40);
        jButton9.setFont(new Font("Arial", Font.BOLD,16));
        jButton9.setForeground(new Color(255,255,255));
        jButton9.setBackground(new Color(0,0,0));
        jButton9.setToolTipText("Visualizar todos os pedidos de revisao por finalizar por data");
        add(jButton9);


        JButton jButton10 = new JButton("Listar Pedidos Revisao de Obra");
        jButton10.setBounds(245, 420, 302, 40);
        jButton10.setFont(new Font("Arial", Font.BOLD,16));
        jButton10.setForeground(new Color(255,255,255));
        jButton10.setBackground(new Color(0,0,0));
        jButton10.setToolTipText("Visualizar pedidos de revisao de uma obra");
        add(jButton10);


        JButton jButton11 = new JButton("Sair");
        jButton11.setBounds(245, 460, 302, 40);
        jButton11.setFont(new Font("Arial", Font.BOLD,16));
        jButton11.setForeground(new Color(255,255,255));
        jButton11.setBackground(new Color(0,0,0));
        jButton11.setToolTipText("Volta para a pagina principal");
        add(jButton11);

        loginBuffer = user.getLogin();
        passwordBuffer = user.getPassword();
        gestorOutro = user;

        jButton1.addActionListener(this::aprovarPedido);
        jButton11.addActionListener(this::sair);


    }

    public void InterfaceNova(){
        //this.dispose();
        setVisible(false);
        getContentPane().removeAll();
        revalidate();
        repaint();

        setVisible(true);
        setSize(800, 600);
        setTitle("Aprovar Pedido Registo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        //Gestor.aprovarRejeitarPedidosRegisto();



        //text1 = new JTextField(novoBuffer.getLogin());
        //text1.setBounds(320, 200, 150, 17);
        //text1.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
        //text1.setToolTipText("Insira a sua password");
        //add(text1);

        JLabel jLabel = new JLabel("Deseja aprovar o pedido de registo do utilizador: ");
        jLabel.setBounds(185, 100, 450, 30);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(jLabel);

        JLabel jLabel1 = new JLabel(novoBuffer.getLogin());
        jLabel1.setBounds(310, 130, 170, 30);
        jLabel1.setFont(new Font("Arial", Font.PLAIN, 20));
        add(jLabel1);

        JButton jButton1 = new JButton("Nao");
        jButton1.setBounds(290, 200, 80, 40);
        jButton1.setFont(new Font("Arial", Font.BOLD,16));
        jButton1.setForeground(new Color(255,255,255));
        jButton1.setBackground(new Color(0,0,0));
        jButton1.setToolTipText("Reprova pedidos de registo do utilizador");
        add(jButton1);

        JButton jButton2 = new JButton("Sim");
        jButton2.setBounds(420, 200, 80, 40);
        jButton2.setFont(new Font("Arial", Font.BOLD,16));
        jButton2.setForeground(new Color(255,255,255));
        jButton2.setBackground(new Color(0,0,0));
        jButton2.setToolTipText("Aprova pedido de registo do utilizador");
        add(jButton2);

        jButton1.addActionListener(this::voltarMenu);
        jButton2.addActionListener(this::aprovarRegisto);
    }

    private void aprovarRegisto(ActionEvent actionEvent) {

        BDDriver.setUtilizadorEstado(novoBuffer.getIdUser(), 4);
        getContentPane().removeAll();
        revalidate();
        repaint();
        this.dispose();
        new InterfaceGestor(gestorOutro);
    }

    private void voltarMenu(ActionEvent actionEvent) {
        BDDriver.setUtilizadorEstado(novoBuffer.getIdUser(), 1);
        getContentPane().removeAll();
        revalidate();
        repaint();
        this.dispose();
        new InterfaceGestor(gestorOutro);
    }

    private void aprovarPedido(ActionEvent actionEvent) {

        int tamanhoArray;
        tamanhoArray = BDDriver.listarUtilizadores().length;
        Utilizador[] utilizadorBuffer = new Utilizador[tamanhoArray];
        utilizadorBuffer = BDDriver.listarUtilizadores();
        SelectObj object = new SelectObj(this,utilizadorBuffer);

        this.dispose();
        //BDDriver.setUtilizadorEstado(novoBuffer.getIdUser(), aprovaRejeitaN);

        //if(object.o.getClass() == gestor1.getClass()){
            //gestor1 = (Gestor) object.o;
            //System.out.println(gestor1.getIdUser());
            //gestor1.getIdUser();
        //} else if(object.o.getClass() == autor1.getClass()){
          //  autor1 = (Autor) object.o;
          //  autor1.getIdUser();
       // } else if(object.o.getClass() == revisor1.getClass()){
          //  revisor1 = (Revisor) object.o;
          //  System.out.println(revisor1.getIdUser());
          //  revisor1.getIdUser();
     //  }



        //int aprovaRejeitaN;
        //String aprovaRejeita = lerDados("Pretende aprovar ou rejeitar o pedido de registo(s/n): ");
        //if(aprovaRejeita.contentEquals("s")) {
          //  aprovaRejeitaN = 4;
       // }else if(aprovaRejeita.contentEquals("n")) {
          //  aprovaRejeitaN = 1;
       // }else {
         //   System.out.println("Resposta inválida! Insira s ou n como resposta. (s-sim, n-não)");
      //  }

        //BDDriver.setUtilizadorEstado(idEscolha, aprovaRejeitaN);





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
        novoBuffer = ((Utilizador)object);
        this.dispose();
        InterfaceNova();
    }

    private static int lerDadosInt(String aMensagem){
        System.out.println(aMensagem);
        return(new Scanner(System.in)).nextInt();

    }

    public static String lerDados(String aMensagem){
        System.out.print(aMensagem);
        return(new Scanner(System.in)).nextLine();
    }
}

package ui;

//import jdk.internal.icu.impl.UBiDiProps;

import pastaPrincipal.Main;
import sistema.BDDriver;
import users.EstadoConta;
import users.Gestor;
import users.GestorContas;
import users.Utilizador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class InterfaceGestor extends JFrame implements ActionListener,ObjectSelector{


    String loginBuffer;
    String passwordBuffer;
    Gestor gestorOutro;
    Utilizador novoBuffer;
    JTextField text1;
    Gestor userBuffer;
    JTextField text;
    //JTextField text1;
    JTextField text2;
    JTextField text3;
    JTextField text4;
    JTextField text5;
    JTextField text6;
    JTextField text7;
    JTextField text8;
    JTextField text9;
    JTextField text10;
    int bufferGlobal;

    public InterfaceGestor(Gestor user){

        getContentPane().removeAll();
        revalidate();
        repaint();



    	userBuffer = user;
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

        JButton perfil = new JButton("Perfil");
        perfil.setBounds(800-150,0,150,150);
        perfil.addActionListener(this::VerPerfil);
        add(perfil);

        loginBuffer = user.getLogin();
        passwordBuffer = user.getPassword();
        gestorOutro = user;

        jButton1.addActionListener(this::aprovarPedido);
        jButton2.addActionListener(this::criarContaGestor);
        jButton3.addActionListener(this::ativarConta);
        jButton4.addActionListener(this::pedidoRemoverConta);
        jButton5.addActionListener(this::pedidosRemocaoConta);
        jButton6.addActionListener(this::listarPedidosRevisao);
        jButton11.addActionListener(this::sair);


    }

    private void listarPedidosRevisao(ActionEvent actionEvent) {

    }

    private void pedidosRemocaoConta(ActionEvent actionEvent) {
        bufferGlobal = 2;
        //Gestor.pedidosRemocaoConta();

        int tamanhoArray;
        tamanhoArray = BDDriver.listarUtilizadores().length;
        Utilizador[] utilizadorBuffer = new Utilizador[tamanhoArray];
        Utilizador[] utilizadorBuffer1 = new Utilizador[tamanhoArray];
        utilizadorBuffer = BDDriver.listarUtilizadores();
        ArrayList<Utilizador> utilizadorNovo = new ArrayList<Utilizador>();
        int m = 0;
        for(int i=0; i<tamanhoArray; i++) {
            if(utilizadorBuffer[i].getEstado() == EstadoConta.por_remover) {
                utilizadorNovo.add(utilizadorBuffer[i]);
                utilizadorBuffer1[m] = utilizadorBuffer[i];
                m++;
            }

        }
       // if(utilizadorBuffer1[0]!=null){
           // SelectObj object = new SelectObj(this,utilizadorBuffer1);
       // }else{
            SelectObj object = new SelectObj(this,utilizadorBuffer);
       // }
        //(Utilizador[])utilizadorNovo;


        this.dispose();
    }

    private void pedidoRemoverConta(ActionEvent actionEvent) {
        int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo pedir a remoção da sua conta?");
                if(option == 0){
                    GestorContas.pedidoRemoverConta(userBuffer.getLogin());
                    JOptionPane.showMessageDialog(this, "Sucesso! Ainda pode aceder ao sistema até a remoção ser confirmada");
                }
    }

    private void ativarConta(ActionEvent actionEvent) {
        //Gestor.ativarInativarConta(int idEscolha, String ativarInativar);
        bufferGlobal = 1;
        int tamanhoArray;
        tamanhoArray = BDDriver.listarUtilizadores().length;
        Utilizador[] utilizadorBuffer = new Utilizador[tamanhoArray];
        utilizadorBuffer = BDDriver.listarUtilizadores();
        SelectObj object = new SelectObj(this,utilizadorBuffer);

        this.dispose();
    }

    private void criarContaGestor(ActionEvent actionEvent) {
        //private static void criarGestor(String login1, String password1, String nome1, String email1)
        getContentPane().removeAll();
        revalidate();
        repaint();

        setVisible(true);
        setSize(800, 500);
        setTitle("Registo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel jLabel = new JLabel("Registo");
        jLabel.setBounds(350, 0, 200, 50);
        jLabel.setFont(new Font("Arial", Font.BOLD, 25));
        add(jLabel);


        JLabel jLabel2 = new JLabel("Username: ");
        jLabel2.setBounds(307, 30, 300, 100);
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
        add(jLabel2);

        text1 = new JTextField("username");
        text1.setBounds(400, 70, 140, 20);
        text1.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
        text1.setToolTipText("Insira o seu username");
        add(text1);

        JLabel jLabel3 = new JLabel("Password: ");
        jLabel3.setBounds(309, 50, 300, 100);
        jLabel3.setFont(new Font("Arial", Font.PLAIN, 18));
        add(jLabel3);

        text2 = new JTextField("password");
        text2.setBounds(400, 90, 140, 20);
        text2.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
        text2.setToolTipText("Insira a sua password");
        add(text2);

        JLabel jLabel4 = new JLabel("Nome: ");
        jLabel4.setBounds(341, 70, 300, 100);
        jLabel4.setFont(new Font("Arial", Font.PLAIN, 18));
        add(jLabel4);

        text3 = new JTextField("nome");
        text3.setBounds(400, 110, 140, 20);
        text3.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
        text3.setToolTipText("Insira o seu nome");
        add(text3);

        JLabel jLabel5 = new JLabel("Email: ");
        jLabel5.setBounds(344, 90, 300, 100);
        jLabel5.setFont(new Font("Arial", Font.PLAIN, 18));
        add(jLabel5);

        text4 = new JTextField("email");
        text4.setBounds(400, 130, 140, 20);
        text4.setFont(new Font("Arial", Font.ROMAN_BASELINE, 13));
        text4.setToolTipText("Insira o seu email no formato axzc@exmail.com");
        add(text4);


        JButton jButton = new JButton("Back");
        jButton.setBounds(260, 300, 130, 40);
        jButton.setFont(new Font("Arial", Font.BOLD,20));
        jButton.setForeground(new Color(255,255,255));
        jButton.setBackground(new Color(0,0,0));
        jButton.setToolTipText("Voltar para página principal");
        add(jButton);

        JButton jButton1 = new JButton("Ok");
        jButton1.setBounds(400, 300, 130, 40);
        jButton1.setFont(new Font("Arial", Font.BOLD,20));
        jButton1.setForeground(new Color(255,255,255));
        jButton1.setBackground(new Color(0,0,0));
        jButton1.setToolTipText("Confirmar registo");
        add(jButton1);

        JButton imagem = new JButton("Selecionar Imagem");
        imagem.setBounds(400, 350, 130, 40);
        imagem.setFont(new Font("Arial", Font.BOLD,10));
        imagem.setForeground(new Color(255,255,255));
        imagem.setBackground(new Color(0,0,0));
        imagem.setToolTipText("Selecionar Imagem");
        imagem.addActionListener(this::SelectImage);
        add(imagem);

        try {
            pic = new JLabel(new ImageIcon(ImageIO.read(new File("Images/Default.png")).getScaledInstance(100, 100, Image.SCALE_FAST)));
            pic.setBounds(300, 350, 100, 100);
            add(pic);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //text.getText();
        //text1.getText();
        //text2.getText();
        //text3.getText();
        //text4.getText();

        jButton.addActionListener(this::voltarMenu);
        jButton1.addActionListener(this::realizarRegisto1);

        ///this.dispose();
        //Main.registo(text.getText(), false, text1.getText(), text2.getText(), text3.getText(), text4.getText());
        //Main.registo("Revisor", false, text1.getText(), text2.getText(), text3.getText(), text4.getText(), text5.getText(), text6.getText(),text7.getText(), text8.getText(), text9.getText(), text10.getText());

    }

    private void realizarRegisto1(ActionEvent actionEvent) {
        Main.registo("Gestor", false, text1.getText(),text2.getText(), text3.getText(), text4.getText(), null, null,null, null, null,null, imageSelected);
        getContentPane().removeAll();
        revalidate();
        repaint();
        this.dispose();
        new InterfaceGestor(userBuffer);
    }

    JLabel pic;
    byte[] imageSelected;
    void SelectImage(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);

        if(response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try {
                BufferedImage img = ImageIO.read(file);
                if(img != null) {
                    //Imagem!
                    //System.out.println("Image!");
                    imageSelected = Files.readAllBytes(file.toPath());
                    pic.setIcon(new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_FAST)));
                    super.update(getGraphics());

                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    void VerPerfil(ActionEvent e) {
    	new PaginaPerfil(userBuffer);
    	dispose();

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
        bufferGlobal = 0;
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
        if(bufferGlobal == 0){
            InterfaceNova();
        }else if(bufferGlobal == 1){
            InterfaceConclusao();
        }else if(bufferGlobal == 2){
            InterfacePedidosRemocao();
        }

    }

    private void InterfacePedidosRemocao() {
        //this.dispose();
        setVisible(false);
        getContentPane().removeAll();
        revalidate();
        repaint();

        setVisible(true);
        setSize(800, 600);
        setTitle("Aprovar Pedido Remocao Conta");
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

        JLabel jLabel = new JLabel("Deseja mesmo pedir a remoção da sua conta?: ");
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
        jButton1.setToolTipText("Reprova remocao de conta");
        add(jButton1);

        JButton jButton2 = new JButton("Sim");
        jButton2.setBounds(420, 200, 80, 40);
        jButton2.setFont(new Font("Arial", Font.BOLD,16));
        jButton2.setForeground(new Color(255,255,255));
        jButton2.setBackground(new Color(0,0,0));
        jButton2.setToolTipText("Aprova remocao de conta");
        add(jButton2);

        jButton1.addActionListener(this::voltarMenu2);
        jButton2.addActionListener(this::remocaoDaConta);
    }

    private void voltarMenu2(ActionEvent actionEvent) {
        //GestorContas.pedidoRemoverConta(novoBuffer.getLogin());
        getContentPane().removeAll();
        revalidate();
        repaint();
        this.dispose();
        new InterfaceGestor(gestorOutro);
    }

    private void remocaoDaConta(ActionEvent actionEvent) {
        BDDriver.setUtilizadorEstado(novoBuffer.getIdUser(), 3);
        getContentPane().removeAll();
        revalidate();
        repaint();
        this.dispose();
        new InterfaceGestor(gestorOutro);

    }

    private void InterfaceConclusao() {
        setVisible(false);
        getContentPane().removeAll();
        revalidate();
        repaint();

        setVisible(true);
        setSize(800, 600);
        setTitle("Ativar Conta");
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

        JLabel jLabel = new JLabel("Deseja ativar/inativar a conta: ");
        jLabel.setBounds(200, 100, 450, 30);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(jLabel);

        JLabel jLabel1 = new JLabel(novoBuffer.getLogin());
        jLabel1.setBounds(310, 130, 170, 30);
        jLabel1.setFont(new Font("Arial", Font.PLAIN, 20));
        add(jLabel1);

        JButton jButton1 = new JButton("Inativar");
        jButton1.setBounds(290, 200, 100, 40);
        jButton1.setFont(new Font("Arial", Font.BOLD,16));
        jButton1.setForeground(new Color(255,255,255));
        jButton1.setBackground(new Color(0,0,0));
        jButton1.setToolTipText("Reprova pedidos de registo do utilizador");
        add(jButton1);

        JButton jButton2 = new JButton("Ativar");
        jButton2.setBounds(420, 200, 80, 40);
        jButton2.setFont(new Font("Arial", Font.BOLD,16));
        jButton2.setForeground(new Color(255,255,255));
        jButton2.setBackground(new Color(0,0,0));
        jButton2.setToolTipText("Aprova pedido de registo do utilizador");
        add(jButton2);

        jButton1.addActionListener(this::voltarMenu1);
        jButton2.addActionListener(this::aprovaAtivacao);

        //Gestor.ativarInativarConta(novoBuffer.getIdUser(), String ativarInativar);
    }

    private void voltarMenu1(ActionEvent actionEvent) {
        Gestor.ativarInativarConta(novoBuffer.getIdUser(), "i");
        revalidate();
        repaint();
        this.dispose();
        new InterfaceGestor(gestorOutro);
    }

    private void aprovaAtivacao(ActionEvent actionEvent) {
        BDDriver.setUtilizadorEstado(novoBuffer.getIdUser(), 4);
        //Gestor.ativarInativarConta(novoBuffer.getIdUser(), "a");
        getContentPane().removeAll();
        revalidate();
        repaint();
        this.dispose();
        new InterfaceGestor(gestorOutro);

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

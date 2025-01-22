package br.edu.ifms.estudantes.ui;

import javax.swing.*;

public class BookFormView extends JFrame{
    private JPanel Screen1;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel CampoTema;
    private JPanel CampoTitulo;
    private JPanel CampoAutor;
    private JTextField textField3;
    private JPanel CampoISBN;
    private JTextField textField4;

    public BookFormView() {
        this.setTitle("Cadastro de Livros");
        this.setContentPane(Screen1);
        this.setSize(600, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

package br.edu.ifms.estudantes.ui;

import javax.swing.*;

public class BookFormView extends JFrame{
    private JPanel Screen1;
    private JTextField textField1;

    public BookFormView() {
        this.setTitle("Cadastro de Livros");
        this.setContentPane(Screen1);
        this.setSize(600, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

package br.edu.ifms.estudantes.ui;

import javax.swing.*;

public class SearchView extends JFrame{
    private JPanel SearchScreen;
    private JTextField SearchField;
    private JButton SearchButton;

    public SearchView() {
        setContentPane(SearchScreen);
        this.setSize(600, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

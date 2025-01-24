package br.edu.ifms.estudantes.ui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SearchView extends JFrame{
    private JPanel SearchScreen;
    private JButton button1;
    private JTextField textField1;

    public SearchView() {
        setContentPane(SearchScreen);
        this.setSize(600, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

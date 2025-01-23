package br.edu.ifms.estudantes.ui;

import javax.swing.*;

public class MenuView extends JFrame{
    private JPanel MenuScreen;

    public MenuView() {
        this.setTitle("Menu de opções");
        this.setContentPane(MenuScreen);
        this.setSize(600, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

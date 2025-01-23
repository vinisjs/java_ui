package br.edu.ifms.estudantes.ui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class MenuView extends JFrame{
    private JPanel MenuScreen;
    private JPanel MenuBorder;
    private JButton livrosButton;
    private JButton usuariosButton;
    private JButton emprestimosButton;
    private JButton sairButton;
    private JPanel Content;
    private JLabel titleLabel;

    public MenuView() {
        setTitle("Menu de opções");
        setContentPane(MenuScreen);
        this.setSize(600, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        MenuBorder.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));

        this.setVisible(true);
    }
}

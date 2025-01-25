package br.edu.ifms.estudantes.ui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame{
    private JPanel MenuScreen;
    private JPanel MenuBorder;
    private JButton livrosButton;
    private JButton usuariosButton;
    private JButton emprestimosButton;
    private JButton sairButton;
    private JPanel Content;
    private JLabel titleLabel;

    public MenuView(JPanel splashScreen) {
        setTitle("Menu de opções");
        setContentPane(MenuScreen);
        this.setSize(600, 450);
        this.setLocationRelativeTo(splashScreen);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        MenuBorder.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));

        this.setVisible(true);
        livrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBookForm();
            }
        });
    }
    private void openBookForm() {
        new BookFormView(this);
    }
}

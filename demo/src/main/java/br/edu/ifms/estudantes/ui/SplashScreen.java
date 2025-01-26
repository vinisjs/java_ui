package br.edu.ifms.estudantes.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JFrame{
    private JPanel SplashScreen;
    private JButton continuarButton;

    public SplashScreen() {
        setTitle("início");
        setContentPane(SplashScreen);
        this.setSize(600, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        continuarButton.setPreferredSize(new Dimension(200, 30));
        continuarButton.setFocusPainted(false);
        this.setVisible(true);
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuView(SplashScreen);
                dispose();
            }
        });
    }
}

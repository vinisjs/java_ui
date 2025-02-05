package br.edu.ifms.estudantes.ui.menu;

import br.edu.ifms.estudantes.util.Styles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JFrame{
    private JPanel SplashScreen;
    private JButton continuarButton;

    public Styles styles = new Styles();

    public SplashScreen() {
        setTitle("início");
        setContentPane(SplashScreen);
        this.setSize(800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        styles.styleButtonSplash(continuarButton);
        this.setVisible(true);
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu(SplashScreen);
                dispose();
            }
        });
    }
}

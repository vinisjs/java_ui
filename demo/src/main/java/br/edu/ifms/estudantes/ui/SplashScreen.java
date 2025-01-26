package br.edu.ifms.estudantes.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
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

        styleButton(continuarButton);
        this.setVisible(true);
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuView(SplashScreen);
                dispose();
            }
        });
    }

    private  void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(200, 30));
        button.setBackground(new Color(2, 133, 199));
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        button.setUI(new BasicButtonUI() {
            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                AbstractButton button = (AbstractButton) c;
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setOpaque(false);
            }

            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                JButton b = (JButton) c;

                g2.setColor(b.getBackground());
                g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 20, 20);

                super.paint(g, c);
            }
        });
    }
}

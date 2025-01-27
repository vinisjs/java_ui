package br.edu.ifms.estudantes.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class Styles {
    public Styles(){

    }
    public void styleTextField(JTextField textField) {
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    public void styleButton(JButton button) {
        button.setBackground(new Color(217, 217, 217));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setUI(new BasicButtonUI() {

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

    public void alignFields(JPanel panel, String labelText, JComponent component) {
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(100, 30));
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, BorderLayout.WEST);
        panel.add(component, BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    public void alignRadioButtonField(JPanel panel, String labelText, JComponent... components) {
        panel.setLayout(new GridBagLayout());
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(100, 30));
        label.setFont(new Font("Arial", Font.BOLD, 14));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        for (int i = 0; i < components.length; i++) {
            gbc.gridx = i + 1;
            panel.add(components[i], gbc);
        }

        panel.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    public void styleRadioButton(JRadioButton radioButton) {
        radioButton.setBackground(new Color(240, 240, 240));
        radioButton.setFont(new Font("Arial", Font.PLAIN, 14));
        radioButton.setForeground(Color.BLACK);
    }
}

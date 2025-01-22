package br.edu.ifms.estudantes.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form {
    private JTextField textField1;
    private JTextField textField2;
    private JButton enviarButton;

    public Form() {
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,textField1);
                JOptionPane.showMessageDialog(null,textField2);
            }
        });
    }
}

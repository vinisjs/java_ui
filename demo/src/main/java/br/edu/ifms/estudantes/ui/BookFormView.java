package br.edu.ifms.estudantes.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookFormView extends JDialog{
    private JPanel Screen1;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel CampoTema;
    private JPanel CampoTitulo;
    private JPanel CampoAutor;
    private JTextField textField3;
    private JPanel CampoISBN;
    private JTextField textField4;
    private JPanel CampoData;
    private JTextField textField5;
    private JPanel CampoExemplares;
    private JTextField textField6;
    private JButton cadastrarButton;
    private JButton cancelarButton;

    public BookFormView(JFrame parent) {
        super(parent, "Cadastro de Livros", true);
        this.setContentPane(Screen1);
        this.setSize(580, 400);
        this.setLocationRelativeTo(parent);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.setVisible(true);
    }
}

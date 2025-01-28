package br.edu.ifms.estudantes.ui;

import javax.swing.*;

public class ResultsUsers extends JFrame{
    private JPanel ResultScreenUser;
    private JPanel JPanelUserScreen;
    private JPanel JPanelLabelUser;
    private JTextField textFieldName;
    private JTextField textFieldSex;
    private JPanel JPanelName;
    private JPanel JPanelSex;
    private JPanel JPanelPhone;
    private JPanel JPanelEmail;
    private JTextField textFieldPhone;
    private JTextField textFieldEmail;
    private JButton fecharButton;
    private JButton editButton;


    public ResultsUsers(JPanel MenuView) {
        setTitle("Resultados de Usuários");
        setContentPane(ResultScreenUser);
        this.setSize(550, 400);
        this.setLocationRelativeTo(MenuView);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

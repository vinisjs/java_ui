package br.edu.ifms.estudantes.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public Styles styles = new Styles();

    public ResultsUsers(JPanel MenuView) {
        setTitle("Resultados de Usuários");
        setContentPane(ResultScreenUser);
        this.setSize(600, 450);
        this.setLocationRelativeTo(MenuView);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        styles.styleTextField(textFieldName);
        styles.styleTextField(textFieldSex);
        styles.styleTextField(textFieldPhone);
        styles.styleTextField(textFieldEmail);

        textFieldName.setText("Pedro");
        textFieldSex.setText("Masculino");
        textFieldPhone.setText("+55 67 984677777");
        textFieldEmail.setText("pd10@gmail.com");

        styles.alignFields(JPanelName, "Nome:", textFieldName);
        styles.alignFields(JPanelSex, "Sexo:", textFieldSex);
        styles.alignFields(JPanelPhone, "Telefone:", textFieldPhone);
        styles.alignFields(JPanelEmail, "E-mail:", textFieldEmail);

        styles.styleButton(fecharButton);
        styles.styleButton(editButton);

        textFieldName.setEditable(false);
        textFieldSex.setEditable(false);
        textFieldPhone.setEditable(false);
        textFieldEmail.setEditable(false);
        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editButton.getText().equals("Editar")) {
                    textFieldName.setEditable(true);
                    textFieldSex.setEditable(true);
                    textFieldPhone.setEditable(true);
                    textFieldEmail.setEditable(true);

                    editButton.setText("Aplicar");
                } else {

                    String name = textFieldName.getText();
                    String sex = textFieldSex.getText();
                    String phone = textFieldPhone.getText();
                    String email = textFieldEmail.getText();

                    System.out.println("Nome: " + name);
                    System.out.println("Sexo: " + sex);
                    System.out.println("Telefone: " + phone);
                    System.out.println("E-mail: " + email);

                    textFieldName.setEditable(false);
                    textFieldSex.setEditable(false);
                    textFieldPhone.setEditable(false);
                    textFieldEmail.setEditable(false);

                    editButton.setText("Editar");
                }
            }
        });
    }
}

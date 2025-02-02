package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.UserController;
import br.edu.ifms.estudantes.model.UserModel;

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

    private UserModel user = new UserModel();

    public ResultsUsers(JPanel MenuView, UserModel finalResultado) {
        setTitle("Resultados de Usuários");
        setContentPane(ResultScreenUser);
        this.setSize(600, 450);
        this.setLocationRelativeTo(MenuView);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        styles.styleTextField(textFieldName);
        styles.styleTextField(textFieldSex);
        styles.styleTextField(textFieldPhone);
        styles.styleTextField(textFieldEmail);

        textFieldName.setText(finalResultado.getNome());
        textFieldSex.setText(finalResultado.getSexo());
        textFieldPhone.setText(finalResultado.getNumberPhone());
        textFieldEmail.setText(finalResultado.getEmail());

        System.out.println(finalResultado.getSexo());
        System.out.println(finalResultado.getEmail());

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
        this.setVisible(true);

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

                    textFieldName.setEditable(false);
                    textFieldSex.setEditable(false);
                    textFieldPhone.setEditable(false);
                    textFieldEmail.setEditable(false);

                    user.setNumberId(finalResultado.getNumberId());
                    user.setNome(textFieldName.getText());
                    user.setNumberPhone(textFieldPhone.getText());
                    user.setEmail(textFieldEmail.getText());
                    user.setSexo(textFieldPhone.getText());

                    new UserController().UpdateUser(user);

                    editButton.setText("Editar");
                }
            }
        });
    }
}

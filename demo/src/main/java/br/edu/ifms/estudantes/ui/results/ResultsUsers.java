package br.edu.ifms.estudantes.ui.results;

import br.edu.ifms.estudantes.controller.UserController;
import br.edu.ifms.estudantes.model.UserModel;
import br.edu.ifms.estudantes.util.Styles;
import br.edu.ifms.estudantes.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultsUsers extends JFrame {
    private JPanel ResultScreenUser;
    private JPanel JPanelUserScreen;
    private JPanel JPanelLabelUser;
    private JTextField textFieldName;
    private JPanel JPanelName;
    private JPanel JPanelSex;
    private JPanel JPanelPhone;
    private JPanel JPanelEmail;
    private JTextField textFieldEmail;
    private JLabel emailErrorLabel;
    private JButton fecharButton;
    private JButton editButton;
    private JButton excluirButton;
    private JFormattedTextField formattedTextPhone;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;
    private JRadioButton naoBinarioRadioButton;

    public Styles styles = new Styles();
    private Utils utils = new Utils();
    private UserModel user = new UserModel();

    public ResultsUsers(JPanel MenuView, UserModel finalResultado) {
        setTitle("Resultados de Usuários");
        setContentPane(ResultScreenUser);
        this.setSize(600, 450);
        this.setLocationRelativeTo(MenuView);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        utils.configurePhoneMask(formattedTextPhone);

        styles.styleTextField(textFieldName);
        styles.styleTextField(formattedTextPhone);
        styles.styleTextField(textFieldEmail);

        styles.styleRadioButton(masculinoRadioButton);
        styles.styleRadioButton(femininoRadioButton);
        styles.styleRadioButton(naoBinarioRadioButton);

        textFieldName.setText(finalResultado.getNome());
        formattedTextPhone.setText(finalResultado.getNumberPhone());
        textFieldEmail.setText(finalResultado.getEmail());

        switch (finalResultado.getSexo().toLowerCase()) {
            case "masculino":
                masculinoRadioButton.setSelected(true);
                break;
            case "feminino":
                femininoRadioButton.setSelected(true);
                break;
            default:
                naoBinarioRadioButton.setSelected(true);
                break;
        }

        styles.alignFields(JPanelName, "Nome:", textFieldName);
        styles.alignRadioButtonField(JPanelSex, "Sexo:", masculinoRadioButton, femininoRadioButton, naoBinarioRadioButton);
        styles.alignFields(JPanelPhone, "Telefone:", formattedTextPhone);
        styles.alignFields(JPanelEmail, "E-mail:", textFieldEmail);

        emailErrorLabel = new JLabel("");
        emailErrorLabel.setForeground(Color.RED);
        JPanelEmail.add(emailErrorLabel, BorderLayout.SOUTH);

        JLabel phoneErrorLabel = new JLabel("");
        phoneErrorLabel.setForeground(Color.RED);
        JPanelPhone.add(phoneErrorLabel, BorderLayout.SOUTH);

        styles.styleButton(fecharButton);
        styles.styleButton(editButton);
        styles.styleButton(excluirButton);

        textFieldName.setEditable(false);
        formattedTextPhone.setEditable(false);
        textFieldEmail.setEditable(false);
        setRadioButtonsEnabled(false);

        this.setVisible(true);

        fecharButton.addActionListener(e -> dispose());

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editButton.getText().equals("Editar")) {
                    textFieldName.setEditable(true);
                    formattedTextPhone.setEditable(true);
                    textFieldEmail.setEditable(true);
                    setRadioButtonsEnabled(true);

                    editButton.setText("Aplicar");

                    utils.configureEmailValidation(textFieldEmail, emailErrorLabel);

                } else {

                    String email = textFieldEmail.getText();
                    String phone = formattedTextPhone.getText().replaceAll("[^0-9]", "");
                    boolean isValid = true;

                    if (!email.contains("@")) {
                        emailErrorLabel.setText("E-mail inválido");
                        isValid = false;
                    } else {
                        emailErrorLabel.setText("");
                    }

                    if (phone.length() < 11) {
                        phoneErrorLabel.setText("Número de telefone inválido, complete todos os dígitos.");
                        isValid = false;
                    } else {
                        phoneErrorLabel.setText("");
                    }

                    if (!isValid) {
                        return;
                    }

                    String sexoSelecionado = "";
                    if (masculinoRadioButton.isSelected()) {
                        sexoSelecionado = "Masculino";
                    } else if (femininoRadioButton.isSelected()) {
                        sexoSelecionado = "Feminino";
                    } else {
                        sexoSelecionado = "Não Binário";
                    }

                    user.setNumberId(finalResultado.getNumberId());
                    user.setNome(textFieldName.getText());
                    user.setNumberPhone(formattedTextPhone.getText());
                    user.setEmail(textFieldEmail.getText());
                    user.setSexo(sexoSelecionado);

                    new UserController().UpdateUser(user);

                    textFieldName.setEditable(false);
                    formattedTextPhone.setEditable(false);
                    textFieldEmail.setEditable(false);
                    setRadioButtonsEnabled(false);
                    editButton.setText("Editar");
                }
            }
        });

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(
                        ResultsUsers.this,
                        "Tem certeza de que deseja excluir este usuário?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirmation == JOptionPane.YES_OPTION) {
                    user.setNumberId(finalResultado.getNumberId());
                    new UserController().DeleteUserById(finalResultado.getNumberId());
                    dispose();
                }
            }
        });
    }

    private void setRadioButtonsEnabled(boolean enabled) {
        masculinoRadioButton.setEnabled(enabled);
        femininoRadioButton.setEnabled(enabled);
        naoBinarioRadioButton.setEnabled(enabled);
    }
}
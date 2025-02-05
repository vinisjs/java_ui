package br.edu.ifms.estudantes.ui.register;

import br.edu.ifms.estudantes.controller.UserController;
import br.edu.ifms.estudantes.model.UserModel;
import br.edu.ifms.estudantes.util.Styles;
import br.edu.ifms.estudantes.util.Utils;

import javax.swing.*;
import java.awt.*;

public class RegisterUser extends JDialog {
    private JPanel Screen2;
    private JTextField textName;
    private JPanel CampoNome;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;
    private JRadioButton naoBinarioRadioButton;
    private JPanel CampoSexo;
    private JPanel CampoTelefone;
    private JPanel CampoEmail;
    private JButton cancelarButtonUser;
    private JButton cadastrarButtonUser;
    private JFormattedTextField formattedTextTelefone;
    private JTextField textEmail;
    private JPanel Jpanel;
    private JLabel emailErrorLabel = new JLabel("");


    private Styles styles = new Styles();
    private Utils utils = new Utils();
    private UserModel user = new UserModel();

    public RegisterUser(JFrame parentUser) {
        super(parentUser, "Cadastro de Usuários", true);
        setContentPane(Screen2);
        this.setSize(600, 450);
        this.setLocationRelativeTo(parentUser);

        utils.configurePhoneMask(formattedTextTelefone);

        utils.configureEmailValidation(textEmail, emailErrorLabel);

        styleComponents();

        cadastrarButtonUser.addActionListener(e -> registerUser());

        cancelarButtonUser.addActionListener(e -> dispose());

        this.setVisible(true);
    }

    private void styleComponents() {
        styles.styleTextField(textName);
        styles.styleTextField(formattedTextTelefone);
        styles.styleTextField(textEmail);

        styles.styleRadioButton(masculinoRadioButton);
        styles.styleRadioButton(femininoRadioButton);
        styles.styleRadioButton(naoBinarioRadioButton);

        styles.alignFields(CampoNome, "Nome:", textName);
        styles.alignFields(CampoTelefone, "Telefone:", formattedTextTelefone);
        styles.alignFields(CampoEmail, "E-mail:", textEmail);

        styles.alignRadioButtonField(CampoSexo, "Sexo:", masculinoRadioButton, femininoRadioButton, naoBinarioRadioButton);

        emailErrorLabel.setForeground(Color.RED);
        CampoEmail.add(emailErrorLabel, BorderLayout.SOUTH);

        styles.styleButton(cadastrarButtonUser);
        styles.styleButton(cancelarButtonUser);
    }


    private void registerUser() {

        String email = textEmail.getText();

        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(
                    this,
                    "E-mail inválido. Insira um e-mail válido com '@'.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        user.setNome(textName.getText());
        user.setEmail(textEmail.getText());
        user.setNumberPhone(formattedTextTelefone.getText());

        if (masculinoRadioButton.isSelected()) {
            user.setSexo(masculinoRadioButton.getText());
        } else if (femininoRadioButton.isSelected()) {
            user.setSexo(femininoRadioButton.getText());
        } else if (naoBinarioRadioButton.isSelected()) {
            user.setSexo(naoBinarioRadioButton.getText());
        }

        new UserController().saveOneUser(user);

        JOptionPane.showMessageDialog(
                this,
                "Usuário salvo com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
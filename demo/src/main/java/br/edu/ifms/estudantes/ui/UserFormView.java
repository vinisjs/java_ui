package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.UserController;
import br.edu.ifms.estudantes.model.UserModel;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserFormView extends JDialog {
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
    private JLabel emailErrorLabel;
    private JTextField textEmail;
    private JPanel Jpanel;

    private Styles styles = new Styles();
    private UserModel user = new UserModel();

    public UserFormView(JFrame parentUser) {
        super(parentUser, "Cadastro de Usuários", true);
        setContentPane(Screen2);
        this.setSize(580, 400);
        this.setLocationRelativeTo(parentUser);

        configurePhoneMask();

        configureEmailValidation();

        styleComponents();

        cadastrarButtonUser.addActionListener(e -> registerUser());

        cancelarButtonUser.addActionListener(e -> dispose());

        this.setVisible(true);
    }

    private void configurePhoneMask() {
        try {
            MaskFormatter phoneMask = new MaskFormatter("+## (##) ##### - ####");
            formattedTextTelefone.setFormatterFactory(new DefaultFormatterFactory(phoneMask));
        } catch (ParseException ex) {
            System.out.println("Erro ao aplicar a máscara do telefone.");
        }
    }

    private void configureEmailValidation() {
        textEmail.getDocument().addDocumentListener(new SimpleDocumentListener() {
            @Override
            public void update() {
                String email = textEmail.getText();
                if (!isValidEmail(email)) {
                    emailErrorLabel.setText("E-mail inválido");
                } else {
                    emailErrorLabel.setText("");
                }
            }
        });
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

        emailErrorLabel = new JLabel("");
        emailErrorLabel.setForeground(Color.RED);
        CampoEmail.add(emailErrorLabel, BorderLayout.SOUTH);

        styles.styleButton(cadastrarButtonUser);
        styles.styleButton(cancelarButtonUser);
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void registerUser() {
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

        new UserController().controllerSave(user);
    }

    public abstract class SimpleDocumentListener implements javax.swing.event.DocumentListener {
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            update();
        }
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            update();
        }
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            update();
        }
        public abstract void update();
    }
}
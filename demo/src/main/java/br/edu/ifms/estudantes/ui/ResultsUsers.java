package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.UserController;
import br.edu.ifms.estudantes.model.UserModel;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private JTextField textFieldEmail;
    private JLabel emailErrorLabel;
    private JButton fecharButton;
    private JButton editButton;
    private JButton excluirButton;
    private JFormattedTextField formattedTextPhone;

    public Styles styles = new Styles();

    private UserModel user = new UserModel();

    public ResultsUsers(JPanel MenuView, UserModel finalResultado) {
        setTitle("Resultados de Usuários");
        setContentPane(ResultScreenUser);
        this.setSize(600, 450);
        this.setLocationRelativeTo(MenuView);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        configurePhoneMask();

        styles.styleTextField(textFieldName);
        styles.styleTextField(textFieldSex);
        styles.styleTextField(formattedTextPhone);
        styles.styleTextField(textFieldEmail);

        textFieldName.setText(finalResultado.getNome());
        textFieldSex.setText(finalResultado.getSexo());
        formattedTextPhone.setText(finalResultado.getNumberPhone());
        textFieldEmail.setText(finalResultado.getEmail());

        System.out.println(finalResultado.getSexo());
        System.out.println(finalResultado.getEmail());

        styles.alignFields(JPanelName, "Nome:", textFieldName);
        styles.alignFields(JPanelSex, "Sexo:", textFieldSex);
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
        textFieldSex.setEditable(false);
        formattedTextPhone.setEditable(false);
        textFieldEmail.setEditable(false);
        this.setVisible(true);

        fecharButton.addActionListener(e -> dispose());

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editButton.getText().equals("Editar")) {
                    textFieldName.setEditable(true);
                    textFieldSex.setEditable(true);
                    formattedTextPhone.setEditable(true);
                    textFieldEmail.setEditable(true);

                    editButton.setText("Aplicar");

                    configureEmailValidation();

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

                    user.setNumberId(finalResultado.getNumberId());
                    user.setNome(textFieldName.getText());
                    user.setNumberPhone(formattedTextPhone.getText());
                    user.setEmail(textFieldEmail.getText());
                    user.setSexo(textFieldSex.getText());

                    new UserController().UpdateUser(user);

                    textFieldName.setEditable(false);
                    textFieldSex.setEditable(false);
                    formattedTextPhone.setEditable(false);
                    textFieldEmail.setEditable(false);
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

    private void configureEmailValidation() {
        textFieldEmail.getDocument().addDocumentListener(new SimpleDocumentListener() {
            @Override
            public void update() {
                String email = textFieldEmail.getText();
                if (!isValidEmail(email)) {
                    emailErrorLabel.setText("E-mail inválido");
                } else {
                    emailErrorLabel.setText("");
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void configurePhoneMask() {
        try {
            MaskFormatter phoneMask = new MaskFormatter("+## (##) ##### - ####");
            phoneMask.setPlaceholderCharacter('_');
            formattedTextPhone.setFormatterFactory(new DefaultFormatterFactory(phoneMask));
        } catch (ParseException ex) {
            System.out.println("Erro ao aplicar a máscara do telefone.");
        }
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

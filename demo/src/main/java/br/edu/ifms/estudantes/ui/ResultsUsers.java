package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.controller.UserController;
import br.edu.ifms.estudantes.model.UserModel;

import javax.swing.*;
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
    private JTextField textFieldPhone;
    private JTextField textFieldEmail;
    private JLabel emailErrorLabel;
    private JButton fecharButton;
    private JButton editButton;
    private JButton excluirButton;

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

        emailErrorLabel = new JLabel("");
        emailErrorLabel.setForeground(Color.RED);
        JPanelEmail.add(emailErrorLabel, BorderLayout.SOUTH);

        styles.styleButton(fecharButton);
        styles.styleButton(editButton);
        styles.styleButton(excluirButton);

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

                    configureEmailValidation();

                } else {

                    String email = textFieldEmail.getText();
                    if (!email.contains("@")) {
                        JOptionPane.showMessageDialog(null, "E-mail inválido. Insira um e-mail válido com '@'.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    textFieldName.setEditable(false);
                    textFieldSex.setEditable(false);
                    textFieldPhone.setEditable(false);
                    textFieldEmail.setEditable(false);

                    user.setNumberId(finalResultado.getNumberId());
                    user.setNome(textFieldName.getText());
                    user.setNumberPhone(textFieldPhone.getText());
                    user.setEmail(textFieldEmail.getText());
                    user.setSexo(textFieldSex.getText());

                    new UserController().UpdateUser(user);

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

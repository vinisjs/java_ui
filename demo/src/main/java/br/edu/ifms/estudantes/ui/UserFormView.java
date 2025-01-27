package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.UserController;
import br.edu.ifms.estudantes.model.UserModel;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class UserFormView extends JDialog{
    private JPanel Screen2;
    private JTextField textName;
    private JPanel CampoNome;
    private JPanel Jpanel;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;
    private JRadioButton naoBinarioRadioButton;
    private JPanel CampoSexo;
    private JPanel CampoTelefone;
    private JPanel CampoEmail;
    private JButton cancelarButtonUser;
    private JButton cadastrarButtonUser;
    private JFormattedTextField formattedTextEmail;
    private JFormattedTextField formattedTextTelefone;

    public Styles styles = new Styles();

    UserModel user = new UserModel();

    public UserFormView(JFrame parentUser) {
        super(parentUser, "Cadastro de Usuários", true);
        setContentPane(Screen2);
        this.setSize(580, 400);
        this.setLocationRelativeTo(parentUser);

        cadastrarButtonUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setNome(textName.getText());
                user.setEmail(formattedTextEmail.getText());
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
        });
        cancelarButtonUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        MaskFormatter mascaratelefone = null;

        try {
            mascaratelefone = new MaskFormatter("+## (##) ##### - ####");

            formattedTextTelefone.setFormatterFactory(new DefaultFormatterFactory(mascaratelefone));

        } catch (ParseException ex) {
            System.out.println("Deu B.O");
        }

        styles.styleTextField(textName);
        styles.styleTextField(formattedTextTelefone);
        styles.styleTextField(formattedTextEmail);

        styles.alignFields(CampoNome, "Nome:", textName);
        styles.alignFields(CampoTelefone, "Telefone:", formattedTextTelefone);
        styles.alignFields(CampoEmail, "E-mail:", formattedTextEmail);

        styles.styleButton(cadastrarButtonUser);
        styles.styleButton(cancelarButtonUser);


        this.setVisible(true);

    }
}

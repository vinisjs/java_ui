package br.edu.ifms.estudantes.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFormView extends JDialog{
    private JPanel Screen2;
    private JTextField textName;
    private JPanel CampoNome;
    private JPanel Jpanel;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;
    private JRadioButton nãoBinárioRadioButton;
    private JPanel CampoSexo;
    private JTextField textPhone;
    private JPanel CampoTelefone;
    private JPanel CampoEmail;
    private JTextField textEmail;
    private JButton cancelarButtonUser;
    private JButton cadastrarButtonUser;

    public UserFormView(JFrame parentUser) {
        super(parentUser, "Cadastro de Usuários", true);
        setContentPane(Screen2);
        this.setSize(580, 400);
        this.setLocationRelativeTo(parentUser);
        cancelarButtonUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.setVisible(true);
    }
}

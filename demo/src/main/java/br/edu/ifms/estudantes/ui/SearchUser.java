package br.edu.ifms.estudantes.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SearchUser extends JFrame{
    private JPanel SearchUser;
    private JButton novoButton;
    private JButton listarTodosButton;
    private JButton sairButton;
    private JButton SearchButton;
    private JTextField SearchInput;
    private JPanel SearchPanel;

    public Styles styles = new Styles();

    public SearchUser(JFrame searchUser) {
        setContentPane(SearchUser);
        this.setSize(600, 450);
        this.setLocationRelativeTo(searchUser);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        styles.styleButton(SearchButton);
        styles.styleTextField(SearchInput);
        styles.styleButton(novoButton);
        styles.styleButton(listarTodosButton);
        styles.styleButtonMenu(sairButton);

        SearchPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        SearchButton.setIcon(styles.loadIcon("/images/search.png"));

        configureSearchInput();
        novoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSearchUserForm();
            }
        });
        listarTodosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.setVisible(true);
    }

    private void configureSearchInput() {
        SearchInput.setText("Busque por id ou nome do usuário");
        SearchInput.setForeground(Color.GRAY);
        SearchInput.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (SearchInput.getText().equals("Busque por id ou nome do usuário")) {
                    SearchInput.setText("");
                    SearchInput.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (SearchInput.getText().equals("")) {
                    SearchInput.setText("Busque por id ou nome do usuário");
                    SearchInput.setForeground(Color.GRAY);
                }
            }
        });
    }
    public void openSearchUserForm() {
        new UserFormView(this);
    }
}

package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.controller.UserController;
import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.model.UserModel;

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
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            searchUser();
            }
        });

    }

    private void searchUser() {
        String value = SearchInput.getText().trim();
        UserController controller = new UserController();
        UserModel resultado;

        try {
            int id = Integer.parseInt(value);
            resultado = controller.getUser(id);
        } catch (NumberFormatException e) {
            resultado = controller.getUser(value);
        }

        if (resultado != null) {
            UserModel finalResultado = resultado;
            SwingUtilities.invokeLater(() -> new ResultsUsers(SearchUser, finalResultado).setVisible(true));
            displayUserDetails(resultado);
        } else {
            JOptionPane.showMessageDialog(this, "Item não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void displayUserDetails(UserModel user) {
        System.out.println("Id: " + user.getNumberId());
        System.out.println("Nome: " + user.getNome());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Sexo" + user.getSexo());
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

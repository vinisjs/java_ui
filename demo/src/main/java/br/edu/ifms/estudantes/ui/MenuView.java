package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.model.BookModel;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.Objects;

public class MenuView extends JFrame {
    private JPanel MenuScreen;
    private JPanel MenuBorder;
    private JButton livrosButton;
    private JButton usuariosButton;
    private JButton emprestimosButton;
    private JPanel Content;
    private JButton sairButton;
    private JLabel titleLabel;;

    private Styles styles = new Styles();

    public MenuView(JPanel splashScreen) {
        setTitle("Menu de opções");
        setContentPane(MenuScreen);
        this.setSize(800, 500);
        this.setLocationRelativeTo(splashScreen);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        styleComponents();
        addActionListeners();

        MenuBorder.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));

        this.setVisible(true);
    }

    private void styleComponents() {
        MenuBorder.setLayout(new BoxLayout(MenuBorder, BoxLayout.Y_AXIS));
        MenuBorder.setBorder(new EmptyBorder(20, 20, 20, 20));
        livrosButton.setIcon(styles.loadIcon("/images/book.png"));
        usuariosButton.setIcon(styles.loadIcon("/images/users.png"));
        emprestimosButton.setIcon(styles.loadIcon("/images/thumbs-up.png"));
        sairButton.setIcon(styles.loadIcon("/images/log-out.png"));

        styles.styleTitleLabelMenu(titleLabel);
        styles.styleButtonMenu(livrosButton);
        styles.styleButtonMenu(usuariosButton);
        styles.styleButtonMenu(emprestimosButton);
        styles.styleButtonMenu(sairButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(livrosButton);
        buttonPanel.add(usuariosButton);
        buttonPanel.add(emprestimosButton);

        Dimension buttonSize = new Dimension(180, 50);
        livrosButton.setPreferredSize(buttonSize);
        usuariosButton.setPreferredSize(buttonSize);
        emprestimosButton.setPreferredSize(buttonSize);
        sairButton.setPreferredSize(new Dimension(180, 40));

        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exitPanel.add(sairButton);

        MenuBorder.add(buttonPanel, BorderLayout.CENTER);
        MenuBorder.add(exitPanel, BorderLayout.SOUTH);
    }

    private void addActionListeners() {
        livrosButton.addActionListener(e -> openSearchForm());
        usuariosButton.addActionListener(e -> openUserForm());
        sairButton.addActionListener(e -> dispose());
    }


    private void openSearchForm() {
        new SearchBook(this);
    }

    private void openUserForm() {
        new SearchUser(this);
    }
}
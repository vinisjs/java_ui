package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.model.BookModel;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

public class MenuView extends JFrame{
    private JPanel MenuScreen;
    private JPanel MenuBorder;
    private JButton livrosButton;
    private JButton usuariosButton;
    private JButton emprestimosButton;
    private JButton sairButton;
    private JPanel Content;
    private JLabel titleLabel;
    private JButton SearchButton;
    private JTextField SearchInput;
    private JPanel SearchPanel;
    private JButton allBooksButton;

    public BookModel book = new BookModel();
    public Styles styles = new Styles();

    public MenuView(JPanel splashScreen) {
        setTitle("Menu de opções");
        setContentPane(MenuScreen);
        this.setSize(600, 450);
        this.setLocationRelativeTo(splashScreen);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        SearchPanel.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 1, 0, Color.BLACK),
                new EmptyBorder(0, 0, 15, 0)
        ));

        MenuBorder.setLayout(new BoxLayout(MenuBorder, BoxLayout.Y_AXIS));
        MenuBorder.setBorder(new EmptyBorder(20, 20, 20, 20));

        SearchInput.setText("Busque por id ou nome do livro");
        SearchInput.setForeground(Color.GRAY);

        SearchInput.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (SearchInput.getText().equals("Busque por id ou nome do livro")) {
                    SearchInput.setText("");
                    SearchInput.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (SearchInput.getText().equals("")) {
                    SearchInput.setText("Busque por id ou nome do livro");
                    SearchInput.setForeground(Color.GRAY);
                }
            }
        });

        MenuBorder.add(Box.createVerticalStrut(20));

        styles.styleTitleLabelMenu(titleLabel);
        MenuBorder.add(titleLabel);
        MenuBorder.add(Box.createVerticalStrut(20));

        SearchButton.setIcon(loadIcon("/images/search.png"));
        livrosButton.setIcon(loadIcon("/images/book.png"));
        usuariosButton.setIcon(loadIcon("/images/users.png"));
        emprestimosButton.setIcon(loadIcon("/images/thumbs-up.png"));
        sairButton.setIcon(loadIcon("/images/log-out.png"));

        styles.styleButton(SearchButton);
        styles.styleButton(allBooksButton);
        styles.styleButtonMenu(livrosButton);
        styles.styleButtonMenu(usuariosButton);
        styles.styleButtonMenu(emprestimosButton);
        styles.styleButtonMenu(sairButton);

        addCenteredButtonMenu(livrosButton);
        addCenteredButtonMenu(usuariosButton);
        addCenteredButtonMenu(emprestimosButton);
        addCenteredButtonMenu(sairButton);

        MenuBorder.add(Box.createVerticalGlue());

        this.setVisible(true);
        livrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBookForm();
            }
        });
        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUserForm();
            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = SearchInput.getText();

                BookController controller = new BookController();
                BookModel resultado = controller.getBook(value);

                SwingUtilities.invokeLater(() -> new ShowAllData(resultado).setVisible(true));
                if (resultado != null) {
                    System.out.println("Livro encontrado:");
                    System.out.println("Título: " + resultado.getTitulo());
                    System.out.println("Autor: " + resultado.getAutor());
                    System.out.println("ISBN: " + resultado.getISBN());
                    System.out.println("Quantidade: " + resultado.getQuantidade());
                    System.out.println("Tema: " + resultado.getTema());
                    System.out.println("Data de Publicação: " + resultado.getData_publicacao());

                    book = resultado;
                } else {
                    System.out.println("Livro não encontrado.");
                }
            }
        });

        allBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookController controller = new BookController();
                List<BookModel> livros = controller.getAllBooks();

                if (livros != null && !livros.isEmpty()) {
                    ShowAllData.showAllBooks(livros);
                } else {
                    System.out.println("Nenhum livro encontrado.");
                }

                if (livros != null && !livros.isEmpty()) {

                    System.out.println("Livros encontrados:");
                    for (BookModel livro : livros) {
                        System.out.println("----------------------------");
                        System.out.println("ID: " + livro.getNumberId());
                        System.out.println("Título: " + livro.getTitulo());
                        System.out.println("Autor: " + livro.getAutor());
                        System.out.println("ISBN: " + livro.getISBN());
                        System.out.println("Quantidade: " + livro.getQuantidade());
                        System.out.println("Tema: " + livro.getTema());
                        System.out.println("Data de Publicação: " + livro.getData_publicacao());
                    }
                } else {
                    System.out.println("Nenhum livro encontrado.");
                }
            }
        });

    }

    private void addCenteredButtonMenu(JButton button) {
        MenuBorder.add(button);
        MenuBorder.add(Box.createVerticalStrut(10));
    }

    private void openBookForm() {
        new BookFormView(this);
    }

    private void openUserForm() {
        new UserFormView(this);
    }
    
    private ImageIcon loadIcon(String path) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}

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
    private JButton sairButton;
    private JPanel Content;
    private JLabel titleLabel;
    private JButton SearchButton;
    private JTextField SearchInput;
    private JPanel SearchPanel;
    private JButton allBooksButton;

    private Styles styles = new Styles();

    public MenuView(JPanel splashScreen) {
        setTitle("Menu de opções");
        setContentPane(MenuScreen);
        this.setSize(600, 450);
        this.setLocationRelativeTo(splashScreen);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        configureSearchPanel();
        configureSearchInput();
        styleComponents();
        addActionListeners();

        this.setVisible(true);
    }

    private void configureSearchPanel() {
        SearchPanel.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 1, 0, Color.BLACK),
                new EmptyBorder(0, 0, 15, 0)
        ));
    }

    private void configureSearchInput() {
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
    }

    private void styleComponents() {
        MenuBorder.setLayout(new BoxLayout(MenuBorder, BoxLayout.Y_AXIS));
        MenuBorder.setBorder(new EmptyBorder(20, 20, 20, 20));
        SearchButton.setIcon(loadIcon("/images/search.png"));
        livrosButton.setIcon(loadIcon("/images/book.png"));
        usuariosButton.setIcon(loadIcon("/images/users.png"));
        emprestimosButton.setIcon(loadIcon("/images/thumbs-up.png"));
        sairButton.setIcon(loadIcon("/images/log-out.png"));

        styles.styleTitleLabelMenu(titleLabel);
        styles.styleButton(SearchButton);
        styles.styleButton(allBooksButton);
        styles.styleTextField(SearchInput);
        styles.styleButtonMenu(livrosButton);
        styles.styleButtonMenu(usuariosButton);
        styles.styleButtonMenu(emprestimosButton);
        styles.styleButtonMenu(sairButton);

        addCenteredButtonMenu(livrosButton);
        addCenteredButtonMenu(usuariosButton);
        addCenteredButtonMenu(emprestimosButton);
        addCenteredButtonMenu(sairButton);
    }

    private void addCenteredButtonMenu(JButton button) {
        MenuBorder.add(button);
        MenuBorder.add(Box.createVerticalStrut(10));
    }

    private void addActionListeners() {
        livrosButton.addActionListener(e -> openBookForm());
        usuariosButton.addActionListener(e -> openUserForm());
        sairButton.addActionListener(e -> dispose());
        SearchButton.addActionListener(e -> searchBook());
        allBooksButton.addActionListener(e -> showAllBooks());
    }

    private void searchBook() {
        String value = SearchInput.getText();
        BookController controller = new BookController();
        BookModel resultado = controller.getBook(value);

        SwingUtilities.invokeLater(() -> new ResultsForm(MenuScreen, resultado).setVisible(true));

        // SwingUtilities.invokeLater(() -> new ShowAllData(resultado).setVisible(true));

        if (resultado != null) {
            System.out.println("Livro encontrado:");
            displayBookDetails(resultado);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void displayBookDetails(BookModel book) {
        System.out.println("Título: " + book.getTitulo());
        System.out.println("Autor: " + book.getAutor());
        System.out.println("ISBN: " + book.getISBN());
        System.out.println("Quantidade: " + book.getQuantidade());
        System.out.println("Tema: " + book.getTema());
        System.out.println("Data de Publicação: " + book.getData_publicacao());
    }

    private void showAllBooks() {
        BookController controller = new BookController();
        List<BookModel> livros = controller.getAllBooks();

        if (livros != null && !livros.isEmpty()) {
            ShowAllData.showAllBooks(livros);
            livros.forEach(this::displayBookDetails);
        } else {
            System.out.println("Nenhum livro encontrado.");
        }
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
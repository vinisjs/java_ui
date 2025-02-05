package br.edu.ifms.estudantes.ui.search;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.ui.menu.ShowAllTables;
import br.edu.ifms.estudantes.ui.register.RegisterBook;
import br.edu.ifms.estudantes.ui.results.ResultBook;
import br.edu.ifms.estudantes.util.Styles;
import br.edu.ifms.estudantes.util.Utils;

import java.util.List;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBook extends JFrame{
    private JPanel SearchBook;
    private JButton novoButton;
    private JButton listarTodosButton;
    private JButton sairButton;
    private JButton cancelarButton;
    private JButton SearchButton;
    private JTextField SearchInput;
    private JPanel SearchPanel;
    private JLabel BookLabel;

    public Styles styles = new Styles();
    public Utils utils = new Utils();

    public SearchBook(JFrame search) {
        setContentPane(SearchBook);
        this.setSize(600, 450);
        this.setLocationRelativeTo(search);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        styles.styleButton(SearchButton);
        styles.styleTextField(SearchInput);
        styles.styleButton(novoButton);
        styles.styleButton(listarTodosButton);
        styles.styleButtonMenu(sairButton);

        SearchPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        SearchButton.setIcon(styles.loadIcon("/images/search.png"));
        utils.configureSearchInput(SearchInput, "Busque por id ou nome do livro");

        novoButton.addActionListener(e -> openSearchBookForm());
        listarTodosButton.addActionListener(e -> showAllBooks());
        sairButton.addActionListener(e -> dispose());
        SearchButton.addActionListener(e -> searchBook());
        SearchInput.addActionListener(e -> searchBook());

        this.setVisible(true);
    }

    private void showAllBooks() {
        BookController controller = new BookController();
        List<BookModel> livros = controller.getAllBooks();

        if (livros != null && !livros.isEmpty()) {
            new ShowAllTables().showAllBooks(livros);
            livros.forEach(this::displayBookDetails);
        } else {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    private void searchBook() {
        String value = SearchInput.getText().trim();
        BookController controller = new BookController();
        BookModel resultado;

        try {
            int id = Integer.parseInt(value);
            resultado = controller.getBook(id);
        } catch (NumberFormatException e) {
            resultado = controller.getBook(value);
        }

        if (resultado != null) {
            BookModel finalResultado = resultado;
            SwingUtilities.invokeLater(() -> new ResultBook(SearchBook, finalResultado).setVisible(true));
            displayBookDetails(resultado);
        } else {
            JOptionPane.showMessageDialog(this, "Item não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
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

    public void openSearchBookForm() {
        new RegisterBook(this);
    }
}

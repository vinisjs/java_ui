package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.model.BookModel;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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

        configureSearchInput();
        novoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSearchBookForm();
            }
        });
        listarTodosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllBooks();
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
                searchBook();
            }
        });
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
            SwingUtilities.invokeLater(() -> new ResultsForm(SearchBook, finalResultado).setVisible(true));
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
        new BookFormView(this);
    }
}

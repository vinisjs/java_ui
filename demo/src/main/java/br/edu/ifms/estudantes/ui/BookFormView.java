package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.util.Styles;
import br.edu.ifms.estudantes.util.Utils;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BookFormView extends JDialog {
    private JPanel Screen1;
    private JTextField textTheme;
    private JTextField textTitle;
    private JPanel CampoTema;
    private JPanel CampoTitulo;
    private JPanel CampoAutor;
    private JTextField textAuthor;
    private JPanel CampoISBN;
    private JTextField textIsbn;
    private JPanel CampoData;
    private JPanel CampoExemplares;
    private JTextField textTotal;
    private JButton cadastrarButton;
    private JButton cancelarButton;
    private JFormattedTextField formattedTextDate;

    public Styles styles = new Styles();
    public Utils utils = new Utils();

    public BookFormView(JFrame parent) {
        super(parent, "Cadastro de Livros", true);
        this.setContentPane(Screen1);
        this.setSize(600, 450);
        this.setLocationRelativeTo(parent);

        styles.styleTextField(textTheme);
        styles.styleTextField(textTitle);
        styles.styleTextField(textAuthor);
        styles.styleTextField(textIsbn);
        styles.styleTextField(formattedTextDate);
        styles.styleTextField(textTotal);

        styles.alignFields(CampoTema, "Tema:", textTheme);
        styles.alignFields(CampoTitulo, "Título:", textTitle);
        styles.alignFields(CampoAutor, "Autor:", textAuthor);
        styles.alignFields(CampoISBN, "ISBN:", textIsbn);
        styles.alignFields(CampoData, "Data:", formattedTextDate);
        styles.alignFields(CampoExemplares, "Exemplares:", textTotal);

        styles.styleButton(cadastrarButton);
        styles.styleButton(cancelarButton);

        utils.maskDate(formattedTextDate);

        cadastrarButton.addActionListener(e -> cadastrarLivro());

        cancelarButton.addActionListener(e -> dispose());

        this.setVisible(true);
    }

    private void cadastrarLivro() {
        try {
            if (textTitle.getText().isEmpty() || textAuthor.getText().isEmpty() ||
                    textTheme.getText().isEmpty() || textIsbn.getText().isEmpty() ||
                    formattedTextDate.getText().trim().equals("__/__/____") ||
                    textTotal.getText().isEmpty()) {

                throw new IllegalArgumentException("Por favor, preencha todos os campos corretamente.");
            }

            int quantidade = Integer.parseInt(textTotal.getText());
            if (quantidade <= 0) {
                throw new NumberFormatException("Quantidade deve ser um número maior que zero.");
            }

            String dataStr = formattedTextDate.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            dateFormat.setLenient(false);

            Date dataPublicacao;
            try {
                dataPublicacao = dateFormat.parse(dataStr);
            } catch (Exception e) {
                throw new IllegalArgumentException("Data inválida. Utilize uma data válida.");
            }

            BookModel book = new BookModel();
            book.setTitulo(textTitle.getText());
            book.setAutor(textAuthor.getText());
            book.setTema(textTheme.getText());
            book.setISBN(textIsbn.getText());
            book.setData_publicacao(formattedTextDate.getText());
            book.setQuantidade(quantidade);
            BookController controller = new BookController();
            controller.saveOneBook(book);

            JOptionPane.showMessageDialog(
                    this,
                    "Livro salvo com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            System.out.println("Consultando o livro...");
            BookModel retrievedBook = controller.getBook(2);
            if (retrievedBook != null) {
                System.out.println("Livro encontrado: " + retrievedBook.getTitulo());
            } else {
                System.out.println("Livro não encontrado.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Quantidade deve ser um número válido.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ocorreu um erro inesperado: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

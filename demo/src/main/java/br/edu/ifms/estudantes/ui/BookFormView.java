package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.model.BookModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class BookFormView extends JDialog{
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

    public BookFormView(JFrame parent) {
        super(parent, "Cadastro de Livros", true);
        this.setContentPane(Screen1);
        this.setSize(650, 450);
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

        MaskFormatter mascaradata = null;

            try {
                mascaradata = new MaskFormatter("##/##/####");
                mascaradata.setPlaceholderCharacter('_');

                formattedTextDate.setFormatterFactory(new DefaultFormatterFactory(mascaradata));

            } catch (ParseException ex) {
                System.out.println("Deu B.O");
            }

        BookModel book = new BookModel();
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookModel book = new BookModel();
                book.setTitulo(textTitle.getText());
                book.setAutor(textAuthor.getText());
                book.setTema(textTheme.getText());
                book.setISBN(textIsbn.getText());
                book.setData_publicacao(formattedTextDate.getText());
                book.setQuantidade(Integer.parseInt(textTotal.getText()));

                BookController controller = new BookController();

                // Salvar o livro
                System.out.println("Salvando o livro...");
                controller.saveBook(book);

                // Consultar o livro pelo ID ou título
                System.out.println("Consultando o livro...");
                BookModel retrievedBook = controller.getBook(2); // Substituir por um ID ou título válido

                if (retrievedBook != null) {
                    System.out.println("Livro encontrado: " + retrievedBook.getTitulo());
                } else {
                    System.out.println("Livro não encontrado.");
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.setVisible(true);
    }
}

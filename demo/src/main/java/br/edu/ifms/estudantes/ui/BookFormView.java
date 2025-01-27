package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.model.BookModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField textDate;
    private JPanel CampoExemplares;
    private JTextField textTotal;
    private JButton cadastrarButton;
    private JButton cancelarButton;

    public BookFormView(JFrame parent) {
        super(parent, "Cadastro de Livros", true);
        this.setContentPane(Screen1);
        this.setSize(580, 400);
        this.setLocationRelativeTo(parent);

        BookModel book = new BookModel();
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Captura os valores dos campos de texto
                book.setTitulo(textTitle.getText()); // tema
                book.setAutor(textAuthor.getText()); // titulo
                book.setTema(textTheme.getText()); // autor
                book.setISBN(textIsbn.getText()); //
                book.setData_publicacao(textDate.getText());
                book.setQuantidade(Integer.parseInt(textTotal.getText()));


                new BookController().controller(book);
                BookModel re = new BookController().getBook(2);
                System.out.println(re.getTitulo());

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

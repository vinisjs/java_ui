package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.util.Styles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultsForm extends JFrame {
    private JPanel ResultScreenBook;
    private JPanel JPanelAllScreen;
    private JPanel JPanelLabel;
    private JPanel JPanelTitle;
    private JLabel labelTitle;
    private JTextField textFieldTitle;
    private JPanel JPanelTheme;
    private JLabel labelTheme;
    private JTextField textFieldTheme;
    private JPanel JPanelAuthor;
    private JTextField textFieldAuthor;
    private JPanel JPanelIsbn;
    private JLabel labelIsbn;
    private JTextField textFieldIsbn;
    private JPanel JPanelDate;
    private JPanel JPanelQtd;
    private JLabel labelDate;
    private JLabel labelQtd;
    private JTextField textFieldQtd;
    private JButton fecharButton;
    private JButton editarButton;
    private JFormattedTextField formattedTextDateUser;
    private JButton excluirButton;

    public Styles styles = new Styles();

    BookModel book = new BookModel();

    public ResultsForm(JPanel MenuView, BookModel resultado) {

        setTitle("Resultados de Livros");
        setContentPane(ResultScreenBook);
        this.setSize(600, 450);
        this.setLocationRelativeTo(MenuView);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        styles.styleTextField(textFieldTheme);
        styles.styleTextField(textFieldTitle);
        styles.styleTextField(textFieldAuthor);
        styles.styleTextField(textFieldIsbn);
        styles.styleTextField(formattedTextDateUser);
        styles.styleTextField(textFieldQtd);

        textFieldTitle.setText(resultado.getTitulo());
        textFieldTheme.setText(resultado.getTema());
        textFieldAuthor.setText(resultado.getAutor());
        textFieldIsbn.setText(resultado.getISBN());
        formattedTextDateUser.setText(resultado.getData_publicacao());
        textFieldQtd.setText(String.valueOf(resultado.getQuantidade()));

        styles.alignFields(JPanelTheme, "Tema:", textFieldTheme);
        styles.alignFields(JPanelTitle, "Título:", textFieldTitle);
        styles.alignFields(JPanelAuthor, "Autor:", textFieldAuthor);
        styles.alignFields(JPanelIsbn, "ISBN:", textFieldIsbn);
        styles.alignFields(JPanelDate, "Data:", formattedTextDateUser);
        styles.alignFields(JPanelQtd, "Quantidade:", textFieldQtd);

        styles.styleButton(fecharButton);
        styles.styleButton(editarButton);
        styles.styleButton(excluirButton);

        textFieldTitle.setEditable(false);
        textFieldTheme.setEditable(false);
        textFieldAuthor.setEditable(false);
        textFieldIsbn.setEditable(false);
        formattedTextDateUser.setEditable(false);
        textFieldQtd.setEditable(false);

        this.setVisible(true);
        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (editarButton.getText().equals("Editar")) {
                    textFieldTitle.setEditable(true);
                    textFieldTheme.setEditable(true);
                    textFieldAuthor.setEditable(true);
                    textFieldIsbn.setEditable(true);
                    formattedTextDateUser.setEditable(true);
                    textFieldQtd.setEditable(true);

                    editarButton.setText("Aplicar");

                } else {

                    textFieldTitle.setEditable(false);
                    textFieldTheme.setEditable(false);
                    textFieldAuthor.setEditable(false);
                    textFieldIsbn.setEditable(false);
                    formattedTextDateUser.setEditable(false);
                    textFieldQtd.setEditable(false);

                    book.setNumberId(resultado.getNumberId());
                    book.setTitulo( textFieldTitle.getText());
                    book.setTema( textFieldTheme.getText());
                    book.setAutor( textFieldAuthor.getText());
                    book.setISBN( textFieldIsbn.getText());
                    book.setData_publicacao( formattedTextDateUser.getText());
                    book.setQuantidade(Integer.parseInt(textFieldQtd.getText()));

                    new BookController().UpdateBook(book);

                    editarButton.setText("Editar");
                }

            }
        });

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(
                        ResultsForm.this,
                        "Tem certeza de que deseja excluir este livro?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirmation == JOptionPane.YES_OPTION) {
                    book.setNumberId(resultado.getNumberId());
                    new BookController().DeleteById(book.getNumberId());
                    dispose();
                }
            }
        });
    }
}

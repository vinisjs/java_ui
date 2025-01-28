package br.edu.ifms.estudantes.ui;

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

    public Styles styles = new Styles();

    public ResultsForm() {
        setTitle("Resultados de Livros");
        setContentPane(ResultScreenBook);
        this.setSize(600, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        styles.styleTextField(textFieldTheme);
        styles.styleTextField(textFieldTitle);
        styles.styleTextField(textFieldAuthor);
        styles.styleTextField(textFieldIsbn);
        styles.styleTextField(formattedTextDateUser);
        styles.styleTextField(textFieldQtd);

        textFieldTitle.setText("Dom Casmurro");
        textFieldTheme.setText("Romance");
        textFieldAuthor.setText("Machado de Assis");
        textFieldIsbn.setText("978-85-359-0277-2");
        formattedTextDateUser.setText("01/01/1900"); // Formato de data
        textFieldQtd.setText("10");

        styles.alignFields(JPanelTheme, "Tema:", textFieldTheme);
        styles.alignFields(JPanelTitle, "Título:", textFieldTitle);
        styles.alignFields(JPanelAuthor, "Autor:", textFieldAuthor);
        styles.alignFields(JPanelIsbn, "ISBN:", textFieldIsbn);
        styles.alignFields(JPanelDate, "Data:", formattedTextDateUser);
        styles.alignFields(JPanelQtd, "Quantidade:", textFieldQtd);

        styles.styleButton(fecharButton);
        styles.styleButton(editarButton);

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

                    String title = textFieldTitle.getText();
                    String theme = textFieldTheme.getText();
                    String author = textFieldAuthor.getText();
                    String isbn = textFieldIsbn.getText();
                    String date = formattedTextDateUser.getText();
                    String quantity = textFieldQtd.getText();

                    System.out.println("Título: " + title);
                    System.out.println("Tema: " + theme);
                    System.out.println("Autor: " + author);
                    System.out.println("ISBN: " + isbn);
                    System.out.println("Data: " + date);
                    System.out.println("Quantidade: " + quantity);

                    textFieldTitle.setEditable(false);
                    textFieldTheme.setEditable(false);
                    textFieldAuthor.setEditable(false);
                    textFieldIsbn.setEditable(false);
                    formattedTextDateUser.setEditable(false);
                    textFieldQtd.setEditable(false);

                    editarButton.setText("Editar");
                }
            }
        });
    }
}

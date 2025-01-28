package br.edu.ifms.estudantes.ui;

import javax.swing.*;

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
    private JTextField textFieldDate;
    private JPanel JPanelQtd;
    private JLabel labelDate;
    private JLabel labelQtd;
    private JTextField textFieldQtd;
    private JButton fecharButton;
    private JButton editarButton;

    public ResultsForm(JPanel MenuView) {
        setTitle("Resultados de Livros");
        setContentPane(ResultScreenBook);
        this.setSize(550, 400);
        this.setLocationRelativeTo(MenuView);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public ResultsForm() {
        setTitle("Resultados de Livros");
        setContentPane(ResultScreenBook);
        this.setSize(550, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

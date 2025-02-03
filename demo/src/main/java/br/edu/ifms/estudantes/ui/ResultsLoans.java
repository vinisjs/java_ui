package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.model.BorrowModel;
import br.edu.ifms.estudantes.util.Styles;

import javax.swing.*;

public class ResultsLoans extends JFrame{
    private JPanel ResultsScreenLoan;
    private JButton SearchResult;
    private JTextField LoanInput;
    private JTextField StatusInput;
    private JTextField NameInput;
    private JTextField BookInput;
    private JButton salvarButton;
    private JButton cancelarButton;
    private JButton atualizarButton;
    private JFormattedTextField DateInputPreview;
    private JFormattedTextField DateInput;

    public Styles styles = new Styles();

    public ResultsLoans(JPanel MenuView, BorrowModel borrowModel) {
        setTitle("");
//        setContentPane(ResultsLoans);
        this.setSize(600, 450);
        this.setLocationRelativeTo(MenuView);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

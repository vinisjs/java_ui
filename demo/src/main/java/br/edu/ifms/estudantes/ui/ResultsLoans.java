package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.model.BorrowModel;
import br.edu.ifms.estudantes.util.Styles;
import br.edu.ifms.estudantes.util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPanel JpanelStatus;
    private JPanel JpanelName;
    private JPanel JpanelBook;
    private JPanel JpanelPreview;
    private JPanel JpanelDate;
    private JPanel SearchPanel;

    public Styles styles = new Styles();
    public Utils utils = new Utils();

    public ResultsLoans(JFrame resultsLoan) {
        setTitle("");
        setContentPane(ResultsScreenLoan);
        this.setSize(600, 450);
        this.setLocationRelativeTo(resultsLoan);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        utils.configureSearchInput(LoanInput, "Busque por id ou nome do empréstimo");

        utils.maskDate(DateInputPreview);
        utils.maskDate(DateInput);

        SearchPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        styles.styleButton(SearchResult);
        styles.styleTextField(LoanInput);
        styles.styleTextField(StatusInput);
        styles.styleTextField(NameInput);
        styles.styleTextField(BookInput);
        styles.styleTextField(DateInputPreview);
        styles.styleTextField(DateInput);

        styles.alignFields(JpanelStatus, "Status:", StatusInput);
        styles.alignFields(JpanelName, "Nome:", NameInput);
        styles.alignFields(JpanelBook, "livro:", BookInput);
        styles.alignFields(JpanelPreview, "Data devolução prevista:", DateInputPreview);
        styles.alignFields(JpanelDate, "Data devolução:", DateInput);

        SearchResult.setIcon(styles.loadIcon("/images/search.png"));

        styles.styleButton(cancelarButton);
        styles.styleButton(atualizarButton);
        styles.styleButtonMenu(salvarButton);

        this.setVisible(true);
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

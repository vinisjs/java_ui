package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.util.Styles;
import br.edu.ifms.estudantes.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchLoan extends JFrame{
    private JPanel LoanMain;
    private JButton SearchLoan;
    private JTextField SearchLoanInput;
    private JButton novoButton;
    private JButton devolucoesButton;
    private JButton listarTodosButton;
    private JButton sairButton;
    private JPanel panel;

    public Styles styles = new Styles();
    public Utils utils = new Utils();

    public SearchLoan(JFrame searchLoan) {
        setContentPane(LoanMain);
        this.setSize(600, 450);
        this.setLocationRelativeTo(searchLoan);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        styles.styleButton(SearchLoan);
        styles.styleTextField(SearchLoanInput);
        styles.styleButton(novoButton);
        styles.styleButton(devolucoesButton);
        styles.styleButton(listarTodosButton);
        styles.styleButtonMenu(sairButton);

        SearchLoan.setIcon(styles.loadIcon("/images/search.png"));
        utils.configureSearchInput(SearchLoanInput, "Busque por id ou nome do empréstimo");

        this.setVisible(true);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        novoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSearchLoan();
            }
        });
    }

    public void openSearchLoan() {
        new LoanFormView(this);
    }
}

package br.edu.ifms.estudantes.ui.search;

import br.edu.ifms.estudantes.controller.BorrowController;
import br.edu.ifms.estudantes.model.BorrowModel;
import br.edu.ifms.estudantes.model.CartModel;
import br.edu.ifms.estudantes.ui.menu.ShowAllTables;
import br.edu.ifms.estudantes.ui.register.RegisterLoan;
import br.edu.ifms.estudantes.ui.results.ResultsLoans;
import br.edu.ifms.estudantes.util.Styles;
import br.edu.ifms.estudantes.util.Utils;

import javax.swing.*;
import java.util.List;

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
        sairButton.addActionListener(e -> dispose());
        novoButton.addActionListener(e -> openSearchLoan());
        devolucoesButton.addActionListener(e -> openResultsLoan());
        listarTodosButton.addActionListener(e -> showAll());

        this.setVisible(true);
    }

    public void showAll() {

        BorrowController controller = new BorrowController();
        List<BorrowModel> borrow = controller.getAllBorrow();

        if (borrow != null && !borrow.isEmpty()) {
            new ShowAllTables().showAllBorrow(borrow);

        } else {
            System.out.println("Nenhum livro encontrado.");
        }


    }

    public void openResultsLoan() {
        CartModel cartModel = new CartModel();
        new ResultsLoans(this, cartModel);
    }

    public void openSearchLoan() {
        new RegisterLoan(this);
    }
}

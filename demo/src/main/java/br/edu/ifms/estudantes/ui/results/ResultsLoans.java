package br.edu.ifms.estudantes.ui.results;

import br.edu.ifms.estudantes.controller.BorrowController;
import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.model.BorrowModel;
import br.edu.ifms.estudantes.model.CartModel;
import br.edu.ifms.estudantes.model.UserModel;
import br.edu.ifms.estudantes.ui.menu.BagMenu;
import br.edu.ifms.estudantes.ui.search.SearchLoan;
import br.edu.ifms.estudantes.util.Styles;
import br.edu.ifms.estudantes.util.Utils;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class ResultsLoans extends JDialog{
    private JPanel ResultsScreenLoan;
    private JButton SearchResult;
    private JTextField LoanInput;
    private JButton devolverButton;
    private JButton cancelarButton;
    private JButton atualizarButton;
    private JFormattedTextField DateInputPreview;
    private JFormattedTextField DateInput;
    private JPanel JpanelUser;
    private JPanel JpanelTable;
    private JPanel JpanelPreview;
    private JPanel JpanelDate;
    private JPanel SearchPanel;
    private JLabel UserLabel;
    private JLabel DateLabel;
    private JLabel StatusLabel;
    private JTable tableLoan;
    private JScrollPane tableScrollPane;
    private JLabel TotalLabel;
    private JLabel PreviewLabel;
    private JLabel DevolutionLabel;
    private JPanel JPanelDevolution;
    private JLabel StatusLabelFix;
    private JLabel UserNameLabel;
    private JLabel DateALabel;

    public Styles styles = new Styles();
    public Utils utils = new Utils();

    public ResultsLoans(SearchLoan parentLoan , CartModel cartModel) {
        super(parentLoan, "Sacola", true);
        setContentPane(ResultsScreenLoan);
        this.setSize(800, 500);
        this.setLocationRelativeTo(parentLoan);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        utils.configureSearchInput(LoanInput, "Busque por id ou nome do empréstimo");

        styles.styleButton(SearchResult);
        styles.styleTextField(LoanInput);
        styles.styleTable(tableLoan);

        SearchResult.setIcon(styles.loadIcon("/images/search.png"));

        JpanelDate.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        JPanelDevolution.setBorder(new MatteBorder(1, 0, 1, 0, Color.BLACK));

        styles.styleButton(cancelarButton);
        styles.styleButtonMenu(devolverButton);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        DateLabel.setText(dateFormat.format(currentDate));

        setupTable(cartModel);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        Date returnDate = calendar.getTime();
        PreviewLabel.setText(dateFormat.format(returnDate));
//        updateTotalLabel(cartModel);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        devolverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(
                        ResultsLoans.this,
                        "Tem certeza que deseja devolver o(s) livros?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                );
            }
        });

        styleStatus();

        this.setVisible(true);
    }

    private void styleStatus() {
        JpanelUser.setLayout(new GridLayout(2, 3, 10, 5));

        UserNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        StatusLabelFix.setHorizontalAlignment(SwingConstants.CENTER);
        DateALabel.setHorizontalAlignment(SwingConstants.RIGHT);

        UserLabel.setHorizontalAlignment(SwingConstants.LEFT);
        StatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        DateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JpanelUser.add(UserNameLabel);
        JpanelUser.add(StatusLabelFix);
        JpanelUser.add(DateALabel);
        JpanelUser.add(UserLabel);
        JpanelUser.add(StatusLabel);
        JpanelUser.add(DateLabel);
    }


    private void setupTable(CartModel cartModel) {
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "Livro", "Quantidade"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Map.Entry<BookModel, Integer> entry : cartModel.getBooks().entrySet()) {
            BookModel book = entry.getKey();
            int quantity = entry.getValue();
            tableModel.addRow(new Object[]{book.getNumberId(), book.getTitulo(), quantity, ""});
        }

        tableLoan.setModel(tableModel);
    }
}

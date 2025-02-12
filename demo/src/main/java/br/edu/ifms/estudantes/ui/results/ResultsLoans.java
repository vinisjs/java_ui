package br.edu.ifms.estudantes.ui.results;

import br.edu.ifms.estudantes.controller.BorrowController;
import br.edu.ifms.estudantes.controller.UserController;
import br.edu.ifms.estudantes.model.BorrowModel;
import br.edu.ifms.estudantes.model.CartModel;
import br.edu.ifms.estudantes.model.UserModel;
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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class ResultsLoans extends JDialog {
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

    public ResultsLoans(SearchLoan parentLoan, CartModel cartModel) {
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

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        Date returnDate = calendar.getTime();
        DevolutionLabel.setText(dateFormat.format(returnDate));

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

        SearchResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAll();
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

    private void loadDataToTable(DefaultTableModel tableModel) {
        tableLoan.setModel(tableModel);
        tableLoan.repaint();
    }

    public void showAll() {

        BorrowController borrowController = new BorrowController();
        List<BorrowModel> borrowList = borrowController.getBorrow(LoanInput.getText());

        if (!borrowList.isEmpty()) {
            BorrowModel borrow = borrowList.get(0);
            System.out.println("Borrow Id User: " + borrow.getId_user());

            setupTable(borrowList);

            UserController userController = new UserController();
            UserModel user = userController.getUser(borrow.getId_user());

            System.out.println("user id: " + user.getNumberId());
            System.out.println("user id: " + user.getNome());
            System.out.println("user id: " + user.getEmail());
            UserLabel.setText(user.getNome());

            LocalDate dateOut = convertToLocalDate(borrow.getDateOut());
            LocalDate returnPreview = convertToLocalDate(borrow.getDataReturnPreview());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDateOut = dateOut.format(formatter);
            String formattedReturnPreview = returnPreview.format(formatter);

            long daysBetween = ChronoUnit.DAYS.between(returnPreview, dateOut);

            if (daysBetween > 14) {
                StatusLabel.setText("Atrasado");
            } else {
                StatusLabel.setText("No Prazo");
            }

            TotalLabel.setText(String.valueOf(borrow.getQnt()));
            PreviewLabel.setText(formattedReturnPreview);

        } else {
            System.out.println("Empréstimo não encontrado.");
        }
    }

    // Método para converter Date para LocalDate
    private LocalDate convertToLocalDate(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    // Método para formatar a data em dd-MM-yyyy
    private String formatDate(Date date) {
        if (date == null) return "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public void setupTable(List<BorrowModel> borrow) {
        setTitle("Todos os Empréstimos");

        String[] columnNames = {"Transaction ID", "ID", "ID USER", "ID BOOKS", "DATE OUT", "DATE BACK PREV", "QNT"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (BorrowModel borrowData : borrow) {
            tableModel.addRow(new Object[]{
                    borrowData.getTransactionId(),
                    borrowData.getId(),
                    borrowData.getId_user(),
                    borrowData.getId_book(),
                    formatDate(borrowData.getDateOut()),  // Aplicando formatação
                    formatDate(borrowData.getDataReturnPreview()),  // Aplicando formatação
                    borrowData.getQnt()
            });
        }

        loadDataToTable(tableModel);
        setVisible(true);
    }
}
package br.edu.ifms.estudantes.ui.results;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.controller.BorrowController;
import br.edu.ifms.estudantes.controller.UserController;
import br.edu.ifms.estudantes.model.BookModel;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

        cancelarButton.addActionListener(e -> dispose());

        devolverButton.addActionListener(e -> processarDevolucao());

        SearchResult.addActionListener(e -> showAll());

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
            setupTable(borrowList);

            UserController userController = new UserController();
            UserModel user = userController.getUser(borrow.getId_user());

            UserLabel.setText(user.getNome());

            LocalDate returnPreview = convertToLocalDate(borrow.getDataReturnPreview());
            LocalDate returnDate = convertToLocalDate(borrow.getDataReturn());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedReturnPreview = returnPreview.format(formatter);

            if (returnDate != null) {
                DevolutionLabel.setText(returnDate.format(formatter));
                long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(returnPreview, returnDate);
                StatusLabel.setText(daysBetween <= 14 ? "Dentro do prazo" : "Fora do prazo");
            } else {
                DevolutionLabel.setText("NUL");
                StatusLabel.setText("Pendente");
            }

            PreviewLabel.setText(formattedReturnPreview);
        } else {
            JOptionPane.showMessageDialog(this, "Empréstimo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Empréstimo não encontrado para o termo: " + LoanInput.getText());
        }
    }

    private void processarDevolucao() {
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja devolver o(s) livro(s)?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmation == JOptionPane.YES_OPTION) {
            BorrowController borrowController = new BorrowController();
            BookController bookController = new BookController();

            List<BorrowModel> borrowList = borrowController.getBorrow(LoanInput.getText());

            if (borrowList.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Empréstimo não encontrado.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                System.out.println("Empréstimo não encontrado para o termo: " + LoanInput.getText());
                return;
            }

            for (BorrowModel borrow : borrowList) {
                if (borrow.getDataReturn() != null) {
                    JOptionPane.showMessageDialog(
                            this,
                            "O empréstimo de ID " + borrow.getTransactionId() + " já foi devolvido anteriormente.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );
                    System.out.println("Tentativa de devolução de empréstimo já devolvido: " + borrow.getTransactionId());
                    return;
                }

                Date dataDevolucao = new Date();
                borrow.setDataReturn(dataDevolucao);

                borrowController.UpdateBorrow(borrow);

                BookModel book = bookController.getBook(borrow.getId_book());
                if (book != null) {
                    book.setQuantidade(book.getQuantidade() + borrow.getQnt());
                    bookController.UpdateBook(book);
                }

                System.out.println("Devolução registrada para empréstimo ID: " + borrow.getTransactionId());
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Devolução realizada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            showAll();
        }
    }

    private LocalDate convertToLocalDate(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void setupTable(List<BorrowModel> borrowList) {
        String[] columnNames = {"Transaction ID", "Título do Livro", "Quantidade"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        BookController bookController = new BookController();
        int totalQuantity = 0;

        for (BorrowModel borrowData : borrowList) {
            BookModel book = bookController.getBook(borrowData.getId_book());
            tableModel.addRow(new Object[]{borrowData.getTransactionId(), book != null ? book.getTitulo() : "Não encontrado", borrowData.getQnt()});
            totalQuantity += borrowData.getQnt();
        }

        TotalLabel.setText(String.valueOf(totalQuantity));
        loadDataToTable(tableModel);
    }
}
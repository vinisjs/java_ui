package br.edu.ifms.estudantes.ui.register;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.controller.BorrowController;
import br.edu.ifms.estudantes.controller.UserController;
import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.model.BorrowModel;
import br.edu.ifms.estudantes.model.CartModel;
import br.edu.ifms.estudantes.model.UserModel;
import br.edu.ifms.estudantes.ui.menu.LoanTablesBook;
import br.edu.ifms.estudantes.ui.menu.LoanTablesUsers;
import br.edu.ifms.estudantes.util.Styles;
import br.edu.ifms.estudantes.util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RegisterLoan extends JDialog {
    private JPanel Screen3;
    private JTextField NameLoanInput;
    private JButton SearchButton1;
    private JTextField BookLoanInput;
    private JButton SearchButton2;
    private JFormattedTextField DateLoanInput;
    private JButton salvarButton;
    private JButton cancelarButton;
    private JPanel CampoNome;
    private JPanel CampoLivro;
    private JPanel CampoData;
    private JButton BagButton;
    private JPanel CampoQtd;
    private JButton PlusButton;
    private JButton LessButton;
    private JTextField QtdInput;

    public Styles styles = new Styles();
    public Utils utils = new Utils();
    int value = 0;

    public UserController userController = new UserController();
    public BookController bookController = new BookController();

    UserModel selectedUser = new UserModel();
    BookModel selectedBook = new BookModel();

    private CartModel cartModel = new CartModel();

    public RegisterLoan(JFrame parentLoan) {
        super(parentLoan, "Cadastro de Emprestimos", true);
        this.setContentPane(Screen3);
        this.setSize(600, 450);
        this.setLocationRelativeTo(parentLoan);

        styles.styleTextField(NameLoanInput);
        styles.styleTextField(BookLoanInput);
        styles.styleTextField(DateLoanInput);
        styles.styleTextField(QtdInput);

        styles.alignFieldsLoan(CampoNome, "Nome:", NameLoanInput, SearchButton1);
        styles.alignFieldsLoan(CampoLivro, "livro:", BookLoanInput, SearchButton2);
        styles.alignFieldsQtd(CampoQtd, "Quantidade:", LessButton, QtdInput, PlusButton);
        styles.alignFields(CampoData, "Data devolução:", DateLoanInput);

        SearchButton1.setIcon(styles.loadIcon("/images/search.png"));
        SearchButton2.setIcon(styles.loadIcon("/images/search.png"));
        BagButton.setIcon(styles.loadIcon("/images/bag.png"));
        LessButton.setIcon(styles.loadIcon("/images/less.png"));
        PlusButton.setIcon(styles.loadIcon("/images/plus.png"));

        styles.styleButtonMenu(BagButton);
        styles.styleButton(SearchButton1);
        styles.styleButton(SearchButton2);
        styles.styleButton(PlusButton);
        styles.styleButton(LessButton);
        styles.styleButtonMenu(salvarButton);
        styles.styleButton(cancelarButton);
        QtdInput.setText(String.valueOf(1));
        value = Integer.parseInt(QtdInput.getText());

        utils.maskDate(DateLoanInput);

        utils.configureSearchInput(NameLoanInput, "Busque por id ou nome do usuário");
        utils.configureSearchInput(BookLoanInput, "Busque por id ou nome do livro");

        BagButton.addActionListener(e -> showCart());
        cancelarButton.addActionListener(e -> dispose());

        salvarButton.addActionListener(e -> {
            try {
                if (salvarButton.getText().equals("Adicionar")) {
                    addToCart();
                } else {
                    finalizeLoan();
                    saveLoan();
                }
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        });

        SearchButton1.addActionListener(e -> showAllUsers());
        NameLoanInput.addActionListener(e -> showAllUsers());
        SearchButton2.addActionListener(e -> showAllBooks());
        BookLoanInput.addActionListener(e -> showAllBooks());

        LessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(value > 0) {
                    value -= 1;
                }
                QtdInput.setText(String.valueOf(value));
            }
        });
        PlusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    value += 1;
                    if (value <= 5) {
                        QtdInput.setText(String.valueOf(value));
                    } else {
                        JOptionPane.showMessageDialog(parentLoan, "O empréstimo máximo é de 5 livros.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }

                    updateSaveButton();
                } catch (NumberFormatException ex) {
                    QtdInput.setText("0");
                }
            }
        });

        this.setVisible(true);
    }

    private void updateSaveButton() {
        if (cartModel.getTotalBooks() >= 5) {
            salvarButton.setText("Finalizar");
        } else {
            salvarButton.setText("Adicionar");
        }
    }

    private void addToCart() {
        if (selectedBook != null) {
            int quantity = Integer.parseInt(QtdInput.getText());
            int totalBooksAfterAddition = cartModel.getTotalBooks() + quantity;

            if (totalBooksAfterAddition > 5) {
                JOptionPane.showMessageDialog(this, "Limite de 5 livros por empréstimo excedido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            cartModel.addBook(selectedBook, quantity);
            JOptionPane.showMessageDialog(this, "Livro adicionado ao carrinho!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            updateSaveButton();

            BookLoanInput.setText("");
            BookLoanInput.setEditable(true);
            SearchButton2.setIcon(styles.loadIcon("/images/search.png"));
            selectedBook = null;
            QtdInput.setText("1");
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum livro selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void finalizeLoan() throws ParseException {
        if (cartModel.getBooks().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O carrinho está vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (selectedUser == null) {
            JOptionPane.showMessageDialog(this, "Nenhum usuário selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOut = dateFormat.parse(DateLoanInput.getText());

        BorrowController borrowController = new BorrowController();

        for (Map.Entry<BookModel, Integer> entry : cartModel.getBooks().entrySet()) {
            BookModel book = entry.getKey();
            int quantity = entry.getValue();

            for (int i = 0; i < quantity; i++) {
                BorrowModel borrowModel = new BorrowModel();
                borrowModel.setId_user(selectedUser.getNumberId());
                borrowModel.setId_book(book.getNumberId());
                borrowModel.setDateOut(dateOut);
                borrowModel.setDataReturnPreview(null);
                borrowModel.setDataReturn(null);

                BorrowModel data_return = borrowController.Create(borrowModel);
                borrowController.saveOneBorrow(data_return);
            }
        }

        cartModel.clearCart();
        JOptionPane.showMessageDialog(this, "Empréstimo finalizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }


    private void showCart() {
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(this, "Nenhum usuário selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        Date returnDate = calendar.getTime();

        StringBuilder cartContent = new StringBuilder();
        cartContent.append("ID do Usuário: ").append(selectedUser.getNumberId()).append("\n");
        cartContent.append("Nome do Usuário: ").append(selectedUser.getNome()).append("\n");
        cartContent.append("Data Atual: ").append(dateFormat.format(currentDate)).append("\n");
        cartContent.append("Data de Devolução: ").append(dateFormat.format(returnDate)).append("\n\n");
        cartContent.append("Livros no Carrinho:\n");

        for (Map.Entry<BookModel, Integer> entry : cartModel.getBooks().entrySet()) {
            BookModel book = entry.getKey();
            int quantity = entry.getValue();
            cartContent.append("- ").append(book.getTitulo())
                    .append(" (Quantidade: ").append(quantity).append(")\n");
        }

        cartContent.append("\nTotal de Livros: ").append(cartModel.getTotalBooks());

        JOptionPane.showMessageDialog(this, cartContent.toString(), "Carrinho", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveLoan() throws ParseException {

        BorrowController borrowController = new BorrowController();

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date dateOut = dateFormat.parse(DateLoanInput.getText());
            BorrowModel borrowModel = new BorrowModel();

            utils.validationDate(DateLoanInput);

            borrowModel.setId_user(selectedUser.getNumberId());
            borrowModel.setId_book(selectedBook.getNumberId());
            borrowModel.setDateOut(dateOut);
            borrowModel.setDataReturnPreview(null);
            borrowModel.setDataReturn(null);

            BorrowModel data_return = borrowController.Create(borrowModel);

            borrowController.saveOneBorrow(data_return);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showAllUsers() {
        List<UserModel> users = userController.getAllUser();

        if (users != null && !users.isEmpty()) {
            LoanTablesUsers loanTablesUser = new LoanTablesUsers(this, users);
            selectedUser = loanTablesUser.getSelectedUser();

            if (selectedUser != null) {
                NameLoanInput.setText(selectedUser.getNome());
                NameLoanInput.setEditable(false);
                SearchButton1.setIcon(styles.loadIcon("/images/pencil.png"));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum usuário encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAllBooks() {
        List<BookModel> livros = bookController.getAllBooks();

        if (livros != null && !livros.isEmpty()) {
            LoanTablesBook loanTablesBook = new LoanTablesBook(this, livros);
            selectedBook = loanTablesBook.getSelectedBook();

            if (selectedBook != null) {
                BookLoanInput.setText(selectedBook.getTitulo());
                BookLoanInput.setEditable(false);
                SearchButton2.setIcon(styles.loadIcon("/images/pencil.png"));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum livro encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
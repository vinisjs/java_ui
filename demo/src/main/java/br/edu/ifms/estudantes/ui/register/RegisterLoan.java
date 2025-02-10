package br.edu.ifms.estudantes.ui.register;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.controller.BorrowController;
import br.edu.ifms.estudantes.controller.UserController;
import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.model.BorrowModel;
import br.edu.ifms.estudantes.model.CartModel;
import br.edu.ifms.estudantes.model.UserModel;
import br.edu.ifms.estudantes.ui.menu.BagMenu;
import br.edu.ifms.estudantes.ui.menu.LoanTablesBook;
import br.edu.ifms.estudantes.ui.menu.LoanTablesUsers;
import br.edu.ifms.estudantes.util.Styles;
import br.edu.ifms.estudantes.util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel CampoTitulo;
    private JButton BagButton;
    private JPanel CampoQtd;
    private JButton PlusButton;
    private JButton LessButton;
    private JTextField QtdInput;
    private JTextField TitleInput;
    private JTextField AuthorInput;
    private JTextField ExempleInput;
    private JPanel CampoAutor;
    private JPanel CampoData;
    private JPanel CampoExemplares;

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
        this.setSize(600, 500);
        this.setLocationRelativeTo(parentLoan);

        styles.styleTextField(NameLoanInput);
        styles.styleTextField(BookLoanInput);
        styles.styleTextField(QtdInput);
        styles.styleTextField(TitleInput);
        styles.styleTextField(AuthorInput);
        styles.styleTextField(DateLoanInput);
        styles.styleTextField(ExempleInput);

        styles.alignFieldsLoan(CampoNome, "Nome:", NameLoanInput, SearchButton1);
        styles.alignFieldsLoan(CampoLivro, "livro:", BookLoanInput, SearchButton2);
        styles.alignFields(CampoTitulo, "Titulo:", TitleInput);
        styles.alignFields(CampoAutor, "Autor:", AuthorInput);
        styles.alignFields(CampoData, "Data publicação:", DateLoanInput);
        styles.alignFields(CampoExemplares, "Exemplares:", ExempleInput);
        styles.alignFieldsQtd(CampoQtd, "Quantidade:", LessButton, QtdInput, PlusButton);

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
        value += Integer.parseInt(QtdInput.getText());

        utils.configureSearchInput(NameLoanInput, "Busque por id ou nome do usuário");
        utils.configureSearchInput(BookLoanInput, "Busque por id ou nome do livro");

        TitleInput.setEditable(false);
        AuthorInput.setEditable(false);
        DateLoanInput.setEditable(false);
        ExempleInput.setEditable(false);

        BagButton.addActionListener(e -> showCart());
        cancelarButton.addActionListener(e -> dispose());
        salvarButton.addActionListener(e -> addToCart());

        SearchButton1.addActionListener(e -> showAllUsers());
        NameLoanInput.addActionListener(e -> showAllUsers());
        SearchButton2.addActionListener(e -> showAllBooks());
        BookLoanInput.addActionListener(e -> showAllBooks());

        PlusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int totalBooksInCart = cartModel.getTotalBooks();
                    int remainingBooksAllowed = 5 - totalBooksInCart;

                    if (remainingBooksAllowed <= 0) {
                        JOptionPane.showMessageDialog(parentLoan, "Você já atingiu o limite de 5 livros no total.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    value += 1;

                    if (value <= remainingBooksAllowed) {
                        QtdInput.setText(String.valueOf(value));
                    } else {
                        JOptionPane.showMessageDialog(parentLoan, "Você só pode adicionar mais " + remainingBooksAllowed + " livro(s).", "Aviso", JOptionPane.WARNING_MESSAGE);
                        value = remainingBooksAllowed;
                        QtdInput.setText(String.valueOf(value));
                    }
                } catch (NumberFormatException ex) {
                    QtdInput.setText("1");
                    value = 1;
                }
            }
        });

        LessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (value > 1) { // Garante que o valor não seja menor que 1
                    value -= 1;
                    QtdInput.setText(String.valueOf(value));
                }
            }
        });

        this.setVisible(true);
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
            JOptionPane.showMessageDialog(this, "Livro adicionado ao carrinho: " + selectedBook.getTitulo() + ", Quantidade: " + quantity, "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            BookLoanInput.setText("");
            BookLoanInput.setEditable(true);
            SearchButton2.setIcon(styles.loadIcon("/images/search.png"));
            selectedBook = null;
            QtdInput.setText("1");
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum livro selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showCart() {
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(this, "Nenhum usuário selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new BagMenu(this, cartModel, selectedUser);
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

                TitleInput.setText(selectedBook.getTitulo());
                AuthorInput.setText(selectedBook.getAutor());
                DateLoanInput.setText(selectedBook.getData_publicacao());
                ExempleInput.setText(String.valueOf(selectedBook.getQuantidade()));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum livro encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

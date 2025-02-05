package br.edu.ifms.estudantes.ui.register;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.controller.UserController;
import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.model.UserModel;
import br.edu.ifms.estudantes.util.Styles;
import br.edu.ifms.estudantes.util.Utils;

import javax.swing.*;
import java.util.List;

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

    public Styles styles = new Styles();
    public Utils utils = new Utils();

    public UserController userController = new UserController();
    public BookController bookController = new BookController();

    public RegisterLoan(JFrame parentLoan) {
        super(parentLoan, "Cadastro de Emprestimos", true);
        this.setContentPane(Screen3);
        this.setSize(600, 450);
        this.setLocationRelativeTo(parentLoan);

        styles.styleTextField(NameLoanInput);
        styles.styleTextField(BookLoanInput);
        styles.styleTextField(DateLoanInput);

        styles.alignFieldsLoan(CampoNome, "Nome:", NameLoanInput, SearchButton1);
        styles.alignFieldsLoan(CampoLivro, "livro:", BookLoanInput, SearchButton2);
        styles.alignFields(CampoData, "Data devolução:", DateLoanInput);

        SearchButton1.setIcon(styles.loadIcon("/images/search.png"));
        SearchButton2.setIcon(styles.loadIcon("/images/search.png"));

        styles.styleButton(SearchButton1);
        styles.styleButton(SearchButton2);
        styles.styleButtonMenu(salvarButton);
        styles.styleButton(cancelarButton);

        utils.maskDate(DateLoanInput);

        utils.configureSearchInput(NameLoanInput, "Busque por id ou nome do usuário");
        utils.configureSearchInput(BookLoanInput, "Busque por id ou nome do livro");

        cancelarButton.addActionListener(e -> dispose());

        salvarButton.addActionListener(e -> utils.validationDate(DateLoanInput));

        SearchButton1.addActionListener(e -> searchUser());
        SearchButton2.addActionListener(e -> searchBook());

        this.setVisible(true);
    }

    private void searchUser() {

        String value = NameLoanInput.getText().trim();
        UserController controller = new UserController();
        UserModel resultado;

        try {
            int id = Integer.parseInt(value);
            resultado = controller.getUser(id);
        } catch (NumberFormatException e) {
            resultado = controller.getUser(value);
        }

        if (resultado != null) {
            UserModel finalResultado = resultado;
         //   SwingUtilities.invokeLater(() -> new ResultsUsers(SearchUser, finalResultado).setVisible(true));
          //  displayBookDetails(resultado);
        } else {
            JOptionPane.showMessageDialog(this, "Item não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        String searchTerm = NameLoanInput.getText().trim();
        if (searchTerm.isEmpty() || searchTerm.equals("Busque por id ou nome do usuário")) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um ID ou nome para buscar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Iniciando busca de usuário com o termo: " + searchTerm);

        try {
            List<UserModel> users = (List<UserModel>) userController.getUser(searchTerm);
            if (users.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                System.out.println("Nenhum usuário encontrado para o termo: " + searchTerm);
            } else {
                NameLoanInput.setText(users.get(0).getNome());
                System.out.println("Usuário encontrado: " + users.get(0));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
    }

    private void searchBook() {
        String searchTerm = BookLoanInput.getText().trim();
        if (searchTerm.isEmpty() || searchTerm.equals("Busque por id ou nome do livro")) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um ID ou nome para buscar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Iniciando busca de livro com o termo: " + searchTerm);

        try {
            List <BookModel> books = (List<BookModel>) bookController.getBook(searchTerm);
            if (books.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Livro não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                System.out.println("Nenhum livro encontrado para o termo: " + searchTerm);
            } else {
                BookLoanInput.setText(books.get(0).getTitulo());
                System.out.println("Livro encontrado: " + books.get(0));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
    }
}
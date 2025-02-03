package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.util.Styles;
import br.edu.ifms.estudantes.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoanFormView extends JDialog{
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

    public LoanFormView(JFrame parentLoan) {
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

        System.out.println(DateLoanInput.getText());

        utils.maskDate(DateLoanInput);

        configureSearchInputName();
        configureSearchInputBook();


        cancelarButton.addActionListener(e -> dispose());

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(DateLoanInput.getText());
                utils.validationDate(DateLoanInput);
            }
        });

        this.setVisible(true);
    }

    private void configureSearchInputName() {
        NameLoanInput.setText("Busque por id ou nome do usuário");
        NameLoanInput.setForeground(Color.GRAY);
        NameLoanInput.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (NameLoanInput.getText().equals("Busque por id ou nome do usuário")) {
                    NameLoanInput.setText("");
                    NameLoanInput.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (NameLoanInput.getText().equals("")) {
                    NameLoanInput.setText("Busque por id ou nome do usuário");
                    NameLoanInput.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void configureSearchInputBook() {
        BookLoanInput.setText("Busque por id ou nome do livro");
        BookLoanInput.setForeground(Color.GRAY);
        BookLoanInput.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (BookLoanInput.getText().equals("Busque por id ou nome do livro")) {
                    BookLoanInput.setText("");
                    BookLoanInput.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (BookLoanInput.getText().equals("")) {
                    BookLoanInput.setText("Busque por id ou nome do livro");
                    BookLoanInput.setForeground(Color.GRAY);
                }
            }
        });
    }
}

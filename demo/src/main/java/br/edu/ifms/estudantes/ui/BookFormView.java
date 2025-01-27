package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.controller.BookController;
import br.edu.ifms.estudantes.model.BookModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class BookFormView extends JDialog{
    private JPanel Screen1;
    private JTextField textTheme;
    private JTextField textTitle;
    private JPanel CampoTema;
    private JPanel CampoTitulo;
    private JPanel CampoAutor;
    private JTextField textAuthor;
    private JPanel CampoISBN;
    private JTextField textIsbn;
    private JPanel CampoData;
    private JTextField textDate;
    private JPanel CampoExemplares;
    private JTextField textTotal;
    private JButton cadastrarButton;
    private JButton cancelarButton;
    private JFormattedTextField formattedTextDate;

    public BookFormView(JFrame parent) {
        super(parent, "Cadastro de Livros", true);
        this.setContentPane(Screen1);
        this.setSize(650, 450);
        this.setLocationRelativeTo(parent);

        styleTextField(textTheme);
        styleTextField(textTitle);
        styleTextField(textAuthor);
        styleTextField(textIsbn);
        styleTextField(formattedTextDate);
        styleTextField(textTotal);

        alignFields(CampoTema, "Tema:", textTheme);
        alignFields(CampoTitulo, "Título:", textTitle);
        alignFields(CampoAutor, "Autor:", textAuthor);
        alignFields(CampoISBN, "ISBN:", textIsbn);
        alignFields(CampoData, "Data:", formattedTextDate);
        alignFields(CampoExemplares, "Exemplares:", textTotal);

        styleButton(cadastrarButton);
        styleButton(cancelarButton);

        MaskFormatter mascaratelefone = null;

            try {
                mascaratelefone = new MaskFormatter("##/##/####");
                mascaratelefone.setPlaceholderCharacter('_');

                formattedTextDate.setFormatterFactory(new DefaultFormatterFactory(mascaratelefone));

            } catch (ParseException ex) {
                System.out.println("Deu B.O");
            }

        BookModel book = new BookModel();
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                book.setTitulo(textTitle.getText());
                book.setAutor(textAuthor.getText());
                book.setTema(textTheme.getText());
                book.setISBN(textIsbn.getText());
                book.setData_publicacao(formattedTextDate.getText());
                book.setQuantidade(Integer.parseInt(textTotal.getText()));


                new BookController().controller(book);
                BookModel re = new BookController().getBook(2);
                System.out.println(re.getTitulo());

            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.setVisible(true);
    }

    private void styleTextField(JTextField textField) {
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    private void alignFields(JPanel panel, String labelText, JComponent component) {
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(100, 30));
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, BorderLayout.WEST);
        panel.add(component, BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(217, 217, 217));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setUI(new BasicButtonUI() {

            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                JButton b = (JButton) c;
                g2.setColor(b.getBackground());
                g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 20, 20);

                super.paint(g, c);
            }
        });
    }
}

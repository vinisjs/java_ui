package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.model.UserModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowAllData extends JFrame {
    private JTable table;
    private JButton buttonOk;
    private JPanel Boby;
    private JScrollPane Jscroll;
    private Styles styles = new Styles();

    public ShowAllData() {
        setTitle("Exibição de Dados");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        table = new JTable();
        table.setEnabled(false);
        styles.styleTable(table);

        buttonOk = new JButton("Ok");
        styles.styleButton(buttonOk);
        buttonOk.addActionListener(e -> dispose());

        Boby = new JPanel(new BorderLayout());
        Jscroll = new JScrollPane(table);
        Boby.add(Jscroll, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(buttonOk);
        Boby.add(buttonPanel, BorderLayout.SOUTH);

        add(Boby);
    }

    public void showAllUsers(List<UserModel> users) {
        setTitle("Todos os Usuários");

        String[] columnNames = {"Id", "Nome", "Sexo", "Número", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (UserModel user : users) {
            tableModel.addRow(new Object[]{
                    user.getNumberId(),
                    user.getNome(),
                    user.getSexo(),
                    user.getNumberPhone(),
                    user.getEmail()
            });
        }

        loadDataToTable(tableModel);
        setVisible(true);
    }

    private void loadDataToTable(DefaultTableModel tableModel) {
        table.setModel(tableModel);
        table.repaint();
    }

    public void showAllBooks(List<BookModel> livros) {
        setTitle("Todos os Livros");

        String[] columnNames = {"ID", "Título", "Autor", "ISBN", "Quantidade", "Tema", "Data de Publicação"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (BookModel livro : livros) {
            tableModel.addRow(new Object[]{
                    livro.getNumberId(),
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getISBN(),
                    livro.getQuantidade(),
                    livro.getTema(),
                    livro.getData_publicacao()
            });
        }

        loadDataToTable(tableModel);
        setVisible(true);
    }
}

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

    public void showAllUsers(List<UserModel> user) {
        String[] columnNames = {"Id", "Nome", "Sexo", "Número", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (UserModel users : user) {
            Object[] rowData = {
                    users.getNumberId(),
                    users.getNome(),
                    users.getSexo(),
                    users.getNumberPhone(),
                    users.getEmail()
            };
            tableModel.addRow(rowData);
        }

        JFrame frame = new JFrame("Todos os Livros");
        JTable table = new JTable(tableModel);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        JButton buttonOk = new JButton("Ok");
        buttonOk.addActionListener(e -> frame.dispose());

        Styles styles = new Styles();
        styles.styleTable(table);
        styles.styleButton(buttonOk);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(buttonOk);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }


    public void showAllBooks(List<BookModel> livros) {
        String[] columnNames = {"ID", "Título", "Autor", "ISBN", "Quantidade", "Tema", "Data de Publicação"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (BookModel livro : livros) {
            Object[] rowData = {
                    livro.getNumberId(),
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getISBN(),
                    livro.getQuantidade(),
                    livro.getTema(),
                    livro.getData_publicacao()
            };
            tableModel.addRow(rowData);
        }

        JFrame frame = new JFrame("Todos os Livros");
        JTable table = new JTable(tableModel);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        JButton buttonOk = new JButton("Ok");
        buttonOk.addActionListener(e -> frame.dispose());

        Styles styles = new Styles();
        styles.styleTable(table);
        styles.styleButton(buttonOk);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(buttonOk);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}

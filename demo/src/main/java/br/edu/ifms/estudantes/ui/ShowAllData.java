package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.model.BookModel;

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

    public ShowAllData(BookModel book) {
        setTitle("Detalhes do Livro");
        setContentPane(Boby);
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID","Título", "Autor", "ISBN", "Quantidade", "Tema", "Data de Publicação"};
        DefaultTableModel tableModel = getDefaultTableModel(book, columnNames);

        table.setModel(tableModel);

        Jscroll.setViewportView(table);

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(buttonOk);
        Boby.add(buttonPanel, BorderLayout.SOUTH);
    }

    private static DefaultTableModel getDefaultTableModel(BookModel book, String[] columnNames) {
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        if (book != null) {
            Object[] rowData = {
                    book.getTitulo(),
                    book.getAutor(),
                    book.getISBN(),
                    book.getQuantidade(),
                    book.getTema(),
                    book.getData_publicacao()
            };
            tableModel.addRow(rowData);
        } else {
            Object[] rowData = {"Nenhum dado encontrado", "", "", "", "", ""};
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    public static void showAllBooks(List<BookModel> livros) {

        String[] columnNames = {"ID","Título", "Autor", "ISBN", "Quantidade", "Tema", "Data de Publicação"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

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
        JScrollPane scrollPane = new JScrollPane(table);

        JButton buttonOk = new JButton("Ok");
        buttonOk.addActionListener(e -> frame.dispose());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
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

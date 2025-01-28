package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.model.BookModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        this.setSize(600, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        new Styles().styleButton(buttonOk);

        // Configuração da tabela para exibir os dados do livro
        String[] columnNames = {"Título", "Autor", "ISBN", "Quantidade", "Tema", "Data de Publicação"};
        DefaultTableModel tableModel = getDefaultTableModel(book, columnNames);

        table.setModel(tableModel);

        // Adiciona ação ao botão "Ok"
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    private static DefaultTableModel getDefaultTableModel(BookModel book, String[] columnNames) {
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        if (book != null) {
            // Adiciona os dados do livro à tabela
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
            // Caso nenhum livro seja encontrado, exibe uma linha indicando isso
            Object[] rowData = {"Nenhum dado encontrado", "", "", "", "", ""};
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    public static void showAllBooks(List<BookModel> livros) {
        // Cria a tabela e o modelo de dados para todos os livros
        String[] columnNames = {"Título", "Autor", "ISBN", "Quantidade", "Tema", "Data de Publicação"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Adiciona os dados de todos os livros
        for (BookModel livro : livros) {
            Object[] rowData = {
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getISBN(),
                    livro.getQuantidade(),
                    livro.getTema(),
                    livro.getData_publicacao()
            };
            tableModel.addRow(rowData);
        }

        // Cria a janela para exibir os livros
        JFrame frame = new JFrame("Todos os Livros");
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


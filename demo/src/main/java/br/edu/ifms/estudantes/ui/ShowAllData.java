package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.model.BookModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                dispose(); // Fecha a janela ao clicar em "Ok"
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
}

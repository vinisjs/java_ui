package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.model.BookModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowAllData extends JFrame {
    private JTable table;       // Vinculado pelo editor GUI
    private JButton buttonOk;   // Vinculado pelo editor GUI
    private JPanel Boby;        // Vinculado pelo editor GUI
    private JScrollPane Jscroll; // Vinculado pelo editor GUI

    public ShowAllData(BookModel book) {
        setTitle("Detalhes do Livro");
        setContentPane(Boby); // Usa o painel gerado pelo editor GUI
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuração da tabela
        String[] columnNames = {"Título", "Autor", "ISBN", "Quantidade", "Tema", "Data de Publicação"};
        DefaultTableModel tableModel = getDefaultTableModel(book, columnNames);

        // Define o modelo da tabela existente
        table.setModel(tableModel);

        // Garante que o JScrollPane seja usado para exibir a tabela
        Jscroll.setViewportView(table);

        // Adiciona ação ao botão "Ok"
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
            }
        });

        // Garante que o botão "Ok" fique alinhado à direita
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Cria painel com alinhamento à direita
        buttonPanel.add(buttonOk); // Adiciona o botão ao painel
        Boby.add(buttonPanel, BorderLayout.SOUTH); // Adiciona esse painel no canto inferior do painel principal
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
        // Colunas da tabela
        String[] columnNames = {"Título", "Autor", "ISBN", "Quantidade", "Tema", "Data de Publicação"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Adiciona os livros ao modelo da tabela
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

        // Cria a janela
        JFrame frame = new JFrame("Todos os Livros");
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Botão "Ok" para fechar a janela
        JButton buttonOk = new JButton("Ok");
        buttonOk.addActionListener(e -> frame.dispose());

        // Configuração do painel de layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Usa o layout padrão
        panel.add(scrollPane, BorderLayout.CENTER); // Adiciona a tabela na parte central

        // Cria um painel só para o botão
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Alinha o botão à direita
        buttonPanel.add(buttonOk); // Adiciona o botão no painel
        panel.add(buttonPanel, BorderLayout.SOUTH); // Coloca o painel do botão no fundo

        // Adiciona o painel ao frame
        frame.add(panel);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}

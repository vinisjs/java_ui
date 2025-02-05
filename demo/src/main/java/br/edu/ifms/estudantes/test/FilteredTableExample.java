package br.edu.ifms.estudantes.test;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FilteredTableExample extends JFrame {
    private JTable table;
    private TableRowSorter<TableModel> sorter;
    private JTextField filterField;

    public FilteredTableExample() {
        setTitle("Tabela com Filtragem");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Dados da tabela
        String[] columnNames = {"Nome", "Idade", "Cidade"};
        Object[][] data = {
                {"Alice", 23, "São Paulo"},
                {"Bob", 30, "Rio de Janeiro"},
                {"Charlie", 25, "Belo Horizonte"},
                {"David", 28, "Curitiba"},
                {"Eva", 22, "Porto Alegre"}
        };

        // Criando a tabela
        table = new JTable(data, columnNames);
        sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        // Adicionando a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Campo de texto para filtragem
        filterField = new JTextField();
        filterField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = filterField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        // Adicionando o campo de texto ao topo da janela
        add(filterField, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FilteredTableExample example = new FilteredTableExample();
            example.setVisible(true);
        });
    }
}
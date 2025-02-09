package br.edu.ifms.estudantes.test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BibliotecaUI extends JFrame {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;
    private Map<Integer, Integer> quantidadePorLivro = new HashMap<>(); // Armazena a quantidade de cada livro pelo índice da tabela
    private int totalLivros = 0; // Contador de livros total no carrinho

    public BibliotecaUI() {
        frame = new JFrame("Sistema de Biblioteca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Criar a tabela
        String[] colunas = {"ID", "Título", "Autor", "Ano", "Quantidade", "Ações"};
        tableModel = new DefaultTableModel(colunas, 0);
        table = new JTable(tableModel);
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Rótulo do total de livros
        totalLabel = new JLabel("Total de Livros no Carrinho: 0");
        frame.add(totalLabel, BorderLayout.SOUTH);

        // Adicionar alguns livros à tabela (exemplo)
        adicionarLivro(1, "Livro A", "Autor A", 2020);
        adicionarLivro(2, "Livro B", "Autor B", 2018);
        adicionarLivro(3, "Livro C", "Autor C", 2022);

        frame.setVisible(true);
    }

    private void adicionarLivro(int id, String titulo, String autor, int ano) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnMenos = new JButton("-");
        JLabel lblQuantidade = new JLabel("0");
        JButton btnMais = new JButton("+");

        quantidadePorLivro.put(id, 0); // Inicializa a quantidade desse livro

        // Evento do botão "+"
        btnMais.addActionListener(e -> {
            if (totalLivros < 5) {
                int quantidadeAtual = quantidadePorLivro.get(id);
                quantidadeAtual++;
                quantidadePorLivro.put(id, quantidadeAtual);
                lblQuantidade.setText(String.valueOf(quantidadeAtual));

                totalLivros++;
                totalLabel.setText("Total de Livros no Carrinho: " + totalLivros);
            } else {
                JOptionPane.showMessageDialog(frame, "Limite máximo de 5 livros atingido!");
            }
        });

        // Evento do botão "-"
        btnMenos.addActionListener(e -> {
            int quantidadeAtual = quantidadePorLivro.get(id);
            if (quantidadeAtual > 0) {
                quantidadeAtual--;
                quantidadePorLivro.put(id, quantidadeAtual);
                lblQuantidade.setText(String.valueOf(quantidadeAtual));

                totalLivros--;
                totalLabel.setText("Total de Livros no Carrinho: " + totalLivros);
            }
        });

        panel.add(btnMenos);
        panel.add(lblQuantidade);
        panel.add(btnMais);

        tableModel.addRow(new Object[]{id, titulo, autor, ano, lblQuantidade, panel});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BibliotecaUI::new);
    }
}

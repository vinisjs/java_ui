package br.edu.ifms.estudantes.ui.menu;

import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.ui.register.RegisterLoan;
import br.edu.ifms.estudantes.util.Styles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoanTablesBook extends JDialog {
    private JTable tableLoan;
    private JButton buscarButton;
    private JPanel TableLoanScreen;
    private JScrollPane scrollPane;
    private Styles styles = new Styles();
    private BookModel selectedBook = null;

    public LoanTablesBook(RegisterLoan parentBook, List<BookModel> livros) {
        super(parentBook, "Selecionar Livro", true);
            setSize(600, 450);
            setLocationRelativeTo(parentBook);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            styles.styleTable(tableLoan);
            scrollPane = new JScrollPane(tableLoan);

            styles.styleButton(buscarButton);
            buscarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buscarLivro();
                }
            });

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(buscarButton);

            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            loadBookData(livros);
            setVisible(true);
        }

        private void loadBookData(List<BookModel> livros) {
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

            tableLoan.setModel(tableModel);
        }

        private void buscarLivro() {
            int selectedRow = tableLoan.getSelectedRow();
            if (selectedRow != -1) {
                selectedBook = new BookModel();
                selectedBook.setNumberId((int) tableLoan.getValueAt(selectedRow, 0));
                selectedBook.setTitulo((String) tableLoan.getValueAt(selectedRow, 1));
                selectedBook.setAutor((String) tableLoan.getValueAt(selectedRow, 2));
                selectedBook.setISBN((String) tableLoan.getValueAt(selectedRow, 3));
                selectedBook.setQuantidade((int) tableLoan.getValueAt(selectedRow, 4));
                selectedBook.setTema((String) tableLoan.getValueAt(selectedRow, 5));
                selectedBook.setData_publicacao((String) tableLoan.getValueAt(selectedRow, 6));

                JOptionPane.showMessageDialog(this, "Livro selecionado: " + selectedBook.getTitulo());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum livro selecionado!");
            }
        }

        public BookModel getSelectedBook() {
            return selectedBook;
    }
}


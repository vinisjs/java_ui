package br.edu.ifms.estudantes.ui.menu;

import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.model.CartModel;
import br.edu.ifms.estudantes.model.UserModel;
import br.edu.ifms.estudantes.ui.register.RegisterLoan;
import br.edu.ifms.estudantes.util.Styles;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class BagMenu extends JDialog {
    private JPanel BagScreen;
    private JLabel UserLabel;
    private JLabel DateLabel;
    private JTable tableBag;
    private JLabel DevolutionLabel;
    private JButton cancelarButton;
    private JButton finalizarButton;
    private JPanel DevolutionField;
    private JPanel UserField;
    private JLabel totalLabel;
    private JPanel TotalField;
    private Styles styles = new Styles();

    public BagMenu(RegisterLoan parentBag, CartModel cartModel, UserModel selectedUser) {
        super(parentBag, "Sacola", true);
        setContentPane(BagScreen);
        this.setSize(800, 500);
        setLocationRelativeTo(parentBag);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableBag.setRowSelectionAllowed(false);
        tableBag.setFocusable(false);
        tableBag.setRowHeight(40);

        styles.styleButton(cancelarButton);
        styles.styleButton(finalizarButton);
        styles.styleTable(tableBag);

        UserField.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        DevolutionField.setBorder(new MatteBorder(1, 0, 1, 0, Color.BLACK));

        if (selectedUser != null) {
            UserLabel.setText(selectedUser.getNome());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        DateLabel.setText(dateFormat.format(currentDate));

        setupTable(cartModel);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        Date returnDate = calendar.getTime();
        DevolutionLabel.setText(dateFormat.format(returnDate));
        updateTotalLabel(cartModel);

        cancelarButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void setupTable(CartModel cartModel) {
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "Livro", "Quantidade"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        for (Map.Entry<BookModel, Integer> entry : cartModel.getBooks().entrySet()) {
            BookModel book = entry.getKey();
            int quantity = entry.getValue();
            tableModel.addRow(new Object[]{book.getNumberId(), book.getTitulo(), quantity});
        }

        tableBag.setModel(tableModel);
        tableBag.getColumnModel().getColumn(2).setCellEditor(new QuantityEditor(cartModel, this));
        tableBag.setRowHeight(40);
    }

    private static class QuantityEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel panel;
        private JTextField qtdInput;
        private JButton lessButton;
        private JButton plusButton;
        private int currentQuantity;
        private CartModel cartModel;
        private BookModel currentBook;
        private BagMenu bagMenu;
        private Styles styles = new Styles();

        public QuantityEditor(CartModel cartModel, BagMenu bagMenu) {
            this.cartModel = cartModel;
            this.bagMenu = bagMenu;
            panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            qtdInput = new JTextField(2);
            qtdInput.setHorizontalAlignment(JTextField.CENTER);
            qtdInput.setEditable(false);
            qtdInput.setFont(new Font("Arial", Font.PLAIN, 14));

            lessButton = new JButton();
            plusButton = new JButton();

            styles.styleButton(lessButton);
            styles.styleButton(plusButton);
            styles.styleTextField(qtdInput);

            lessButton.setIcon(styles.loadIcon("/images/less.png"));
            plusButton.setIcon(styles.loadIcon("/images/plus.png"));

            lessButton.setPreferredSize(new Dimension(40, 30));
            plusButton.setPreferredSize(new Dimension(40, 30));
            qtdInput.setPreferredSize(new Dimension(50, 30));

            lessButton.addActionListener(e -> updateQuantity(-1));
            plusButton.addActionListener(e -> updateQuantity(1));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0, 5, 0, 5);

            gbc.gridx = 0;
            panel.add(lessButton, gbc);
            gbc.gridx = 1;
            panel.add(qtdInput, gbc);
            gbc.gridx = 2;
            panel.add(plusButton, gbc);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            currentQuantity = (int) value;
            qtdInput.setText(String.valueOf(currentQuantity));

            int bookId = (int) table.getValueAt(row, 0);
            for (BookModel book : cartModel.getBooks().keySet()) {
                if (book.getNumberId() == bookId) {
                    currentBook = book;
                    break;
                }
            }

            lessButton.setEnabled(currentQuantity > 1);
            plusButton.setEnabled(currentQuantity < 5);

            panel.revalidate();
            panel.repaint();

            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return currentQuantity;
        }

        private void updateQuantity(int change) {
            int newQuantity = currentQuantity + change;

            int currentTotal = cartModel.getBooks().values().stream().mapToInt(Integer::intValue).sum();
            int newTotal = currentTotal - currentQuantity + newQuantity;

            if (newQuantity >= 1 && newQuantity <= 5 && newTotal <= 5) {
                currentQuantity = newQuantity;
                cartModel.updateBookQuantity(currentBook, newQuantity);
                bagMenu.updateTotalLabel(cartModel);
                int row = bagMenu.tableBag.getEditingRow();
                if (row != -1) {
                    bagMenu.tableBag.setValueAt(currentQuantity, row, 2);
                }
                fireEditingStopped();
            } else {
                JOptionPane.showMessageDialog(bagMenu, "O total de livros no carrinho não pode ultrapassar 5.", "Limite Excedido", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void updateTotalLabel(CartModel cartModel) {
        int total = cartModel.getBooks().values().stream().mapToInt(Integer::intValue).sum();
        totalLabel.setText("Total de livros: " + total);
    }
}
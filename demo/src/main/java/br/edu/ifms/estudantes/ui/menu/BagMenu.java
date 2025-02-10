package br.edu.ifms.estudantes.ui.menu;

import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.model.CartModel;
import br.edu.ifms.estudantes.model.UserModel;
import br.edu.ifms.estudantes.ui.register.RegisterLoan;
import br.edu.ifms.estudantes.util.Styles;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
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
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "Livro", "Quantidade", "Ações"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        for (Map.Entry<BookModel, Integer> entry : cartModel.getBooks().entrySet()) {
            BookModel book = entry.getKey();
            int quantity = entry.getValue();
            tableModel.addRow(new Object[]{book.getNumberId(), book.getTitulo(), quantity, ""});
        }

        tableBag.setModel(tableModel);
        ButtonEditor buttonEditor = new ButtonEditor(cartModel, this);
        tableBag.getColumnModel().getColumn(3).setCellEditor(buttonEditor);
        tableBag.getColumnModel().getColumn(3).setCellRenderer(buttonEditor);
        tableBag.setRowHeight(40);
    }

    private static class ButtonEditor extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
        private JPanel panel;
        private JButton lessButton;
        private JButton plusButton;
        private CartModel cartModel;
        private BookModel currentBook;
        private BagMenu bagMenu;
        private Styles styles = new Styles();

        public ButtonEditor(CartModel cartModel, BagMenu bagMenu) {
            this.cartModel = cartModel;
            this.bagMenu = bagMenu;
            panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            lessButton = new JButton();
            plusButton = new JButton();

            styles.styleButtonQuantity(lessButton);
            styles.styleButtonQuantity(plusButton);

            lessButton.setIcon(styles.loadIcon("/images/less.png"));
            plusButton.setIcon(styles.loadIcon("/images/plus.png"));

            lessButton.addActionListener(e -> updateQuantity(-1));
            plusButton.addActionListener(e -> updateQuantity(1));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0, 5, 0, 5);

            gbc.gridx = 0;
            panel.add(lessButton, gbc);
            gbc.gridx = 1;
            panel.add(plusButton, gbc);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            setupButtons(table, row);
            return panel;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setupButtons(table, row);
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        private void setupButtons(JTable table, int row) {
            int bookId = (int) table.getValueAt(row, 0);
            for (BookModel book : cartModel.getBooks().keySet()) {
                if (book.getNumberId() == bookId) {
                    currentBook = book;
                    break;
                }
            }

            int currentQuantity = (int) table.getValueAt(row, 2);
            lessButton.setEnabled(currentQuantity > 1);
            plusButton.setEnabled(currentQuantity < 5);
        }

        private void updateQuantity(int change) {
            int row = bagMenu.tableBag.getSelectedRow();
            int currentQuantity = (int) bagMenu.tableBag.getValueAt(row, 2);
            int newQuantity = currentQuantity + change;

            int currentTotal = cartModel.getBooks().values().stream().mapToInt(Integer::intValue).sum();
            int newTotal = currentTotal - currentQuantity + newQuantity;

            if (newQuantity >= 1 && newQuantity <= 5 && newTotal <= 5) {
                cartModel.updateBookQuantity(currentBook, newQuantity);
                bagMenu.tableBag.setValueAt(newQuantity, row, 2);
                bagMenu.updateTotalLabel(cartModel);
                fireEditingStopped();
            } else {
                JOptionPane.showMessageDialog(bagMenu, "O total de livros no carrinho não pode ultrapassar 5.", "Limite Excedido", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void updateTotalLabel(CartModel cartModel) {
        int total = cartModel.getBooks().values().stream().mapToInt(Integer::intValue).sum();

        if (total > 5) {
            JOptionPane.showMessageDialog(tableBag, "O máximo é 5", "Limite Excedido", JOptionPane.WARNING_MESSAGE);
        }
        totalLabel.setText("" + total);
    }
}

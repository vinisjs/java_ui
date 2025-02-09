package br.edu.ifms.estudantes.ui.menu;

import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.model.CartModel;
import br.edu.ifms.estudantes.model.UserModel;
import br.edu.ifms.estudantes.ui.register.RegisterLoan;
import br.edu.ifms.estudantes.util.Styles;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel TotalField;
    private JLabel totalLabel;
    private JButton LessButton;
    private JTextField QtdInput;
    private JButton PlusButton;
    private JPanel QuantityField;
    private Styles styles = new Styles();

    public BagMenu(RegisterLoan parentBag, CartModel cartModel, UserModel selectedUser) {
        super(parentBag, "Sacola", true);
        setContentPane(BagScreen);
        this.setSize(800, 500);
        setLocationRelativeTo(parentBag);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        LessButton.setIcon(styles.loadIcon("/images/less.png"));
        PlusButton.setIcon(styles.loadIcon("/images/plus.png"));

        styles.styleButtonQuantity(PlusButton);
        styles.styleButtonQuantity(LessButton);
        styles.styleButton(cancelarButton);
        styles.styleButtonMenu(finalizarButton);

        styles.styleTable(tableBag);
        styles.styleTextFieldQuantity(QtdInput);
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
        DevolutionLabel.setText("" + dateFormat.format(returnDate));
        QtdInput.setText(String.valueOf(cartModel.getTotalBooks()));

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public void setupTable(CartModel cartModel) {
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "Livro"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Map.Entry<BookModel, Integer> entry : cartModel.getBooks().entrySet()) {
            BookModel book = entry.getKey();
            tableModel.addRow(new Object[]{book.getNumberId(), book.getTitulo()});
        }
        tableBag.setModel(tableModel);
        setupQuantityControls(cartModel);
    }

    private void setupQuantityControls(CartModel cartModel) {
        int totalBooks = cartModel.getTotalBooks();
        QtdInput.setText(String.valueOf(totalBooks));

        LessButton.addActionListener(e -> {
            int currentQuantity = Integer.parseInt(QtdInput.getText());
            if (currentQuantity > 1) {
                currentQuantity--;
                cartModel.setTotalBooks();
                QtdInput.setText(String.valueOf(currentQuantity));
                updateTotalLabel(cartModel);
            }
        });

        PlusButton.addActionListener(e -> {
            int currentQuantity = Integer.parseInt(QtdInput.getText());
            if (currentQuantity < 5) {
                currentQuantity++;
                cartModel.setTotalBooks();
                QtdInput.setText(String.valueOf(currentQuantity));
                updateTotalLabel(cartModel);
            } else {
                JOptionPane.showMessageDialog(this, "Máximo de 5 livros no total.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        updateTotalLabel(cartModel);
    }

    private void updateTotalLabel(CartModel cartModel) {
        int total = cartModel.getTotalBooks();
        QtdInput.setText(String.valueOf(total));
        totalLabel.setText("Total de livros: " + total);
    }
}
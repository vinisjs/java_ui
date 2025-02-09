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
    private Styles styles = new Styles();

    public BagMenu(RegisterLoan parentBag, CartModel cartModel, UserModel selectedUser) {
        super(parentBag, "Sacola", true);
        setContentPane(BagScreen);
        this.setSize(600, 500);
        setLocationRelativeTo(parentBag);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//        LessButton.setIcon(styles.loadIcon("/images/less.png"));
//        PlusButton.setIcon(styles.loadIcon("/images/plus.png"));

        styles.styleButton(PlusButton);
        styles.styleButton(LessButton);
        styles.styleButton(cancelarButton);
        styles.styleButtonMenu(finalizarButton);

        styles.styleTable(tableBag);
        styles.styleTextField(QtdInput);
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
            int quantity = entry.getValue();
            tableModel.addRow(new Object[]{book.getNumberId(), book.getTitulo()});
        }
        tableBag.setModel(tableModel);
//        alignQuantityPanel(cartModel);
    }

//    private void alignQuantityPanel(CartModel cartModel) {
//        TotalField.removeAll();
//
//        for (Map.Entry<BookModel, Integer> entry : cartModel.getBooks().entrySet()) {
//            BookModel book = entry.getKey();
//            int quantity = entry.getValue();
//
//            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//            JTextField quantityInput = new JTextField(String.valueOf(quantity), 3);
//            quantityInput.setEditable(false);
//
//            LessButton.addActionListener(e -> {
//                int currentQuantity = Integer.parseInt(quantityInput.getText());
//                if (currentQuantity > 1) {
//                    currentQuantity--;
//                    quantityInput.setText(String.valueOf(currentQuantity));
//                    cartModel.addBook(book, currentQuantity);
//                }
//            });
//
//            PlusButton.addActionListener(e -> {
//                int currentQuantity = Integer.parseInt(quantityInput.getText());
//                if (currentQuantity < 5) {
//                    currentQuantity++;
//                    quantityInput.setText(String.valueOf(currentQuantity));
//                    cartModel.addBook(book, currentQuantity);
//                } else {
//                    JOptionPane.showMessageDialog(this, "Máximo de 5 livros por título.", "Aviso", JOptionPane.WARNING_MESSAGE);
//                }
//            });
//
//            quantityPanel.add(LessButton);
//            quantityPanel.add(quantityInput);
//            quantityPanel.add(PlusButton);
//
//            TotalField.add(quantityPanel);
//        }
//
//        TotalField.revalidate();
//        TotalField.repaint();
//    }
}
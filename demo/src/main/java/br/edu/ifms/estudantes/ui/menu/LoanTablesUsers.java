package br.edu.ifms.estudantes.ui.menu;

import br.edu.ifms.estudantes.model.UserModel;
import br.edu.ifms.estudantes.ui.register.RegisterLoan;
import br.edu.ifms.estudantes.util.Styles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoanTablesUsers extends JDialog{
    private JPanel TableLoanScreen;
    private JTable tableUser;
    private JButton buscarButton;
    private JScrollPane ScrollPane;
    private Styles styles = new Styles();
    private UserModel selectedUser = null;

    public LoanTablesUsers(RegisterLoan parentUsers, List<UserModel> users) {
        super(parentUsers, "Selecionar Usuário", true);
        setSize(600, 450);
        setLocationRelativeTo(parentUsers);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        styles.styleTable(tableUser);
        ScrollPane = new JScrollPane(tableUser);

        styles.styleButton(buscarButton);
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(buscarButton);

        setLayout(new BorderLayout());
        add(ScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadUserData(users);
        setVisible(true);
    }

    private void loadUserData(List<UserModel> users) {
        String[] columnNames = {"ID", "Nome", "Email", "Telefone"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (UserModel user : users) {
            tableModel.addRow(new Object[]{
                    user.getNumberId(),
                    user.getNome(),
                    user.getEmail(),
                    user.getNumberPhone()
            });
        }

        tableUser.setModel(tableModel);
    }

    private void buscarUsuario() {
        int selectedRow = tableUser.getSelectedRow();
        if (selectedRow != -1) {
            selectedUser = new UserModel();
            selectedUser.setNumberId((int) tableUser.getValueAt(selectedRow, 0));
            selectedUser.setNome((String) tableUser.getValueAt(selectedRow, 1));
            selectedUser.setEmail((String) tableUser.getValueAt(selectedRow, 2));
            selectedUser.setNumberPhone((String) tableUser.getValueAt(selectedRow, 3));

            JOptionPane.showMessageDialog(this, "Usuário selecionado: " + selectedUser.getNome());
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum usuário selecionado!");
        }
    }

    public UserModel getSelectedUser() {
        return selectedUser;
    }
}

package br.edu.ifms.estudantes.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.util.Objects;

public class Styles {
    public Styles(){

    }
    public void styleTextField(JTextField textField) {
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    public void styleButton(JButton button) {
        button.setBackground(new Color(217, 217, 217));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setUI(new BasicButtonUI() {
            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                AbstractButton button = (AbstractButton) c;
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setOpaque(false);
            }

            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                JButton b = (JButton) c;
                g2.setColor(b.getBackground());
                g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 20, 20);

                super.paint(g, c);
            }
        });
    }

    public void alignFields(JPanel panel, String labelText, JComponent component) {
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(100, 30));
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, BorderLayout.WEST);
        panel.add(component, BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    public void alignRadioButtonField(JPanel panel, String labelText, JComponent... components) {
        panel.setLayout(new GridBagLayout());
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(100, 30));
        label.setFont(new Font("Arial", Font.BOLD, 14));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        for (int i = 0; i < components.length; i++) {
            gbc.gridx = i + 1;
            panel.add(components[i], gbc);
        }

        panel.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    public void styleRadioButton(JRadioButton radioButton) {
        radioButton.setBackground(new Color(240, 240, 240));
        radioButton.setFont(new Font("Arial", Font.PLAIN, 14));
        radioButton.setForeground(Color.BLACK);
    }

    public void styleButtonMenu(JButton button) {
        button.setPreferredSize(new Dimension(100, 30));
        button.setMaximumSize(new Dimension(200, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(2, 133, 199));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setIconTextGap(10);

        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setUI(new BasicButtonUI() {
            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                AbstractButton button = (AbstractButton) c;
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setOpaque(false);
            }

            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                JButton b = (JButton) c;

                g2.setColor(b.getBackground());
                g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 20, 20);

                super.paint(g, c);
            }
        });
    }

    public void styleTitleLabelMenu(JLabel label){
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(new Color(50, 50, 50));
    }

    public ImageIcon loadIcon(String path) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }


//    private void configureSearchInput() {
//        SearchInput.setText("Busque por id ou nome do livro");
//        SearchInput.setForeground(Color.GRAY);
//        SearchInput.addFocusListener(new FocusAdapter() {
//            public void focusGained(FocusEvent e) {
//                if (SearchInput.getText().equals("Busque por id ou nome do livro")) {
//                    SearchInput.setText("");
//                    SearchInput.setForeground(Color.BLACK);
//                }
//            }
//
//            public void focusLost(FocusEvent e) {
//                if (SearchInput.getText().equals("")) {
//                    SearchInput.setText("Busque por id ou nome do livro");
//                    SearchInput.setForeground(Color.GRAY);
//                }
//            }
//        });
//    }
    //    private void searchBook() {
//        String value = SearchInput.getText().trim();
//        BookController controller = new BookController();
//        BookModel resultado;
//
//        try {
//            int id = Integer.parseInt(value);
//            resultado = controller.getBook(id);
//        } catch (NumberFormatException e) {
//            resultado = controller.getBook(value);
//        }
//
//        if (resultado != null) {
//            BookModel finalResultado = resultado;
//            SwingUtilities.invokeLater(() -> new ResultsForm(MenuScreen, finalResultado).setVisible(true));
//            displayBookDetails(resultado);
//        } else {
//            JOptionPane.showMessageDialog(this, "Item não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
//        }
//    }

    //    private void showAllBooks() {
//        BookController controller = new BookController();
//        List<BookModel> livros = controller.getAllBooks();
//
//        if (livros != null && !livros.isEmpty()) {
//            ShowAllData.showAllBooks(livros);
//            livros.forEach(this::displayBookDetails);
//        } else {
//            System.out.println("Nenhum livro encontrado.");
//        }
//    }
}

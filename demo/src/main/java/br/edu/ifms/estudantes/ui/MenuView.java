package br.edu.ifms.estudantes.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MenuView extends JFrame{
    private JPanel MenuScreen;
    private JPanel MenuBorder;
    private JButton livrosButton;
    private JButton usuariosButton;
    private JButton emprestimosButton;
    private JButton sairButton;
    private JPanel Content;
    private JLabel titleLabel;

    public MenuView(JPanel splashScreen) {
        setTitle("Menu de opções");
        setContentPane(MenuScreen);
        this.setSize(600, 450);
        this.setLocationRelativeTo(splashScreen);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        MenuBorder.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        MenuBorder.setLayout(new BoxLayout(MenuBorder, BoxLayout.Y_AXIS));
        MenuBorder.setBorder(new EmptyBorder(20, 20, 20, 20));

        styleTitleLabel(titleLabel);
        MenuBorder.add(titleLabel);
        MenuBorder.add(Box.createVerticalStrut(20));

        livrosButton.setIcon(loadIcon("/images/book.png"));
        usuariosButton.setIcon(loadIcon("/images/users.png"));
        emprestimosButton.setIcon(loadIcon("/images/thumbs-up.png"));
        sairButton.setIcon(loadIcon("/images/log-out.png"));

        styleButton(livrosButton);
        styleButton(usuariosButton);
        styleButton(emprestimosButton);
        styleButton(sairButton);

        addCenteredButton(livrosButton);
        addCenteredButton(usuariosButton);
        addCenteredButton(emprestimosButton);
        addCenteredButton(sairButton);

        MenuBorder.add(Box.createVerticalGlue());

        this.setVisible(true);
        livrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBookForm();
            }
        });
        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUserForm();
            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void styleTitleLabel(JLabel label){
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(new Color(50, 50, 50));
    }

    private void styleButton(JButton button) {
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

    private void addCenteredButton(JButton button) {
        MenuBorder.add(button);
        MenuBorder.add(Box.createVerticalStrut(10));
    }

    private void openBookForm() {
        new BookFormView(this);
    }

    private void openUserForm() {
        new UserFormView(this);
    }
    
    private ImageIcon loadIcon(String path) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}

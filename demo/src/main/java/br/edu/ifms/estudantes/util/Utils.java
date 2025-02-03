package br.edu.ifms.estudantes.util;

import br.edu.ifms.estudantes.ui.UserFormView;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils extends Component {
    public void maskDate(JFormattedTextField formattedTextField) {
        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            formattedTextField.setFormatterFactory(new DefaultFormatterFactory(mascaraData));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao aplicar máscara no campo de data.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void validationDate(JFormattedTextField formattedTextField) {
        try {
            if (formattedTextField.getText().trim().equals("__/__/____")) {
                System.out.println(formattedTextField.getText());
                throw new IllegalArgumentException("Por favor, preencha todos os campos corretamente.");
            }

            String dataStr = formattedTextField.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            dateFormat.setLenient(false);

            Date dataPublicacao;
            try {
                dataPublicacao = dateFormat.parse(dataStr);
                System.out.println(dataPublicacao);
            } catch (Exception e) {
                throw new IllegalArgumentException("Data inválida. Utilize uma data válida.");
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void configurePhoneMask(JFormattedTextField formattedTextFieldphone) {
        try {
            MaskFormatter phoneMask = new MaskFormatter("+## (##) ##### - ####");
            formattedTextFieldphone.setFormatterFactory(new DefaultFormatterFactory(phoneMask));
        } catch (ParseException ex) {
            System.out.println("Erro ao aplicar a máscara do telefone.");
        }
    }

    public void configureEmailValidation(JTextField textField, JLabel label) {
        textField.getDocument().addDocumentListener(new SimpleDocumentListener() {
            @Override
            public void update() {
                String email = textField.getText();
                if (!isValidEmail(email)) {
                    label.setText("E-mail inválido");
                } else {
                    label.setText("");
                }
            }
        });
    }

    public boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public abstract class SimpleDocumentListener implements javax.swing.event.DocumentListener {
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            update();
        }
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            update();
        }
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            update();
        }
        public abstract void update();
    }
}

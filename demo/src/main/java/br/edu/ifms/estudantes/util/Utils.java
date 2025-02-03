package br.edu.ifms.estudantes.util;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
}

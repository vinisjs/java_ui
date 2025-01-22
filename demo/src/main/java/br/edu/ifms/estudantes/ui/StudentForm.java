package br.edu.ifms.estudantes.ui;

import br.edu.ifms.estudantes.model.Student;
import br.edu.ifms.estudantes.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentForm {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Student Form");
    frame.setLayout(new FlowLayout());
    frame.setSize(300, 200);

    JLabel firstNameLabel = new JLabel("First Name");
    JTextField firstNameField = new JTextField(15);
    JLabel lastNameLabel = new JLabel("Last Name");
    JTextField lastNameField = new JTextField(15);
    JButton saveButton = new JButton("Save");

    frame.add(firstNameLabel);
    frame.add(firstNameField);
    frame.add(lastNameLabel);
    frame.add(lastNameField);
    frame.add(saveButton);

    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Student student = new Student(firstName, lastName);
        session.save(student);

        transaction.commit();
        JOptionPane.showMessageDialog(frame, "Student saved successfully!");

        firstNameField.setText("");
        lastNameField.setText("");
      }
    });

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}

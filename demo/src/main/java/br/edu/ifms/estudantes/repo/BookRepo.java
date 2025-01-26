package br.edu.ifms.estudantes.repo;

import br.edu.ifms.estudantes.model.BookModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookRepo extends BookModel {
    public void save(BookModel book, Session session, Transaction transaction){
        session.save(book);
        transaction.commit();
        System.out.println("Livro Salvo");
    }
}

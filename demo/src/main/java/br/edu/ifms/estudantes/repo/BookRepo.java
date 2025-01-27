package br.edu.ifms.estudantes.repo;

import br.edu.ifms.estudantes.model.BookModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

public class BookRepo {

    public void save(BookModel book, Session session, Transaction transaction) {
        session.save(book);
        transaction.commit();
        System.out.println("Livro Salvo");
    }

    public static BookModel getBook(Object param, Session session) {
        if (param instanceof Integer) {
            return session.get(BookModel.class, (Serializable) param);
        } else if (param instanceof String) {
            return session.createQuery("FROM BookModel WHERE Titulo = :titulo", BookModel.class)
                    .setParameter("titulo", param)
                    .uniqueResult();
        }
        return null;
    }
}

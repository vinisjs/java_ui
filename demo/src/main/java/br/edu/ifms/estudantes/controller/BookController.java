package br.edu.ifms.estudantes.controller;

import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.repo.BookRepo;
import br.edu.ifms.estudantes.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookController {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction transaction = session.beginTransaction();

    public void controller(BookModel book) {
        new BookRepo().save(book, session,transaction);

    }
}

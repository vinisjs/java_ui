package br.edu.ifms.estudantes.controller;

import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.repo.BookRepo;
import br.edu.ifms.estudantes.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class BookController {
    private final BookRepo bookRepo = new BookRepo();

    public void saveBook(BookModel book) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            bookRepo.SaveOrUpdate(book, session);
        }
    }
    public void saveOrUpdate(BookModel book) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            bookRepo.save(book, session);
        }
    }

    public BookModel getBook(Object param) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return bookRepo.getBook(param, session);
        }
    }

    public List<BookModel> getAllBooks(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return bookRepo.getAllBooks(session);
        }
    }
}

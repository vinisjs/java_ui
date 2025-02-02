package br.edu.ifms.estudantes.repo;

import br.edu.ifms.estudantes.model.BookModel;
import org.hibernate.Session;

import java.util.List;

public class BookRepo {

    public void SaveOrUpdate(BookModel book, Session session) {
        try {
            session.beginTransaction();
            System.out.println("Tentando Atualizar Livro");

            session.merge(book);

            session.getTransaction().commit();
            System.out.println("Livro atualizado com sucesso!");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void saveOnBook(BookModel book, Session session) {
        try {
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
            System.out.println("Livro salvo com sucesso!");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar o livro: " + e.getMessage());
        }
    }

    public List<BookModel> getAllBooks(Session session) {
        try {
            return session.createQuery("FROM BookModel", BookModel.class).list();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os livros: " + e.getMessage());
            return null;
        }
    }

    public BookModel getBook(Object param, Session session) {
        if (param == null) {
            System.out.println("Parâmetro fornecido é nulo.");
            return null;
        }

        try {
            System.out.println("Parâmetro recebido: " + param);
            if (param instanceof Integer) {
                return session.createQuery("FROM BookModel WHERE NumberId = :id", BookModel.class)
                        .setParameter("id", param)
                        .uniqueResult();
            } else if (param instanceof String) {
                return session.createQuery("FROM BookModel WHERE Titulo = :titulo", BookModel.class)
                        .setParameter("titulo", param)
                        .uniqueResult();
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar o livro: " + e.getMessage());
        }
        return null;
    }

    public void DeleteById(Object param, Session session) {
        if (param == null) {
            System.out.println("Parâmetro fornecido é nulo.");
            return;
        }

        try {
            BookModel bookToDelete = session.createQuery("FROM BookModel WHERE id = :id", BookModel.class)
                    .setParameter("id", param)
                    .uniqueResult();

            if (bookToDelete != null) {
                session.beginTransaction();
                session.delete(bookToDelete);
                session.getTransaction().commit();
                System.out.println("Livro excluído com sucesso!");
            } else {
                System.out.println("Livro não encontrado para exclusão.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao deletar o livro: " + e.getMessage());
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }

}

package br.edu.ifms.estudantes.repo;

import br.edu.ifms.estudantes.model.BookModel;
import org.hibernate.Session;

import java.util.List;

public class BookRepo {

    public void save(BookModel book, Session session) {
        try {
            session.beginTransaction(); // Iniciar transação na chamada
            session.save(book);
            session.getTransaction().commit(); // Confirmar a transação
            System.out.println("Livro salvo com sucesso!");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // Reverter a transação em caso de erro
            }
            System.err.println("Erro ao salvar o livro: " + e.getMessage());
        }
    }

    public List<BookModel> getAllBooks(Session session) {
        try {
            // Consulta para listar todos os livros usando HQL
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
            if (param instanceof Integer) {
                return session.createQuery("FROM BookModel WHERE id = :id", BookModel.class)
                        .setParameter("id", param)
                        .uniqueResult();
            } else if (param instanceof String) {
                return session.createQuery("FROM BookModel WHERE titulo = :titulo", BookModel.class)
                        .setParameter("titulo", param)
                        .uniqueResult();
            } else {
                System.out.println("Parâmetro inválido. Deve ser um Integer (ID) ou String (Título).");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar o livro: " + e.getMessage());
            return null;
        }
    }
}

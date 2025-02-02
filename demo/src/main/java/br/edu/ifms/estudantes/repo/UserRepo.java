package br.edu.ifms.estudantes.repo;

import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.model.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserRepo extends UserModel {

    public void SaveOrUpdate(UserModel user, Session session) {
        try {
            session.beginTransaction();
            System.out.println("Tentando Atualizar Usuário");

            session.merge(user);

            session.getTransaction().commit();
            System.out.println("Usuário atualizado com sucesso!");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void saveOnBook(UserModel user, Session session) {
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("Usuário salvo com sucesso!");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar o Usuário: " + e.getMessage());
        }
    }

    public List<UserModel> getAllUser(Session session) {
        try {
            return session.createQuery("FROM UserModel", UserModel.class).list();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os livros: " + e.getMessage());
            return null;
        }
    }

    public UserModel getUser(Object param, Session session) {
        if (param == null) {
            System.out.println("Parâmetro fornecido é nulo.");
            return null;
        }

        try {
            System.out.println("Parâmetro recebido: " + param);
            if (param instanceof Integer) {
                return session.createQuery("FROM UserModel WHERE NumberId = :id", UserModel.class)
                        .setParameter("id", param)
                        .uniqueResult();
            } else if (param instanceof String) {
                return session.createQuery("FROM UserModel WHERE Nome = :nome", UserModel.class)
                        .setParameter("nome", param)
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
            UserModel userToDelete = session.createQuery("FROM UserModel WHERE id = :id", UserModel.class)
                    .setParameter("id", param)
                    .uniqueResult();

            if (userToDelete != null) {
                session.beginTransaction();
                session.delete(userToDelete);
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



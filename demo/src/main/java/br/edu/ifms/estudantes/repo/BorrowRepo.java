package br.edu.ifms.estudantes.repo;

import br.edu.ifms.estudantes.model.BorrowModel;
import br.edu.ifms.estudantes.model.UserModel;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BorrowRepo {

    public void UpdateBorrow(BorrowModel borrow, Session session) {
        try {
            session.beginTransaction();
            System.out.println("Tentando Atualizar Empréstimo");

            session.merge(borrow);

            session.getTransaction().commit();



            System.out.println("Empréstimo atualizado com sucesso!");
            System.out.println("Data de devolução!" + borrow.getDataReturnPreview());
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void saveOneBorrow(BorrowModel borrow, Session session) {
        try {
            session.beginTransaction();
            session.save(borrow);
            session.getTransaction().commit();
            System.out.println("Empréstimo salvo com sucesso!");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar o Empréstimo: " + e.getMessage());
        }
    }

    public List<BorrowModel> getAllBorrow(Session session) {
        try {
            return session.createQuery("FROM BorrowModel", BorrowModel.class).list();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os Empréstimo: " + e.getMessage());
            return null;
        }
    }

    public BorrowModel getBorrow(Object param, Session session) {
        if (param == null) {
            System.out.println("Parâmetro fornecido é nulo.");
            return null;
        }

        try {
            System.out.println("Parâmetro recebido: " + param);
            if (param instanceof Integer) {
                return session.createQuery("FROM BorrowModel WHERE NumberId = :id", BorrowModel.class)
                        .setParameter("id", param)
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
            BorrowModel userToDelete = session.createQuery("FROM BorrowModel WHERE NumberId = :id", BorrowModel.class)
                    .setParameter("id", param)
                    .uniqueResult();

            if (userToDelete != null) {
                session.beginTransaction();
                session.delete(userToDelete);
                session.getTransaction().commit();
                System.out.println("Empréstimo excluído com sucesso!");
            } else {
                System.out.println("Empréstimo não encontrado para exclusão.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao deletar o Empréstimo: " + e.getMessage());
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }

}

package br.edu.ifms.estudantes.controller;

import br.edu.ifms.estudantes.model.BorrowModel;
import br.edu.ifms.estudantes.repo.BorrowRepo;
import br.edu.ifms.estudantes.util.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class BorrowController {
    BorrowRepo borrowRepo = new BorrowRepo();

    public BorrowModel Create(BorrowModel data) {
        System.out.println("Id: " + data.getId());
        System.out.println("Id Usuário: " + data.getId_user());
        System.out.println("Id Livro: " + data.getId_book());
        System.out.println("Saída: " + data.getDateOut());

        LocalDate dateOut = data.getDateOut().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate previsaoLocalDate = dateOut.plusDays(14);

        Date previsao = Date.from(previsaoLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        System.out.println("Previsão: " + previsao);
        System.out.println("Retorno: " + data.getDataReturn());

        data.setDataReturnPreview(previsao);

        return data;
    }

    public void saveOneBorrow(BorrowModel borrow) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            borrowRepo.saveOneBorrow(borrow, session);
        }
    }
    public void UpdateBorrow(BorrowModel borrow) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            borrowRepo.saveOneBorrow(borrow, session);
        }
    }

    public List<BorrowModel> getBorrow(Object param) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Certifica-se de que o retorno é uma lista, não um único objeto
            return borrowRepo.getBorrow(param, session);
        }
    }


    public List<BorrowModel> getAllBorrow(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return borrowRepo.getAllBorrow(session);
        }
    }

    public void DeleteById(Object param) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            borrowRepo.DeleteById(param, session);
        }
    }

    public void saveLoan(BorrowModel mainBorrow, List<BorrowModel> borrowItems, String transactionId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try (session) {
            session.beginTransaction();



            System.out.println("Transaction ID: " + transactionId);
            // Salva o empréstimo principal
            borrowRepo.saveOneBorrow(mainBorrow, session);

            // Atualiza as informações dos itens relacionados ao empréstimo
            for (BorrowModel item : borrowItems) {
                item.setDateOut(mainBorrow.getDateOut());
                item.setDataReturnPreview(mainBorrow.getDataReturnPreview());
                item.setId_user(mainBorrow.getId_user());
                item.setTransactionId(transactionId); // Aplica a mesma chave de transação
                borrowRepo.saveOneBorrow(item, session);
            }

            // Faz o commit da transação
            session.getTransaction().commit();
            System.out.println("Empréstimo salvo com sucesso com Transaction ID: " + transactionId);
        } catch (Exception e) {
            // Reverte a transação em caso de erro
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar o empréstimo: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar o empréstimo e itens relacionados.", e);
        }
    }



}

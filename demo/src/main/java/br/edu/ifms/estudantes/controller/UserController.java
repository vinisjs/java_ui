package br.edu.ifms.estudantes.controller;


import br.edu.ifms.estudantes.model.UserModel;
import br.edu.ifms.estudantes.repo.UserRepo;
import br.edu.ifms.estudantes.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserController {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction transaction = session.beginTransaction();

    public void controllerSave(UserModel user) {
        new UserRepo().save(user,session,transaction);;
    };
};

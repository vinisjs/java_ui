package br.edu.ifms.estudantes.controller;


import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.model.UserModel;
import br.edu.ifms.estudantes.repo.BookRepo;
import br.edu.ifms.estudantes.repo.UserRepo;
import br.edu.ifms.estudantes.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserController {

    private final UserRepo userRepo = new UserRepo();

    public void saveOneUser(UserModel user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            userRepo.saveOnBook(user, session);
        }
    }
    public void UpdateUser(UserModel user) {
        System.out.println("Deu ruim!");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            userRepo.SaveOrUpdate(user, session);
        }
    }

    public UserModel getUser(Object param) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return userRepo.getUser(param, session);
        }
    }

    public List<UserModel> getAllUser(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return userRepo.getAllUser(session);
        }
    }

    public void DeleteUserById(Object param) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            userRepo.DeleteById(param, session);
        }
    }


};

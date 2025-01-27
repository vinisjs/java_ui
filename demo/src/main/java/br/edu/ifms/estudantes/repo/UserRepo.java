package br.edu.ifms.estudantes.repo;

import br.edu.ifms.estudantes.model.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepo extends UserModel {
    public void save(UserModel user, Session session, Transaction transaction){
        session.save(user);
        transaction.commit();
        System.out.println("usuário salvo");
    }

    public UserModel getUser(int id, Session session) {
        return session.get(UserModel.class, id);
    }
}

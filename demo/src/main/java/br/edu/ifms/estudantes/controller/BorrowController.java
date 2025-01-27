package br.edu.ifms.estudantes.controller;

import br.edu.ifms.estudantes.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BorrowController {

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction transaction = session.beginTransaction();

    public void Get(){

    }

    public void Create(){

    }

    public void Update(){

    }

    public void Desative(){

    }
}

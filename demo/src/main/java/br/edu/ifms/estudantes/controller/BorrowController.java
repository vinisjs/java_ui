package br.edu.ifms.estudantes.controller;

import br.edu.ifms.estudantes.model.BorrowModel;
import br.edu.ifms.estudantes.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BorrowController {

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction transaction = session.beginTransaction();

    public void Get(){

    }

    public void Create(BorrowModel data){

        System.out.println(data.getId());
        System.out.println(data.getId_user());
        System.out.println(data.getId_book());
        System.out.println(data.getDateOut());
        System.out.println(data.getDataReturnPreview());
        System.out.println(data.getDataReturn());


    }

    public void Update(){

    }

    public void Desative(){

    }
}

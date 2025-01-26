package br.edu.ifms.estudantes.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import br.edu.ifms.estudantes.model.*;

public class HibernateUtil {
  private static final SessionFactory sessionFactory;

  static {
    try {
      sessionFactory = new Configuration().configure("hibernate.cfg.xml")
          .addAnnotatedClass(BookModel.class)
          .addAnnotatedClass(UserModel.class)
          .buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to create sessionFactory object", e);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
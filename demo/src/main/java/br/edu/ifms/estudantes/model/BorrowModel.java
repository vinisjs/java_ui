package br.edu.ifms.estudantes.model;

import java.util.Date;

public class BorrowModel {
    private int id;
    private int id_book;
    private int id_user;
    private Date dateOut;
    private Date dataReturn;
    private Date LastUpdate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}

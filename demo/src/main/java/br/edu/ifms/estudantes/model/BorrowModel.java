package br.edu.ifms.estudantes.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "borrows")
public class BorrowModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column()
    private int id_book;

    @Column()
    private int id_user;

    @Column()
    private Date dateOut;

    @Column()
    private Date dataReturnPreview;

    @Column()
    private Date dataReturn;

    @Column()
    private Integer Qnt;

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    // Getters e Setters
    public Integer getQnt() {
        return Qnt;
    }

    public void setQnt(Integer Qnt) {
        this.Qnt = Qnt;
    }

    public Date getDataReturnPreview() {
        return dataReturnPreview;
    }

    public void setDataReturnPreview(Date dataReturnPreview) {
        this.dataReturnPreview = dataReturnPreview;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public Date getDataReturn() {
        return dataReturn;
    }

    public void setDataReturn(Date dataReturn) {
        this.dataReturn = dataReturn;
    }

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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}

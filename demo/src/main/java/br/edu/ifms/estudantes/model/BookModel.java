package br.edu.ifms.estudantes.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
public class BookModel {

    @Column()
    private String Titulo;
    @Column()
    private String Tema;
    @Column()
    private String Autor;
    @Column()
    private String ISBN;
    @Column()
    private String data_publicacao;
    @Column()
    private int Quantidade;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int NumberId;

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getTema() {
        return Tema;
    }

    public void setTema(String tema) {
        Tema = tema;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getData_publicacao() {
        return data_publicacao;
    }

    public void setData_publicacao(String data_publicacao) {
        this.data_publicacao = data_publicacao;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }

    public int getNumberId() {
        return NumberId;
    }

    public void setNumberId(int numberId) {
        NumberId = numberId;
    }
}

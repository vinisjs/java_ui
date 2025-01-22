package br.edu.ifms.estudantes.model;

import java.util.Date;

public class BookModel {
    private String Título;
    private String Tema;
    private String Autor;
    private String ISBN;
    private Date data_publicacao;
    private int Quantidade;
    private int NumberId;

    public String getTítulo() {
        return Título;
    }

    public void setTítulo(String título) {
        Título = título;
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

    public Date getData_publicacao() {
        return data_publicacao;
    }

    public void setData_publicacao(Date data_publicacao) {
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

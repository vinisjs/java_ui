package br.edu.ifms.estudantes.model;

import java.util.HashMap;
import java.util.Map;

public class CartModel {
    private Map<BookModel, Integer> books; // Mapa para armazenar livro e quantidade

    public CartModel() {
        this.books = new HashMap<>();
    }

    // Adiciona um livro ao carrinho com uma quantidade específica
    public void addBook(BookModel book, int quantity) {
        if (books.containsKey(book)) {
            books.put(book, books.get(book) + quantity);
        } else {
            books.put(book, quantity);
        }
    }


    // Remove um livro do carrinho
    public void removeBook(BookModel book) {
        books.remove(book);
//        updateSaveButton();
    }

    // Retorna o mapa de livros e quantidades
    public Map<BookModel, Integer> getBooks() {
        return books;
    }
    // Limpa o carrinho
    public void clearCart() {
        books.clear();
    }

    // Retorna o total de livros no carrinho
    public int getTotalBooks() {
        int total = 0;
        for (Map.Entry<BookModel, Integer> entry : books.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }

}
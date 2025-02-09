package br.edu.ifms.estudantes.model;

import java.util.HashMap;
import java.util.Map;

public class CartModel {
    private Map<BookModel, Integer> books;
    private int totalBooks;

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

    public void removeBook(BookModel book) {
        books.remove(book);
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
        return totalBooks;
    }

    public void setTotalBooks() {
        totalBooks = 0;
        for (int quantity : books.values()) {
            totalBooks += quantity;
        }
        // Limite de 5 livros por título
        for (Map.Entry<BookModel, Integer> entry : books.entrySet()) {
            if (entry.getValue() > 5) {
                books.put(entry.getKey(), 5); // Limita a quantidade a 5
            }
        }
    }

}
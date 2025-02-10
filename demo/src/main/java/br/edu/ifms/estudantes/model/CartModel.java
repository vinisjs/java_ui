package br.edu.ifms.estudantes.model;

import java.util.HashMap;
import java.util.Map;

public class CartModel {
    private Map<BookModel, Integer> books;

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
        return books.values().stream().mapToInt(Integer::intValue).sum();
    }

    // Atualiza a quantidade de um livro no carrinho
    public void updateBookQuantity(BookModel book, int newQuantity) {
        if (books.containsKey(book)) {
            books.put(book, newQuantity);
        }
    }
}

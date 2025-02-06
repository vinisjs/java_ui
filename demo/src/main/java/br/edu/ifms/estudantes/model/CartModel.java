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
            // Se o livro já está no carrinho, atualiza a quantidade
            int currentQuantity = books.get(book);
            books.put(book, currentQuantity + quantity);
        } else {
            // Se o livro não está no carrinho, adiciona com a quantidade
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
}
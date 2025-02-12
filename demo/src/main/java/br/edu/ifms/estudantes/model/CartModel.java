package br.edu.ifms.estudantes.model;

import java.util.HashMap;
import java.util.Map;

public class CartModel {
    private Map<BookModel, Integer> books;

    public CartModel() {
        this.books = new HashMap<>();
    }

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

    public Map<BookModel, Integer> getBooks() {
        return books;
    }

    public void clearCart() {
        books.clear();
    }

    public int getTotalBooks() {
        return books.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void updateBookQuantity(BookModel book, int newQuantity) {
        if (books.containsKey(book)) {
            books.put(book, newQuantity);
        }
    }
}

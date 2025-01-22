package br.edu.ifms.estudantes.controller;

import br.edu.ifms.estudantes.model.BookModel;
import br.edu.ifms.estudantes.repo.BookRepo;

public class BookController {
    public BookModel controller(BookModel book) {
        return new BookRepo();
    }
}

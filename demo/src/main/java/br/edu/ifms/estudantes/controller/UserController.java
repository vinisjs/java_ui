package br.edu.ifms.estudantes.controller;


import br.edu.ifms.estudantes.model.UserModel;
import br.edu.ifms.estudantes.repo.UserRepo;

public class UserController {
    public UserModel controller () {
      return new UserRepo();
    };
};

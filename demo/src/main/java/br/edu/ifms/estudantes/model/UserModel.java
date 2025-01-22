package br.edu.ifms.estudantes.model;

public class UserModel {
    private String Nome;
    private String Sexo;
    private int NumberPhone;
    private String email;
    private int NumberId;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public int getNumberPhone() {
        return NumberPhone;
    }

    public void setNumberPhone(int numberPhone) {
        NumberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberId() {
        return NumberId;
    }

    public void setNumberId(int numberId) {
        NumberId = numberId;
    }
}

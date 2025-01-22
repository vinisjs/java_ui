package br.edu.ifms.estudantes.model;

import javax.persistence.*;

@Entity
@Table(name="Users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int NumberId;

    @Column()
    private String Nome;
    @Column()
    private String Sexo;
    @Column()
    private int NumberPhone;
    @Column()
    private String email;

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

package br.usjt.mrrobot.model;

import java.io.Serializable;

public class User implements Serializable {
    private String nome = "Usuário", email;

    public User(){
        super();
    }
    public User(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package br.usjt.mrrobot.model;

public class Mensagem {
    public String mensagem, username;

    public Mensagem(){

    }

    public Mensagem(String usr, String msg){
        this.username = usr;
        this.mensagem = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}

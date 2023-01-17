package com.example.demo.dto;

public class ComentarioMin {
    private String comment_text;
    private String hora;
    private String username;

    public ComentarioMin(String comment_text, String hora, String username) {
        this.comment_text = comment_text;
        this.hora = hora;
        this.username = username;
    }

    public ComentarioMin() {
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package com.example.demo.modelos;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "COMENTARIOS_APP")
public class ComentarioApp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id", nullable = false)
    private int id;
    @Column(name = "comment_text", nullable = false)
    private String comment_text;
    @Column(name = "hora", nullable = false)
    private String hora;

    //CLAVES FORANEAS A CONFIGURAR
    @Column(name = "user_id", nullable = false)
    private String user_id;
    @Column(name = "app_id", nullable = false)
    private String app_id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }
}

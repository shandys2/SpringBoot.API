package com.example.demo.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class ComentarioApp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id", nullable = false)
    private int comment_id;
    @Column(name = "comment_text", nullable = false)
    private String comment_text;
    @Column(name = "hora", nullable = false)
    private String hora;

    //CLAVES FORANEAS A CONFIGURAR
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private Usuario user_id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = "app_id", referencedColumnName = "app_id")
    private Aplicacion app_id;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public ComentarioApp(String comment_text, String hora, Usuario user_id, Aplicacion app_id) {
        this.comment_text = comment_text;
        this.hora = hora;
        this.user_id = user_id;
        this.app_id = app_id;
    }

    public ComentarioApp() {
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

    public Usuario getUser_id() {
        return user_id;
    }

    public void setUser_id(Usuario user_id) {
        this.user_id = user_id;
    }

    public Aplicacion getApp_id() {
        return app_id;
    }

    public void setApp_id(Aplicacion app_id) {
        this.app_id = app_id;
    }
}

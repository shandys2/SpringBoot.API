package com.example.demo.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id", nullable = false)
    private int id;

    @Column(name = "comment_text", nullable = false)
    private String comment_text;

    @Column(name = "hora", nullable = false)
    private String hora;

    @Column(name = "elemento_id", nullable = false)
    private String elemento_id;  // se usa para hacer la llamada a la api correspondiente

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id" ,nullable = false)
    private Usuario user_id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = "app_id", referencedColumnName = "app_id")
    private Aplicacion app_id;

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

    public String getElemento_id() {
        return elemento_id;
    }

    public void setElemento_id(String elemento_id) {
        this.elemento_id = elemento_id;
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

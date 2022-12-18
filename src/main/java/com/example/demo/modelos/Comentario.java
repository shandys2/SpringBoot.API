package com.example.demo.modelos;

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

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id" ,nullable = false)
    private Usuario user_id;
    @ManyToOne
    @JoinColumn(name = "app_id", referencedColumnName = "app_id")
    private Aplicacion app_id;



}

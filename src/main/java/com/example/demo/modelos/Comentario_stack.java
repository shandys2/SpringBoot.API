package com.example.demo.modelos;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "COMENTARIOS_STACK")
public class Comentario_stack implements Serializable {


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


}

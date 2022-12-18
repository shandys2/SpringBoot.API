package com.example.demo.modelos;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private int id;

    @Column(name = "nombreUsuario", nullable = false)
    private String nombre;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name="email", nullable = false)
    private String email;
    public Usuario(){
    }

    public Usuario(String nombre, String password, String email) {
        this.nombre = nombre;
        this.password = password;
        this.email=email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return password;
    }

    public void setContraseña(String password) {
        this.password = password;
    }
}

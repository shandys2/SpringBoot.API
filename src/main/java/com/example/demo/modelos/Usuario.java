package com.example.demo.models;

import jakarta.persistence.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;

@Entity
@Table(name = "USUARIOS")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name="email", nullable = true)
    private String email;
    public Usuario(){
    }

    public Integer getId() {
        return user_id;
    }

    public void setId(Integer id) {
        this.user_id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

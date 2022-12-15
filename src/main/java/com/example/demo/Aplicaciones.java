package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Aplicaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "estrellas", nullable = false)
    private double estrellas;

    @Column(name = "vecesEstrellado", nullable = false)
    private int vecesEstrellado;

    public Aplicaciones(){

    }
    public Aplicaciones(String nombre) {
        this.nombre = nombre;
        this.estrellas = 0;
        this.vecesEstrellado = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getEstrellas() {
        return estrellas/vecesEstrellado;
    }

    public void setEstrellas(double estrellas) {
        this.estrellas = this.estrellas + estrellas;
        this.vecesEstrellado++;
    }
}

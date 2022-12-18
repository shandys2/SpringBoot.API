package com.example.demo.modelos;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "APLICACIONES")
public class Aplicacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "app_id", nullable = false)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;


    @Column(name = "mediaPuntos", nullable = false)
    private double mediaPuntos;

    public Aplicacion(){

    }
    public Aplicacion(String nombre, double mediaPuntos ) {
        this.nombre = nombre;
        this.mediaPuntos = mediaPuntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getEstrellas() {
        return mediaPuntos;
    }

    public void setEstrellas(double mediaPuntos) {
        this.mediaPuntos = this.mediaPuntos;

    }
}

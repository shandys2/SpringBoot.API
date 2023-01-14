package com.example.demo.modelos;

import jakarta.persistence.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "APLICACIONES")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Aplicacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "app_id", nullable = false)
    private int app_id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "mediaPuntos", nullable = false)
    private double mediaPuntos;
    List<ComentarioApp> listaComentarios ;

    public List<ComentarioApp> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<ComentarioApp> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public Aplicacion(int app_id, String nombre, double mediaPuntos) {
        this.app_id = app_id;
        this.nombre = nombre;
        this.mediaPuntos = mediaPuntos;
    }

    public Aplicacion(){}
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public double getMediaPuntos() {
        return mediaPuntos;
    }

    public void setMediaPuntos(double mediaPuntos) {
        this.mediaPuntos = mediaPuntos;
    }
}

package com.example.demo.dto;

import com.example.demo.modelos.ComentarioApp;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class AplicacionDTO {



    private int app_id;

    private String nombre;

    private String descripcion;

    private double mediaPuntos;

    List<ComentarioAppMin> listaComentarios;


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ComentarioAppMin> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<ComentarioAppMin> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public AplicacionDTO(int app_id, String nombre, double mediaPuntos , String descripcion , List<ComentarioAppMin> listaComentarios) {
        this.app_id = app_id;
        this.nombre = nombre;
        this.mediaPuntos = mediaPuntos;
        this.descripcion = descripcion;
        this.listaComentarios=listaComentarios;
    }

    public AplicacionDTO() {
    }

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

package com.example.demo.dto;

import java.util.List;

public class AplicacionDTO {

    private Integer app_id;

    private String nombre;

    private String descripcion;

    private double mediaPuntos;

    List<ComentarioMin> listaComentarios;


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ComentarioMin> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<ComentarioMin> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public AplicacionDTO(int app_id, String nombre, double mediaPuntos , String descripcion , List<ComentarioMin> listaComentarios) {
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

    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }

    public double getMediaPuntos() {
        return mediaPuntos;
    }

    public void setMediaPuntos(double mediaPuntos) {
        this.mediaPuntos = mediaPuntos;
    }
}

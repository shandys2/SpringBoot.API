package com.example.demo.modelos;

import java.util.HashMap;
import java.util.List;

public class ElementoGeneral {

    //Comunes para las demas entidaddes

    public Integer id;
    public String tipo;

    public String name;
    public String image;
    public String description;
    public String version;
    public String publisher;
    public String genero;
    HashMap<Object, Object> detalles;
    List<Comentario> comentarios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public HashMap<Object, Object> getDetalles() {
        return detalles;
    }

    public void setDetalles(HashMap<Object, Object> detalles) {
        this.detalles = detalles;
    }
}

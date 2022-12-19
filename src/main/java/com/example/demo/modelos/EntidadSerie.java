package com.example.demo.modelos;

import java.util.List;

public class EntidadSerie extends EntidadAbstract{
//https://api.tvmaze.com/shows/1

    private List<String> generos;
    private Integer duracion;

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

}

package com.example.demo.dto;

public class MediaPuntosDTO {

    Double puntos;
    Integer app;

    public MediaPuntosDTO(Double puntos, Integer app) {
        this.puntos = puntos;
        this.app = app;
    }

    public Double getMediaPuntos() {
        return puntos;
    }

    public void setMediaPuntos(Double mediaPuntos) {
        this.puntos = mediaPuntos;
    }

    public Integer getApp() {
        return app;
    }

    public void setApp(Integer app) {
        this.app = app;
    }
}

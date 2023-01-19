package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class RankingAppDTO {


    @NotBlank
    private String app_id;
    @NotBlank
    private String puntos;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }
}

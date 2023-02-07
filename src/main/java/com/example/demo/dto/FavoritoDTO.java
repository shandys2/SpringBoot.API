package com.example.demo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class FavoritoDTO {

    @NotBlank
    private String elemento_id;

    @NotBlank
    private String app_id;

    public String getElemento_id() {
        return elemento_id;
    }

    public void setElemento_id(String elemento_id) {
        this.elemento_id = elemento_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }
}

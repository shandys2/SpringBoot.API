package com.example.demo.dto;

import java.io.Serializable;

public class ComentarioDTO implements Serializable {

    private String comment_text;
    private String hora;
    private String elemento_id;
    private Integer app_id;

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getElemento_id() {
        return elemento_id;
    }

    public void setElemento_id(String elemento_id) {
        this.elemento_id = elemento_id;
    }


    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }
}

package com.example.demo.dtos;

import java.io.Serializable;

public class ComentarioDTO implements Serializable {
    private int id;

    private String comment_text;

    private String hora;

    private String elemento_id;

    private Integer user_id;

    private Integer app_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }
}

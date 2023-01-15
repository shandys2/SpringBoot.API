package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ComentarioAppDTO {

    @NotBlank
    private String comment_text;
    @NotBlank
    private String hora;
    @NotNull
    private Integer user_id;
    @NotNull
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

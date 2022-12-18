package com.example.demo.modelos.claves;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Favorito_id implements Serializable {

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "elemento_id", nullable = false)
    private String elemento_id;

    @Column(name = "app_id", nullable = false)
    private String app_id;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Favorito_id)) return false;
        Favorito_id that = (Favorito_id) o;
        return Objects.equals(getUser_id(), that.getUser_id()) &&
                Objects.equals(getApp_id(), that.getApp_id()) &&
                Objects.equals(getElemento_id(), that.getElemento_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(),getApp_id(),getElemento_id());
    }

}

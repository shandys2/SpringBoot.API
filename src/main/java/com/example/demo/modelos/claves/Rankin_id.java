package com.example.demo.modelos.claves;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Rankin_id implements Serializable {

    @Column(name = "user_id", nullable = false)
    private int user_id;
    @Column(name = "app_id", nullable = false)
    private int app_id;

    public Rankin_id() {
    }

    public Rankin_id(int id, int app_id) {
        this.user_id = id;
        this.app_id = app_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rankin_id)) return false;
        Rankin_id that = (Rankin_id) o;
        return Objects.equals(getUser_id(), that.getUser_id()) &&
                Objects.equals(getApp_id(), that.getApp_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getApp_id());
    }
}

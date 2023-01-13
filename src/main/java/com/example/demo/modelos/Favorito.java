package com.example.demo.models;

import com.example.demo.models.claves.Favorito_id;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "FAVORITOS")
public class Favorito implements Serializable {

    @EmbeddedId
    private Favorito_id favorito_id;

    public Favorito() {
    }

    public Favorito(Favorito_id favorito_id) {
        this.favorito_id = favorito_id;
    }

    public Favorito_id getFavorito_id() {
        return favorito_id;
    }

    public void setFavorito_id(Favorito_id favorito_id) {
        this.favorito_id = favorito_id;
    }
}

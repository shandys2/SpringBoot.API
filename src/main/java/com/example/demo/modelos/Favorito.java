package com.example.demo.modelos;

import com.example.demo.modelos.claves.Favorito_id;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "FAVORITOS")
public class Favorito implements Serializable {

    @EmbeddedId
    private Favorito_id favoritoId;
    public Favorito() {
    }
    public Favorito_id getFavoritoId() {
        return favoritoId;
    }
    public void setFavoritoId(Favorito_id favoritoId) {
        this.favoritoId = favoritoId;
    }
}

package com.example.demo.modelos;

import com.example.demo.modelos.claves.Favorito_id;
import com.example.demo.modelos.claves.Rankin_id;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

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

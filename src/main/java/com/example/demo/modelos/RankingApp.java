package com.example.demo.models;


import com.example.demo.models.claves.Rankin_id;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "RANKING_APPS")
public class RankingApp implements Serializable {

    @EmbeddedId
    private Rankin_id rankin_id;

    @Column(name = "puntos", nullable = false)
    private String puntos;


    public Rankin_id getRankin_id() {
        return rankin_id;
    }

    public void setRankin_id(Rankin_id rankin_id) {
        this.rankin_id = rankin_id;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }
}

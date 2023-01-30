package com.example.demo.modelos;

import jakarta.persistence.*;

@Entity
public class PokemonListFormat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "imagen", nullable = true)
    private String imagen;
    @Column(name = "genres", nullable = true)
    private String genres;

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public PokemonListFormat() {
    }

    public PokemonListFormat(String name, String imagen, String genres) {
        this.name = name;
        this.imagen = imagen;
        this.genres=genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

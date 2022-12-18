package com.example.demo.daos;

import com.example.demo.modelos.PokemonListFormat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PokemonDao extends JpaRepository<PokemonListFormat, Integer> {

    @Query("SELECT a FROM PokemonListFormat a")
    List<PokemonListFormat> getAllPokemon();
}

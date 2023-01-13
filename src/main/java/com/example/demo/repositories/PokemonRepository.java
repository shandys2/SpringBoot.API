package com.example.demo.repositories;

import com.example.demo.daos.PokemonDao;
import com.example.demo.modelos.Favorito;
import com.example.demo.modelos.PokemonListFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PokemonRepository {

    @Autowired
    PokemonDao pokemonDao;

    public Object insertarPokemon(PokemonListFormat pokemon){

        pokemonDao.save(pokemon);

        return pokemon;
    }

    public List<PokemonListFormat> getAllPokemons(){

        List<PokemonListFormat> lista = pokemonDao.getAllPokemon();

        return lista;
    }
    public PokemonListFormat getPokemonById(Integer id){

        PokemonListFormat pokemon = pokemonDao.getReferenceById(id);

        return pokemon;
    }

}

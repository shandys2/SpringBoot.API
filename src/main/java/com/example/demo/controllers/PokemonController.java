package com.example.demo.controllers;

import com.example.demo.modelos.PokemonListFormat;
import com.example.demo.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    PokemonRepository pokemonRepository;

    @PostMapping(value="/crearPokemon",consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public PokemonListFormat crearPokemon(@RequestBody PokemonListFormat pokemon) {

        pokemonRepository.insertarPokemon(pokemon);  //Aqui ya le esta metiendo el id, esta mutando el usuario

        return pokemon;
    }
}

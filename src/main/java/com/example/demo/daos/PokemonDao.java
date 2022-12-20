package com.example.demo.daos;

import com.example.demo.modelos.PokemonListFormat;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface PokemonDao extends JpaRepository<PokemonListFormat, Integer> {

    @Query("SELECT a FROM PokemonListFormat a")
    List<PokemonListFormat> getAllPokemon();
}

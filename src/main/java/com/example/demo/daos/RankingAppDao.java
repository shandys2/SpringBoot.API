package com.example.demo.daos;

import com.example.demo.modelos.PokemonListFormat;
import com.example.demo.modelos.RankingApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingAppDap extends JpaRepository<RankingApp, Integer> {
}

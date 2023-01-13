package com.example.demo.repositories;

import com.example.demo.daos.PokemonDao;
import com.example.demo.daos.RankingAppDao;
import com.example.demo.modelos.PokemonListFormat;
import com.example.demo.modelos.RankingApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RankingAppRepository {

    @Autowired
    RankingAppDao rankingAppDao;

    public Object addRankingApp(RankingApp rankingApp){

        rankingAppDao.save(rankingApp);

        return "";
    }
}

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
    public RankingApp getRanking(int app_id, int user_id){
        RankingApp rankingApp = rankingAppDao.getRanking(app_id,user_id);
        return rankingApp;
    }

    public String updateRanking(RankingApp rankingApp){

        rankingAppDao.updateRanking(rankingApp.getPuntos(),rankingApp.getRankin_id());
        rankingAppDao.save(rankingApp);
        return "eliminado";
    }
}

package com.example.demo.repositories;

import com.example.demo.daos.PokemonDao;
import com.example.demo.daos.RankingAppDao;
import com.example.demo.modelos.PokemonListFormat;
import com.example.demo.modelos.RankingApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

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

    public  List<Object> mediaApps(){

        List<Object> object =rankingAppDao.getAvgApps();

        return object;
    }
    public  Object mediaApp(int id){

        Object object =rankingAppDao.getAvgApp(id);

        return object;
    }
}

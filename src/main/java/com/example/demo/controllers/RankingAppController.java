package com.example.demo.controllers;

import com.example.demo.modelos.RankingApp;
import com.example.demo.repositories.RankingAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ranking")
public class RankingAppController {

    @Autowired
    RankingAppRepository rankingAppRepository;

    @PostMapping(value="/crearRanking",consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public RankingApp crearRankingApp(@RequestBody RankingApp rankingApp) {

        rankingAppRepository.addRankingApp(rankingApp);

        return rankingApp;
    }
}

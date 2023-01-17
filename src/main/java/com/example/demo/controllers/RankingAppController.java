package com.example.demo.controllers;

import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.RankingApp;
import com.example.demo.modelos.Usuario;
import com.example.demo.repositories.AppRepository;
import com.example.demo.repositories.RankingAppRepository;
import com.example.demo.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ranking")
public class RankingAppController {

    @Autowired
    RankingAppRepository rankingAppRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AppRepository appRepository;

    @PostMapping(value = "/crearRanking", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object crearRankingApp(@RequestBody @Valid RankingApp rankingApp) {

        Usuario usuario = usuarioRepository.getUsuario(rankingApp.getRankin_id().getUser_id());
        Aplicacion app = appRepository.getApp(rankingApp.getRankin_id().getApp_id());

        if(app==null){
            return false; // ¿? porque no hace bien la relacion con la foreign key
        }
        try {
            int id= usuario.getId();
        }catch (Exception e){
            return false;
        }


        //ver si existe
        RankingApp rankgApp= rankingAppRepository.getRanking(rankingApp.getRankin_id().getApp_id(),rankingApp.getRankin_id().getUser_id());
        //si existe actualizar
        if(rankgApp!=null){
           rankingAppRepository.updateRanking(rankingApp);
        }else{
            rankingAppRepository.addRankingApp(rankingApp);
        }
        //sino crear

        RankingApp rankingAppActualizado= rankingAppRepository.getRanking(rankingApp.getRankin_id().getApp_id(),rankingApp.getRankin_id().getUser_id());
        return true;
    }


}

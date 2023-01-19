package com.example.demo.controllers;

import com.example.demo.dto.RankingAppDTO;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.RankingApp;
import com.example.demo.modelos.Usuario;
import com.example.demo.modelos.claves.Rankin_id;
import com.example.demo.repositories.AppRepository;
import com.example.demo.repositories.RankingAppRepository;
import com.example.demo.repositories.UsuarioRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
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
    public Object crearRankingApp(@RequestBody @Valid RankingAppDTO rankingAppDTO, Authentication authentication) {
        Usuario usuario= findUserByToken(authentication);

        if (usuario==null){
            return false;
        }
        Usuario usuarioR = usuarioRepository.getUsuario(usuario.getId());
        Aplicacion app = appRepository.getApp(Integer.parseInt(rankingAppDTO.getApp_id()));

        if(app==null){
            return false; // ¿? porque no hace bien la relacion con la foreign key
        }

        RankingApp rankingAppNuevo = new RankingApp();
        Rankin_id rankingAppNuevoId = new Rankin_id(usuario.getId(), app.getApp_id());
        rankingAppNuevo.setRankin_id(rankingAppNuevoId);
        rankingAppNuevo.setPuntos(rankingAppDTO.getPuntos());

        //ver si existe
        RankingApp rankingExistente= rankingAppRepository.getRanking(app.getApp_id(),usuario.getId());
        //si existe actualizar
        if(rankingExistente!=null){
           rankingAppRepository.updateRanking(rankingAppNuevo);
        }else{ //sino crear
            rankingAppRepository.addRankingApp(rankingAppNuevo);
        }


        return true;
    }

    public  Usuario findUserByToken(Authentication authentication){
        Usuario recivedUser = (Usuario) authentication.getPrincipal();
        return usuarioRepository.getUsuario(recivedUser.getId());
    }
}

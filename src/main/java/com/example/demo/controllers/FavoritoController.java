package com.example.demo.controllers;


import com.example.demo.dto.FavoritoDTO;
import com.example.demo.modelos.Favorito;
import com.example.demo.modelos.Usuario;
import com.example.demo.modelos.claves.Favorito_id;

import com.example.demo.repositories.FavoritoRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.validators.AppValidator;
import com.example.demo.validators.UsuarioValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/favorito")
public class FavoritoController {

    @Autowired
    FavoritoRepository favoritoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AppValidator appValidator;
    @Autowired
    UsuarioValidator usuarioValidator;

    @PostMapping(value = "/cambiarFavorito", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object crearFavorito(@RequestBody @Valid FavoritoDTO favoritoDTO , Authentication authentication) {

        Usuario usuario= findUserByToken(authentication);
        Favorito favorito = new Favorito();
        Favorito_id favorito_id = new Favorito_id(usuario.getId(),Integer.parseInt(favoritoDTO.getElemento_id()),Integer.parseInt(favoritoDTO.getApp_id()));
        favorito.setFavoritoId(favorito_id);
        Object object = favoritoRepository.addFavorito(favorito);

        return object;
    }

    @GetMapping("/dameFavoritos")
    public Object getListadoFavoritos(@RequestParam int app_id, Authentication authentication) throws IOException {

        Usuario usuario= findUserByToken(authentication);

        List<Favorito> listado = favoritoRepository.getFavoritosUsuario(app_id,usuario.getId());
        List<Integer> listadoIds = new ArrayList<>();

        if(!appValidator.esAppValida(app_id) || usuario==null){
            return false;
        }
        for (Favorito favorito:listado ) {
            int id = favorito.getFavoritoId().getElemento_id();
            listadoIds.add(id);
        }
        return listadoIds;
    }

    public  Usuario findUserByToken(Authentication authentication){
        Usuario recivedUser = (Usuario) authentication.getPrincipal();
        return usuarioRepository.getUsuario(recivedUser.getId());
    }

}

package com.example.demo.controllers;


import com.example.demo.modelos.ElementoListado;
import com.example.demo.modelos.Favorito;
import com.example.demo.repositories.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/favorito")
public class FavoritoController {

    @Autowired
    FavoritoRepository favoritoRepository;

    @PostMapping(value="/cambiarFavorito",consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public Object crearFavorito(@RequestBody Favorito favorito) {

        Object object = favoritoRepository.addFavorito(favorito);

        return object;
    }
    @GetMapping("/dameFavoritos")
    public List<Favorito> getListadoFavoritos(@RequestParam int user_id) throws IOException {

        List<Favorito> listado =favoritoRepository.getFavoritosUsuario(user_id);
        return listado;
    }
}

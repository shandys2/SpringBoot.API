package com.example.demo.controllers;


import com.example.demo.dto.ComentarioMin;
import com.example.demo.modelos.*;
import com.example.demo.network.ConexionApi;
import com.example.demo.repositories.AppRepository;
import com.example.demo.repositories.ComentarioRepository;
import com.example.demo.repositories.ComentarioAppRepository;
import com.example.demo.repositories.PokemonRepository;
import com.example.demo.validators.AppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    ConexionApi conexionApi;
    @Autowired
    ComentarioRepository comentariosRepository;

    @Autowired
    AppRepository appRepository;
    @Autowired
    PokemonRepository pokemonRepository;
    @Autowired
    AppValidator appValidator;

    @GetMapping("/dameListado")
    public List<ElementoListado> getListado(@RequestParam int api) throws IOException {

        List<ElementoListado> listado = null;

        if (appValidator.esAppValida(api)) {
            conexionApi.setApiForList(api);
            listado = conexionApi.getListadoItems();
            return listado;
        }else {
            return listado;
        }
    }

    @GetMapping("/dameElemento")
    public ElementoGeneral getElemento(@RequestParam int api, @RequestParam String item) throws IOException {

        List<Comentario> listaComentarios;

        if(api==2){
               listaComentarios = comentariosRepository.getComentariosItem(appRepository.getApp(api),item);
        }else{
               listaComentarios = comentariosRepository.getComentariosItem(appRepository.getApp(api), item);
        }


        List<ComentarioMin> listaComentariosMin= new ArrayList<>();
        for (Comentario comentario: listaComentarios) {
            ComentarioMin comentarioMin=  new ComentarioMin(comentario.getComment_text(), comentario.getHora() , comentario.getUser_id().getUsername());
            listaComentariosMin.add(comentarioMin);
        }

        ElementoGeneral elemento;
        conexionApi.setApiForElement(api, item);
        elemento = conexionApi.getItem();
        elemento.setComentarios(listaComentariosMin);
        return elemento;
    }

}

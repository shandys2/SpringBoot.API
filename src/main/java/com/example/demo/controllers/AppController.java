package com.example.demo.controllers;

import com.example.demo.dto.AplicacionDTO;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.network.ConexionApi;
import com.example.demo.repositories.AppRepository;
import com.example.demo.repositories.ComentarioAppRepository;
import com.example.demo.validators.AppValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/app")
public class AppController {
    @Autowired
    AppRepository appRepository;
    @Autowired
    ComentarioAppRepository comentarioAppRepository;
    @Autowired
    AppValidator appValidator;

    @GetMapping(value = "/dameApp", produces = MediaType.APPLICATION_JSON_VALUE)
    public AplicacionDTO getApp(@RequestParam int app_id) throws JsonProcessingException {

        if(appValidator.esAppValida(app_id)){
            AplicacionDTO aplicacion = appRepository.getAppWithComments(app_id);
            return aplicacion;
        }else{
           return new AplicacionDTO();
        }
    }
    @CrossOrigin
    @GetMapping("/dameApps")
    public List<Aplicacion> getApps() throws JsonProcessingException {

        List<Aplicacion> listado = appRepository.getAllApps();
        return listado;
    }
    @GetMapping("/dameGeneros")
    public List<String> getGeneros(@RequestParam int app_id) throws JsonProcessingException {
        List<String> listado=null;
    switch (app_id){
        case 1:   listado = ConexionApi.generosJuegos; break;
        case 2:   listado = ConexionApi.generosPokemon;   break;
        case 3:   listado = ConexionApi.generosSeries; break;
    }

        return listado;
    }

}

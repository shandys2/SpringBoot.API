package com.example.demo.controllers;

import com.example.demo.dto.AplicacionDTO;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.repositories.AppRepository;
import com.example.demo.repositories.ComentarioAppRepository;
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


    @GetMapping(value = "/dameApp", produces = MediaType.APPLICATION_JSON_VALUE)
    public AplicacionDTO getApp(@RequestParam int app_id) throws JsonProcessingException {

        AplicacionDTO aplicacion = appRepository.getAppWithComments(app_id);

        return aplicacion;
    }

    @GetMapping("/dameApps")
    public List<Aplicacion> getApps() throws JsonProcessingException {

        List<Aplicacion> listado = appRepository.getAllApps();
        return listado;
    }


}

package com.example.demo.controllers;

import com.example.demo.modelos.Aplicacion;
import com.example.demo.repositories.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
    @RequestMapping("/app")
    public class AppController {
        @Autowired
        AppRepository appRepository;

        @PostMapping(value="/crearApp",consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
        public Aplicacion crearApp(@RequestBody Aplicacion app) {
            appRepository.insertarApp(app);
            return app;
        }

        @GetMapping(value="/getApp", produces= MediaType.APPLICATION_JSON_VALUE)
        public Aplicacion getApp(@RequestBody Aplicacion app) {
            Aplicacion aplicacion= appRepository.getApp(app.getApp_id());
            return aplicacion;
        }

}

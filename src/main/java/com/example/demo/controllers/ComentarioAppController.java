package com.example.demo.controllers;

import com.example.demo.modelos.ComentarioApp;
import com.example.demo.repositories.ComentarioAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comentariostack")
public class ComentarioAppController {

    @Autowired
    ComentarioAppRepository comentarioStackRepository;

    @PostMapping(value="/crearComentarioStack",consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ComentarioApp crearComentarioStack(@RequestBody ComentarioApp comentarioStack) {

        comentarioStackRepository.insertarComentarioStack(comentarioStack);  //Aqui ya le esta metiendo el id, esta mutando el usuario

        return comentarioStack;
    }
}

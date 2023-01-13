package com.example.demo.controllers;

import com.example.demo.modelos.ComentarioStack;
import com.example.demo.repositories.ComentarioStackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comentariostack")
public class ComentarioStackController {

    @Autowired
    ComentarioStackRepository comentarioStackRepository;

    @PostMapping(value="/crearComentarioStack",consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ComentarioStack crearComentarioStack(@RequestBody ComentarioStack comentarioStack) {

        comentarioStackRepository.insertarComentarioStack(comentarioStack);  //Aqui ya le esta metiendo el id, esta mutando el usuario

        return comentarioStack;
    }
}

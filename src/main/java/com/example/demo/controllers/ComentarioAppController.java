package com.example.demo.controllers;

import com.example.demo.dto.ComentarioAppDTO;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.ComentarioApp;
import com.example.demo.modelos.Usuario;
import com.example.demo.repositories.AppRepository;
import com.example.demo.repositories.ComentarioAppRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.validators.AppValidator;
import com.example.demo.validators.UsuarioValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comentariosApp")
public class ComentarioAppController {

    @Autowired
    ComentarioAppRepository comentarioAppRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AppRepository appRepository;
    @Autowired
    AppValidator appValidator;
    @Autowired
    UsuarioValidator usuarioValidator;

    @PostMapping(value = "/crearComentarioApp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean crearComentarioApp(@RequestBody @Valid ComentarioAppDTO comentarioAppDTO ) {

        if(!appValidator.esAppValida(comentarioAppDTO.getApp_id()) || !usuarioValidator.esUsuarioValido(comentarioAppDTO.getUser_id())){
            return false;
        }
        Usuario usuario = usuarioRepository.getUsuario(comentarioAppDTO.getUser_id());
        Aplicacion app = appRepository.getApp(comentarioAppDTO.getApp_id());

        ComentarioApp comentario = new ComentarioApp();
        comentario.setApp_id(app);
        comentario.setUser_id(usuario);
        comentario.setHora(comentarioAppDTO.getHora());
        comentario.setComment_text(comentarioAppDTO.getComment_text());
        comentarioAppRepository.insertarComentarioStack(comentario);

        return true;

    }
}

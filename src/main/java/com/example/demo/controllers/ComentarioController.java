package com.example.demo.controllers;

import com.example.demo.dto.ComentarioDTO;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.Comentario;
import com.example.demo.modelos.Usuario;
import com.example.demo.repositories.AppRepository;
import com.example.demo.repositories.ComentarioRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    ComentarioRepository comentariosRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AppRepository appRepository;

    @PostMapping(value = "/crearComentario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Comentario crearComentario(@RequestBody ComentarioDTO comentarioDTO) {
        Usuario usuario = usuarioRepository.getUsuario(comentarioDTO.getUser_id());
        Aplicacion app = appRepository.getApp(comentarioDTO.getApp_id());
        if(app==null){
            return new Comentario(); // ¿? porque no hace bien la relacion con la foreign key
        }
        Comentario comentario = new Comentario();
        comentario.setApp_id(app);
        comentario.setUser_id(usuario);
        comentario.setHora(comentarioDTO.getHora());
        comentario.setComment_text(comentarioDTO.getComment_text());
        comentario.setElemento_id(comentarioDTO.getElemento_id());

        comentariosRepository.insertarComentario(comentario);
        return comentario;
    }

    @GetMapping(value = "/getComentarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comentario> getComentarios(@RequestParam String app_id, @RequestParam String elemento_id) {
        Aplicacion app = appRepository.getApp(Integer.parseInt(app_id));
        List<Comentario> listaComentarios = comentariosRepository.getComentariosItem(app, elemento_id);
        return listaComentarios;
    }
}
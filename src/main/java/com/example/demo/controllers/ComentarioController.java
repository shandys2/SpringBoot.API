package com.example.demo.controllers;

import com.example.demo.dto.ComentarioDTO;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.Comentario;
import com.example.demo.modelos.Usuario;
import com.example.demo.repositories.AppRepository;
import com.example.demo.repositories.ComentarioRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.validators.AppValidator;
import com.example.demo.validators.UsuarioValidator;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
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

    @Autowired
    AppValidator appValidator;
    @Autowired
    UsuarioValidator usuarioValidator;
    //Authorization authorization   -> se puede meter por parametro al lado del requestbody para rescatar
                                      // datos del usuario sin necesidad de mandarlos
    @PostMapping(value = "/crearComentario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean crearComentario(@RequestBody ComentarioDTO comentarioDTO , Authentication authentication) {

        Usuario usuario= findUserByToken(authentication);

        if(!appValidator.esAppValida(comentarioDTO.getApp_id()) && usuario==null){
            return false;
        }else{
            //Usuario usuario = usuarioRepository.getUsuario(comentarioDTO.getUser_id());
            Aplicacion app = appRepository.getApp(comentarioDTO.getApp_id());
            if(usuario==null || app ==null){
                return false;
            }
            Boolean existe =usuarioRepository.isValid(usuario.getId());
            if (existe){
                Comentario comentario = new Comentario();
                comentario.setApp_id(app);
                comentario.setUser_id(usuario);
                comentario.setHora(comentarioDTO.getHora());
                comentario.setComment_text(comentarioDTO.getComment_text());
                comentario.setElemento_id(comentarioDTO.getElemento_id());
                comentariosRepository.insertarComentario(comentario);
                return true;
            }
        }
       return false;
    }

    @GetMapping(value = "/getComentarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comentario> getComentarios(@RequestParam String app_id, @RequestParam String elemento_id) {
        Aplicacion app = appRepository.getApp(Integer.parseInt(app_id));
        List<Comentario> listaComentarios = comentariosRepository.getComentariosItem(app, elemento_id);
        return listaComentarios;
    }

    public  Usuario findUserByToken(Authentication authentication){
        Usuario recivedUser = (Usuario) authentication.getPrincipal();
        return usuarioRepository.getUsuario(recivedUser.getId());
    }
}
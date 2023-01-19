package com.example.demo.controllers;

import com.example.demo.modelos.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value = "/borrarUsuario")
    public Object borrarUsuario(Authentication authentication) {
       Usuario usuario= findUserByToken(authentication);
        String respuesta;
        try {
            usuarioRepository.borrarUsuario(usuario);
        } catch (Exception e) {
            respuesta = "No se pudo eliminar el usuario porque no existe";
            return respuesta;
        }
        return "usuario " + usuario.getId() + " eliminado";
    }

    public  Usuario findUserByToken(Authentication authentication){
        Usuario recivedUser = (Usuario) authentication.getPrincipal();
        return usuarioRepository.getUsuario(recivedUser.getId());
    }
}

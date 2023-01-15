package com.example.demo.controllers;

import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Object borrarUsuario(@RequestParam int user_id) {

        String respuesta;
        try {
            usuarioRepository.borrarUsuario(user_id);
        } catch (Exception e) {
            respuesta = "Ha habido un error, no se pudo eliminar el usuario porque no existe";
            return respuesta;
        }
        return "usuario con id  " + user_id + " eliminado";
    }


}

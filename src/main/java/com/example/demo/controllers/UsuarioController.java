package com.example.demo.controllers;

import com.example.demo.daos.UsuarioDao;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.modelos.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value="/borrarUsuario")
    public Object borrarUsuario(@RequestParam int user_id) {

        String respuesta;
        try {
            usuarioRepository.borrarUsuario(user_id);
        }catch (Exception e){
            respuesta="Ha habido un error, no se pudo eliminar el usuario porque no existe";
            return respuesta;
        }
        return "usuario con id  "+user_id+" eliminado";
    }



}

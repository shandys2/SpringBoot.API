package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    repositorioUsuarios repUsuario;

    @GetMapping("/crearUsuario")

    public boolean crearUsuario(@RequestParam String nombre, @RequestParam String contraseña) {
        Usuario usuario = repUsuario.save(new Usuario(nombre, contraseña));
        return usuario != null;
    }

    @GetMapping("/login")
    public boolean logear(@RequestParam String nombre, @RequestParam String contraseña) {
        Usuario usuario = repUsuario.getUsuarioByName(nombre);
        if(usuario != null){
            return usuario.getContraseña().equals(contraseña);
        }
        return false;
    }

}

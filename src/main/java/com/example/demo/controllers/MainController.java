package com.example.demo.controllers;


import com.example.demo.daos.PokemonDao;
import com.example.demo.modelos.ElementoListado;
import com.example.demo.modelos.PokemonListFormat;

import com.example.demo.modelos.Usuario;
import com.example.demo.daos.UsuarioDao;

import com.example.demo.network.ConexionApi;
import jakarta.transaction.Transactional;
import org.aspectj.apache.bcel.classfile.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.spel.spi.Function;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Transactional
public class MainController {

    @Autowired
    UsuarioDao repUsuario;
    @Autowired
    ConexionApi conexionApi;

    @PostMapping(value="/crearUsuario",  consumes = {"application/json"})
    public boolean crearUsuario(@RequestBody Usuario usuario) {
         repUsuario.save(usuario);
        return usuario != null;
    }

    @GetMapping("/login")
    public boolean logear(@RequestParam String nombre, @RequestParam String password) {

        Usuario usuario = repUsuario.getUsuarioByName(nombre);
        if(usuario != null){
            return usuario.getContraseña().equals(password);
        }
        return false;
    }
    @GetMapping("/dameListado")
    public  List<ElementoListado> getListado(@RequestParam int api) throws IOException {

      conexionApi.setApi(api);

      List<ElementoListado> listado =conexionApi.getListadoItems();

        return listado;
    }
}

package com.example.demo.controllers;


import com.example.demo.modelos.*;
import com.example.demo.daos.UsuarioDao;
import com.example.demo.network.ConexionApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    UsuarioDao repUsuario;
    @Autowired
    ConexionApi conexionApi;

    @PostMapping(value="/crearUsuario",consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        System.out.println("ANTES  ->" + usuario.getId());
        repUsuario.save(usuario);  //Aqui ya le esta metiendo el id, esta mutando el usuario
        System.out.println("DESPUES  ->" + usuario.getId());
        return usuario;
    }
    @PostMapping(value="/borrarUsuario",consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public Object borrarUsuario(@RequestBody Usuario usuario) {

     String respuesta;
        try {
            repUsuario.deleteById(usuario.getId());
        }catch (Exception e){
            respuesta="Ha habido un error";
            return respuesta;
        }
     //Aqui ya le esta metiendo el id, esta mutando el usuario

        return "usuario "+usuario.getNombre()+" eliminado";
    }

    @GetMapping("/login")
    public boolean logear(@RequestParam String nombre, @RequestParam String password) {

        Usuario usuario = repUsuario.getUsuarioByName(nombre);
        if(usuario != null){
            return usuario.getPassword().equals(password);
        }
        return false;
    }
    @GetMapping("/dameListado")
    public  List<ElementoListado> getListado(@RequestParam int api) throws IOException {

      conexionApi.setApiForList(api);
      List<ElementoListado> listado =conexionApi.getListadoItems();
        return listado;
    }

    @GetMapping("/dameElemento")
    public IElemento getElemento(@RequestParam int api , @RequestParam int item) throws IOException {

        IElemento elemento;
        conexionApi.setApiForElement(api ,item);
        elemento= conexionApi.getItem();
        return elemento;
    }


}

package com.example.demo.repositories;

import com.example.demo.modelos.*;
import com.example.demo.modelos.claves.Favorito_id;
import com.example.demo.modelos.claves.Rankin_id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class DemoDataRepository {

    Usuario usuario;
    Aplicacion aplicacion;
    Comentario comentario;
    ComentarioApp comentarioApp;
    Favorito favorito;
    Favorito_id favoritoId;
    RankingApp rankingApp;
    Rankin_id rankin_id;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AppRepository appRepository;
    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    ComentarioAppRepository comentarioAppRepository;
    @Autowired
    FavoritoRepository FavoritoRepository;

    public String cargarDatosPrueba() {

        Usuario usuario1 = new Usuario();
        usuario1.setNombre("dani");
        usuario1.setPassword("1");
        usuario1.setEmail("dani@gmail.com");
        Usuario usuario2 = new Usuario();
        usuario2.setNombre("maider");
        usuario2.setPassword("1");
        usuario2.setEmail("maider@gmail.com");
        Usuario usuario3 = new Usuario();
        usuario3.setNombre("willy");
        usuario3.setPassword("1");
        usuario3.setEmail("willy@gmail.com");

        usuarioRepository.insertarUsuario(usuario1);
        usuarioRepository.insertarUsuario(usuario2);
        usuarioRepository.insertarUsuario(usuario3);

        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(usuario1);
        listaUsuarios.add(usuario2);
        listaUsuarios.add(usuario3);

        Aplicacion app1 = new Aplicacion(1, "FREE TO GAME", 3.6, "descripcion freetogame....");
        Aplicacion app2 = new Aplicacion(2, "POKEDEX", 4.5, "descripcion pokedex....");
        Aplicacion app3 = new Aplicacion(3, "NETFLIX", 4.7, "descripcion netflix....");

        List<Aplicacion> listaAplicaciones = new ArrayList<>();

        listaAplicaciones.add(app1);
        listaAplicaciones.add(app2);
        listaAplicaciones.add(app3);

        appRepository.insertarApp(app1);
        appRepository.insertarApp(app2);
        appRepository.insertarApp(app3);


        //Generador de comentarios
        for (int i = 0; i < 3; i++) {
            for (Usuario usuario : listaUsuarios) {
                Aplicacion app = listaAplicaciones.get(i);
                Comentario comentario = new Comentario("comentario numero " + i, "10:3" + i,( i +1)+ "", usuario, app);
                ComentarioApp comentarioApp = new ComentarioApp("comentario numero " +i,"11:0"+i,usuario,app);
                comentarioRepository.insertarComentario(comentario);
                comentarioAppRepository.insertarComentarioStack(comentarioApp);
            }
        }

        return "Datos cargados correctamente";
    }


}

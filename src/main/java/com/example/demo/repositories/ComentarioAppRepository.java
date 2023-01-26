package com.example.demo.repositories;

import com.example.demo.daos.ComentarioAppDao;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.Comentario;
import com.example.demo.modelos.ComentarioApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioAppRepository {

    @Autowired
    ComentarioAppDao comentarioAppDao;


    public Object insertarComentarioStack(ComentarioApp comentario) {

        comentarioAppDao.save(comentario);

        return "";
    }
    public List<ComentarioApp> getComentariosApp(Aplicacion app_id) {

        List<ComentarioApp> listaComentarios = comentarioAppDao.getComentariosApp(app_id);

        return listaComentarios;
    }
}

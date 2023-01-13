package com.example.demo.repositories;

import com.example.demo.daos.AplicacionDao;
import com.example.demo.daos.ComentarioDao;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComentariosRepository {

    @Autowired
    ComentarioDao comentarioDao;


    public Object insertarComentario(Comentario comentario){

        comentarioDao.save(comentario);

        return comentario;
    }
    public List<Comentario> getComentariosItem(Aplicacion app_id,String elemento_id){

        List<Comentario> listaComentarios= comentarioDao.getComentariosItem(app_id,elemento_id);

        return listaComentarios;
    }

}
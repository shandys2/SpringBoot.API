package com.example.demo.repositories;

import com.example.demo.daos.ComentarioAppDao;
import com.example.demo.modelos.ComentarioApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ComentarioAppRepository {

    @Autowired
    ComentarioAppDao comentarioStackDao;


    public Object insertarComentarioStack(ComentarioApp comentario){

        comentarioStackDao.save(comentario);

        return "";
    }

}

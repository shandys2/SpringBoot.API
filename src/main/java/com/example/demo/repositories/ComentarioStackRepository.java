package com.example.demo.repositories;

import com.example.demo.daos.ComentarioDao;
import com.example.demo.daos.ComentarioStackDao;
import com.example.demo.modelos.Comentario;
import com.example.demo.modelos.ComentarioStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ComentarioStackRepository {

    @Autowired
    ComentarioStackDao comentarioStackDao;


    public Object insertarComentarioStack(ComentarioStack comentario){

        comentarioStackDao.save(comentario);

        return "";
    }

}

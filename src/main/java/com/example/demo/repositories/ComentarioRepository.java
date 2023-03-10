package com.example.demo.repositories;

import com.example.demo.daos.ComentarioDao;
import com.example.demo.daos.UsuarioDao;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.Comentario;
import com.example.demo.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioRepository {

    @Autowired
    ComentarioDao comentarioDao;
    @Autowired
    UsuarioDao usuarioDao;

    public Boolean insertarComentario(Comentario comentario) {

        Usuario usuario = usuarioDao.getReferenceById(comentario.getUser_id().getId());
        if(usuario==null){
            return false;
        }

        comentarioDao.save(comentario);
        //Comentario comentarioControl=  comentarioDao.getComentario();
        return true;
    }

    public List<Comentario> getComentariosItem(Aplicacion app_id, String elemento_id) {

        List<Comentario> listaComentarios;
        if(app_id.getApp_id()==2){
           listaComentarios  = comentarioDao.getComentariosPokemon(elemento_id);
        }else{
            listaComentarios  = comentarioDao.getComentariosItem(app_id.getApp_id(),elemento_id);
        }


        return listaComentarios;
    }

}
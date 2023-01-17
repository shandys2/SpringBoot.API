package com.example.demo.repositories;

import com.example.demo.daos.AplicacionDao;
import com.example.demo.daos.FavoritoDao;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.ElementoListado;
import com.example.demo.modelos.Favorito;
import com.example.demo.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoritoRepository {
    @Autowired
    FavoritoDao favoritoDao;

    public Object addFavorito(Favorito favorito) {

        int app_id = favorito.getFavoritoId().getApp_id();
        int elemento_id = favorito.getFavoritoId().getElemento_id();
        int user_id = favorito.getFavoritoId().getUser_id();
        Favorito comprobacionFavorito = favoritoDao.getFavorito(app_id, elemento_id, user_id);

        if (comprobacionFavorito == null) {//no esta
            favoritoDao.save(favorito);
            return "FAVORITO GUARDADO";
        } else {// si esta
            favoritoDao.delete(favorito);
            return "FAVORITO ELIMINADO";
        }
    }

    public List<Favorito> getFavoritosUsuario(int app_id,int user_id) {
        List<Favorito> listadoFavorito = favoritoDao.getFavoritosUsuario(app_id,user_id);
        return listadoFavorito;
    }
}

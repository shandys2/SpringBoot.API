package com.example.demo.repositories;

import com.example.demo.daos.AplicacionDao;
import com.example.demo.daos.FavoritoDao;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.ElementoListado;
import com.example.demo.modelos.Favorito;
import com.example.demo.modelos.Usuario;
import com.example.demo.validators.AppValidator;
import com.example.demo.validators.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoritoRepository {
    @Autowired
    FavoritoDao favoritoDao;
    @Autowired
    UsuarioValidator usuarioValidator;
    @Autowired
    AppValidator appValidator;
    public Object addFavorito(Favorito favorito) {

        Integer app_id = favorito.getFavoritoId().getApp_id();
        Integer elemento_id = favorito.getFavoritoId().getElemento_id();
        Integer user_id = favorito.getFavoritoId().getUser_id();

        if (!appValidator.esAppValida(app_id) || !usuarioValidator.esUsuarioValido(user_id)){
            return false;
        }
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

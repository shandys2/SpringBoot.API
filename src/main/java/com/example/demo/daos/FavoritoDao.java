package com.example.demo.daos;

import com.example.demo.modelos.Favorito;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface FavoritoDao extends JpaRepository<Favorito, Integer> {


    @Query("SELECT f FROM Favorito f where f.favoritoId.app_id=?1 and f.favoritoId.elemento_id=?2 and f.favoritoId.user_id=?3")
    Favorito getFavorito(int app_id, int elemento_id, int user_id);

    @Query("SELECT f FROM Favorito f where f.favoritoId.app_id=?1 and f.favoritoId.user_id=?2")
    List<Favorito> getFavoritosUsuario(int app_id,int user_id);


}

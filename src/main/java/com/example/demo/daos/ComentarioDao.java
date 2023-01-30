package com.example.demo.daos;

import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.Comentario;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface ComentarioDao extends JpaRepository<Comentario, Integer> {

    @Query("SELECT c FROM Comentario c where c.app_id.app_id = ?1 and c.elemento_id= ?2")
    List<Comentario> getComentariosItem(int app_id, String elemento_id);
    @Query("SELECT c FROM Comentario c where c.elemento_id=?1")
    List<Comentario> getComentariosPokemon(String elemento_id);
}

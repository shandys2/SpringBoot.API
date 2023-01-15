package com.example.demo.daos;

import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.Comentario;
import com.example.demo.modelos.ComentarioApp;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface ComentarioAppDao extends JpaRepository<ComentarioApp, Integer> {

    @Query("SELECT c FROM ComentarioApp c where c.app_id = ?1 ")
    List<ComentarioApp> getComentariosApp(Aplicacion app_id);
}

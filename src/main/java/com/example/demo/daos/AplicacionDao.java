package com.example.demo.daos;

import com.example.demo.modelos.Aplicacion;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface AplicacionDao extends JpaRepository<Aplicacion, Integer> {

    @Query("select a from Aplicacion a where a.nombre = ?1")
    Aplicacion getAplicacionByName(String nombre);


}

package com.example.demo.daos;

import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AplicacionDao extends JpaRepository<Aplicacion, Integer> {

    @Query("select a from Aplicacion a where a.nombre = ?1")
    Aplicacion getAplicacionByName(String nombre);


}

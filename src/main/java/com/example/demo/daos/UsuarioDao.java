package com.example.demo.daos;

import com.example.demo.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioDao extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u where u.nombre = ?1")
    Usuario getUsuarioByName(String nombre);


}

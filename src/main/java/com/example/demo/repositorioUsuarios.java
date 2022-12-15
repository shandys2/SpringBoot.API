package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface repositorioUsuarios extends JpaRepository<Usuario, Integer> {
    @Query("select u from Usuario u where u.nombre = ?1")
    Usuario getUsuarioByName(String nombre);
}

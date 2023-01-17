package com.example.demo.daos;

import com.example.demo.modelos.Usuario;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface UsuarioDao extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u where u.nombre = ?1")
    Usuario getUsuarioByName(String nombre);

    @Query("SELECT u FROM Usuario u where u.user_id = ?1")
    Usuario getUsuarioById(int user_id);

}

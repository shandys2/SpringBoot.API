package com.example.demo.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Integer user_id;
    @NotBlank(message = "El nombre no puede ser nulo, cadena vacia o espacios en blanco")
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    @NotBlank(message = "El password no puede ser nulo, cadena vacia o espacios en blanco")
    @Column(name = "password", nullable = false)
    private String password;
    @Email(message = "El email tiene que tener un formato correcto")
    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @OneToMany(mappedBy="user_id")
    Set<ComentarioApp> comentariosApps;
    @OneToMany(mappedBy="user_id")
    Set<Comentario> comentarios;
    public Usuario() {
    }

    public Integer getId() {
        return user_id;
    }

    public void setId(Integer id) {
        this.user_id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return this.nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}

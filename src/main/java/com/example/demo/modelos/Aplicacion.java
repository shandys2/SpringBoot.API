package com.example.demo.modelos;

import com.example.demo.dto.ComentarioAppDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Aplicacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "app_id", nullable = false)
    private int app_id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "mediaPuntos", nullable = false)
    private double mediaPuntos;

    public Aplicacion(int app_id, String nombre, double mediaPuntos ,String descripcion) {
        this.app_id = app_id;
        this.nombre = nombre;
        this.mediaPuntos = mediaPuntos;
        this.descripcion = descripcion;
    }
    @OneToMany(mappedBy="app_id")
    Set<ComentarioApp> comentariosApps;

    @OneToMany(mappedBy="app_id")
    Set<Comentario> comentariosItems;

    public Aplicacion() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public double getMediaPuntos() {
        return mediaPuntos;
    }

    public void setMediaPuntos(double mediaPuntos) {
        this.mediaPuntos = mediaPuntos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

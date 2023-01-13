package com.example.demo.modelos;

import java.util.ArrayList;
import java.util.List;

public class ElementoListado {

    public int id;
    public String name;
    public String imagen;

    @Override
    public String toString() {
        return "ElementoListado{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }

    public ElementoListado(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    public static List<ElementoListado> parse(List<PokemonListFormat> lista)
    {
        List<ElementoListado> listado = new ArrayList<>();

        for (PokemonListFormat pokemon:lista ) {
            ElementoListado elementoListado=new ElementoListado();
            elementoListado.setId(Math.toIntExact(pokemon.getId()));
            elementoListado.setName(pokemon.getName());
            elementoListado.setImagen(pokemon.getImagen());
            listado.add(elementoListado);
        }

        return listado;
    }
}

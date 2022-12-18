package com.example.demo.controllers;


import com.example.demo.daos.PokemonDao;
import com.example.demo.modelos.ElementoListado;
import com.example.demo.modelos.PokemonListFormat;

import com.example.demo.modelos.Usuario;
import com.example.demo.daos.UsuarioDao;

import com.example.demo.network.ConexionApi;
import org.aspectj.apache.bcel.classfile.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.spel.spi.Function;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    final String URL_FREE_TO_PLAY ="https://www.freetogame.com/api/games";
    final String URL_POKEAPI="https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0";
    final String URL_NETFLIX="https://api.tvmaze.com/shows";
    @Autowired
    UsuarioDao repUsuario;
    @Autowired
    public static PokemonDao repPokemon;

    @PostMapping("/crearUsuario")
    public boolean crearUsuario(@RequestParam Usuario usuario) {
         repUsuario.save(usuario);
        return usuario != null;
    }

    @GetMapping("/login")
    public boolean logear(@RequestParam String nombre, @RequestParam String password) {
        Usuario usuario = repUsuario.getUsuarioByName(nombre);
        if(usuario != null){
            return usuario.getContraseña().equals(password);
        }
        return false;
    }
    @GetMapping("/dameListado")
    public  List<ElementoListado> getListado(@RequestParam int api) throws IOException {

        ConexionApi conexionApi= new ConexionApi(api);

        return conexionApi.getListadoItems();

    /*    List<ElementoListado> listaElementos;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String response = null;
        StringBuffer stringBuffer;
        // create a client
        URL urlGame = new URL(URL_FREE_TO_PLAY);
        URL urlPoke = new URL(URL_POKEAPI);
        URL urlNetflix = new URL(URL_NETFLIX);

        switch (api) {
            case 1:
                urlConnection = (HttpURLConnection) urlGame.openConnection();
                break;
            case 2:
                urlConnection = (HttpURLConnection) urlPoke.openConnection();
                break;
            case 3:
                urlConnection = (HttpURLConnection) urlNetflix.openConnection();
                break;
            default:

        }
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        InputStream inputStream = urlConnection.getInputStream();
        stringBuffer = new StringBuffer();

        if (inputStream == null) {      //Si no nos devuelve nada salimos
            return null;
        }
        reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line); // salida por consola con salto de linea  mientras haya más registros
        }

        if (stringBuffer.length() == 0) {
            return null;
        }
        response = stringBuffer.toString();
        listaElementos= new ArrayList<>(){};

        try {
            if (api==1){
                JSONArray jsonArray = new JSONArray(response);
              for (int i = 0; i <jsonArray.length() ; i++) {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonArray.getString(i));
                        ElementoListado elementoListado=new ElementoListado();

                        elementoListado.id=jsonObject.getInt("id");
                        elementoListado.name=jsonObject.getString("title");
                        elementoListado.imagen=jsonObject.getString("thumbnail");

                        listaElementos.add(elementoListado);
                        System.out.println(elementoListado.toString());

                    } catch (JSONException e) {
                        System.out.println("FALLO EN EL JSON");
                    }
                }
            }
            if (api==2){

                 List<PokemonListFormat> list= repPokemon.getAllPokemon();

                  if (list.size()==0){
                      listaElementos=cargarPokemons();
                  }else {
                      listaElementos=  ElementoListado.parse(list);
                  }

                return listaElementos;
            }
            if (api==3){
                JSONArray jsonarray = new JSONArray(response);

                for (int i = 0; i < jsonarray.length(); i++){

                    JSONObject jsonObject = jsonarray.getJSONObject(i);

                    ElementoListado elementoListado =new ElementoListado();
                    elementoListado.name=jsonObject.getString("name");
                    elementoListado.id=jsonObject.getInt("id");
                    elementoListado.imagen=jsonObject.getJSONObject("image").getString("medium");
                    listaElementos.add(elementoListado);
                    System.out.println(elementoListado.toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
      return listaElementos;*/
    }



    public List<ElementoListado> cargarPokemons() throws IOException, JSONException {


        URL urlPoke = new URL(URL_POKEAPI);
        List<ElementoListado> lista =new ArrayList<>();
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String response = null;
        StringBuffer stringBuffer;

        urlConnection = (HttpURLConnection) urlPoke.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        InputStream inputStream = urlConnection.getInputStream();
        stringBuffer = new StringBuffer();

        if (inputStream == null) {      //Si no nos devuelve nada salimos
            return null;
        }
        reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line); // salida por consola con salto de linea  mientras haya más registros
        }

        if (stringBuffer.length() == 0) {
            return null;
        }
        response = stringBuffer.toString();
              JSONObject objectListadoPokemon = new JSONObject(response);
                JSONArray arrayListadoPokemon = objectListadoPokemon.getJSONArray("results");
                for (int i = 0; i < arrayListadoPokemon.length(); i++){

                    ElementoListado elementoListado= new ElementoListado();
                   // item.id=arrayListadoPokemon.getJSONObject(i).getInt("id");
                    elementoListado.name= arrayListadoPokemon.getJSONObject(i).getString("name");
                try{
                    URL urlPoke2=new URL("https://pokeapi.co/api/v2/pokemon-form/"+elementoListado.name+"/");
                    urlConnection = (HttpURLConnection) urlPoke2.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    InputStream inputStream2 = urlConnection.getInputStream();
                    stringBuffer = new StringBuffer();
                    //Si no nos devuelve nada salimos
                    if (inputStream2 == null) {
                        return null;
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream2));

                    String line2;
                    while ((line2 = reader.readLine()) != null) {
                        stringBuffer.append(line2);
                    }

                    if (stringBuffer.length() == 0) {
                        return null;
                    }
                    response = stringBuffer.toString();

                    JSONObject objectPokemon = new JSONObject(response);
                    elementoListado.id=objectPokemon.getInt("id");
                    elementoListado.imagen=objectPokemon.getJSONObject("sprites").getString("front_default");

                }catch (Exception e){
                    System.out.println("POKEMON NO DISPONIBLE");
                }
                    System.out.println(elementoListado.toString());
                    lista.add(elementoListado);
                    PokemonListFormat pokemon=  new PokemonListFormat(elementoListado.getName(), elementoListado.getImagen());
                    repPokemon.save(pokemon);

                }
                return lista;
    }
}

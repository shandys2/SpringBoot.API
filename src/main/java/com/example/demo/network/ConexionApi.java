package com.example.demo.network;

import com.example.demo.daos.PokemonDao;
import com.example.demo.modelos.*;
import com.example.demo.repositories.PokemonRepository;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ConexionApi {
    @Autowired
    PokemonRepository pokemonRepository;

    final String URL_FREE_TO_PLAY_LIST = "https://www.freetogame.com/api/games";
    final String URL_POKEAPI_LIST = "https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0";
    final String URL_NETFLIX_LIST = "https://api.tvmaze.com/shows";
    final String URL_FREE_TO_PLAY_ELEMENT = "https://www.freetogame.com/api/game?id=";
    final String URL_POKEAPI_ELEMENT = "https://pokeapi.co/api/v2/pokemon-form/";
    final String URL_NETFLIX_ELEMENT = URL_NETFLIX_LIST + "/";
    URL urlGame;
    URL urlPoke;
    URL urlNetflix;
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String response = null;
    StringBuffer stringBuffer;
    // create a client

    Integer API;
    Integer ITEM;
    List<ElementoListado> listaElementos;

    public static List<String> generosSeries;
    public static List<String> generosJuegos;
    public static List<String> generosPokemon;

    public ConexionApi() throws MalformedURLException {
    }

    public void setApiForList(int api) throws IOException {
        this.API = api;
        setUrlForList();
    }

    public void setApiForElement(int api, int item) throws IOException {
        this.API = api;
        this.ITEM = item;
        setUrlForElement();
    }

    public void setUrlForElement() throws IOException {


        switch (this.API) {
            case 1:
                this.urlGame = new URL(URL_FREE_TO_PLAY_ELEMENT + ITEM);
                urlConnection = (HttpURLConnection) urlGame.openConnection();
                break;
            case 2:
                this.urlPoke = new URL(URL_POKEAPI_ELEMENT + ITEM);
                urlConnection = (HttpURLConnection) urlPoke.openConnection();
                break;
            case 3:
                this.urlNetflix = new URL(URL_NETFLIX_ELEMENT + ITEM);
                urlConnection = (HttpURLConnection) urlNetflix.openConnection();
                break;
            default:
        }
        urlConnection.setRequestMethod("GET");

        conectar();
    }

    public void setUrlForList() throws IOException {
        this.urlGame = new URL(URL_FREE_TO_PLAY_LIST);
        this.urlPoke = new URL(URL_POKEAPI_LIST);
        this.urlNetflix = new URL(URL_NETFLIX_LIST);

        switch (this.API) {
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

        conectar();
    }

    public void conectar() throws IOException {

        urlConnection.connect();

        InputStream inputStream = urlConnection.getInputStream();
        stringBuffer = new StringBuffer();
        if (inputStream != null) {
            reader = new BufferedReader(new InputStreamReader(inputStream));
        }
    }

    public ElementoGeneral getItem() throws IOException {

        String line;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line); // salida por consola con salto de linea  mientras haya más registros
        }

        if (stringBuffer.length() == 0) {
            return null;
        }
        response = stringBuffer.toString();

        ElementoGeneral elemento = null;

        try {
            JSONObject jsonObject = new JSONObject(response);

            switch (API) {
                case 1:
                    elemento = new ElementoGeneral();
                    elemento.setId(jsonObject.getInt("id"));
                    elemento.setName(jsonObject.getString("title"));
                    elemento.setImage(jsonObject.getString("thumbnail"));
                    elemento.setDescription(jsonObject.getString("description"));
                    elemento.setGenero(jsonObject.getString("genre"));
                    elemento.setPublisher(jsonObject.getString("publisher"));
                    elemento.setVersion(jsonObject.getString("release_date"));
                    HashMap<Object, Object> detalles = new HashMap<>();
                    detalles.put("valor1", "valorrrrrrr");
                    detalles.put("valor2", "valorrrrrrr");
                    detalles.put("valor3", "valorrrrrrr");
                    detalles.put("valor4", "valorrrrrrr");
                    detalles.put("valor5", "valorrrrrrr");

                    elemento.setDetalles(detalles);
                    break;
                case 2:
                    elemento = new ElementoGeneral();
                    elemento.setId(jsonObject.getInt("id"));
                    elemento.setName(jsonObject.getJSONObject("pokemon").getString("name"));
                    elemento.setVersion(jsonObject.getJSONObject("version_group").getString("name"));
                    elemento.setGenero(parseTiposPokemon(jsonObject.getJSONArray("types")));  // AQUI TENGO GENEROS
                    elemento.setPublisher("GAME FREAK");
                    elemento.setImage(getPokemonImage());
                    elemento.setDescription(getPokemonDescription());
                    HashMap<Object, Object> cosas = new HashMap<>();
                    HashMap<Object, Object> cosasRow = new HashMap<>();
                    cosasRow.put("stats 1", "valorrrrrrr");
                    cosasRow.put("stats 2", "valorrrrrrr");
                    cosasRow.put("stats 3", "valorrrrrrr");
                    cosasRow.put("stats 4", "valorrrrrrr");
                    cosasRow.put("stats 5", "valorrrrrrr");
                    cosasRow.put("stats 6", "valorrrrrrr");
                    cosas.put("stats", cosasRow);
                    cosasRow = new HashMap<>();
                    cosasRow.put("height", "valorrrrrrr");
                    cosasRow.put("weight", "valorrrrrrr");
                    cosas.put("dimensiones", cosasRow);
                    cosasRow = new HashMap<>();
                    cosasRow.put("habilidad1", "valorrrrrrr");
                    cosasRow.put("habilidad2", "valorrrrrrr");
                    cosas.put("abilities", cosasRow);

                    ((ElementoGeneral) elemento).setDetalles(cosas);
                    //stats :   (dimensiones)altura peso  : habilidades
                    break;
                case 3:
                    elemento = new ElementoGeneral();
                    elemento.setId(jsonObject.getInt("id"));
                    elemento.setName(jsonObject.getString("name"));
                    elemento.setImage(jsonObject.getJSONObject("image").getString("medium"));
                    elemento.setDescription(jsonObject.getString("summary"));
                    elemento.setPublisher(jsonObject.getJSONObject("network").getString("name"));
                    elemento.setVersion(jsonObject.getString("premiered"));
                    elemento.setGenero(parseGeneros(jsonObject.getJSONArray("genres")));
                    HashMap<Object, Object> cosasNetflix = new HashMap<>();
                    cosasNetflix.put("valor1", "valorrrrrrr");
                    cosasNetflix.put("valor2", "valorrrrrrr");
                    cosasNetflix.put("valor3", "valorrrrrrr");
                    cosasNetflix.put("valor4", "valorrrrrrr");
                    cosasNetflix.put("valor5", "valorrrrrrr");
                    cosasNetflix.put("valor6", "valorrrrrrr");
                    cosasNetflix.put("valor7", "valorrrrrrr");
                    cosasNetflix.put("valor8", "valorrrrrrr");
                    cosasNetflix.put("valor9", "valorrrrrrr");
                    ((ElementoGeneral) elemento).setDetalles(cosasNetflix);
                    break;
            }
        } catch (Exception e) {
        }
        return elemento;
    }

    public List<ElementoListado> getListadoItems() throws IOException {



        String line;
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line); // salida por consola con salto de linea  mientras haya más registros
        }

        if (stringBuffer.length() == 0) {
            return null;
        }
        response = stringBuffer.toString();
        listaElementos = new ArrayList<>() {
        };

        try {
            if (API == 1) {
                JSONArray jsonArray = new JSONArray(response);
                generosJuegos= new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonArray.getString(i));
                        ElementoListado elementoListado = new ElementoListado();

                        elementoListado.id = jsonObject.getInt("id");
                        elementoListado.name = jsonObject.getString("title");
                        elementoListado.imagen = jsonObject.getString("thumbnail");

                        if(generosJuegos.indexOf(jsonObject.getString("genre"))==-1){
                            generosJuegos.add(jsonObject.getString("genre").trim());
                        }

                        listaElementos.add(elementoListado);
                        System.out.println(elementoListado.toString());

                    } catch (JSONException e) {
                        System.out.println("FALLO EN EL JSON");
                    }
                }
            }
            if (API == 2) {
                generosPokemon= new ArrayList<>();
                List<PokemonListFormat> list = pokemonRepository.getAllPokemons();
                if (list == null || list.size() == 0) {
                    listaElementos = cargarPokemons();
                } else {
                    listaElementos = ElementoListado.parse(list);
                }
            }
            if (API == 3) {
                JSONArray jsonarray = new JSONArray(response);
                generosSeries= new ArrayList<>();
                for (int i = 0; i < jsonarray.length(); i++) {

                    JSONObject jsonObject = jsonarray.getJSONObject(i);

                    ElementoListado elementoListado = new ElementoListado();
                    elementoListado.name = jsonObject.getString("name");
                    elementoListado.id = jsonObject.getInt("id");
                    elementoListado.imagen = jsonObject.getJSONObject("image").getString("medium");

                    String generos = parseGeneros(jsonObject.getJSONArray("genres"));
                    String [] genres= generos.split(";");

                    for (String genero:genres) {
                        if(generosSeries.indexOf(genero)==-1 && !genero.equals("")){
                            generosSeries.add(genero);
                        }
                    }

                    listaElementos.add(elementoListado);
                    System.out.println(elementoListado.toString());

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaElementos;

    }

    public String parseGeneros(JSONArray array) throws JSONException {
        String generos = "";

        for (int i = 0; i < array.length(); i++) {
            generos = generos + array.get(i) + ";";
        }
        return generos;
    }

    public String parseTiposPokemon(JSONArray array) throws JSONException {
        String tipos = "";
        for (int i = 0; i < array.length(); i++) {
            tipos = tipos + array.getJSONObject(i).getJSONObject("type").getString("name") + ";";
        }
        return tipos;
    }

    public String getPokemonDescription() throws IOException, JSONException {

        URL urlPoke = new URL("https://pokeapi.co/api/v2/pokemon-species/" + ITEM);
        List<ElementoListado> lista = new ArrayList<>();
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
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("flavor_text_entries");

        String descripcion = "";
        for (int i = 0; i < jsonArray.length(); i++) {
            if (jsonArray.getJSONObject(i).getJSONObject("language").getString("name").equals("en")) {
                descripcion = descripcion + jsonArray.getJSONObject(i).getString("flavor_text") + ";";
            }
        }
        descripcion = descripcion.replaceAll("\\n", " ");
        descripcion = descripcion.replaceAll("\\u000c", " ");
        return descripcion;
    }

    public List<ElementoListado> cargarPokemons() throws IOException, JSONException {

        URL urlPoke = new URL(URL_POKEAPI_LIST);
        List<ElementoListado> lista = new ArrayList<>();
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
        for (int i = 0; i < arrayListadoPokemon.length(); i++) {

            ElementoListado elementoListado = new ElementoListado();
            // item.id=arrayListadoPokemon.getJSONObject(i).getInt("id");
            elementoListado.name = arrayListadoPokemon.getJSONObject(i).getString("name");
            try {
                URL urlPoke2 = new URL("https://pokeapi.co/api/v2/pokemon-form/" + elementoListado.name + "/");
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
                elementoListado.id = objectPokemon.getInt("id");
                elementoListado.imagen = objectPokemon.getJSONObject("sprites").getString("front_default");
                JSONArray types= objectPokemon.getJSONArray("types");

                for (int x = 0; x < types.length(); x++) {
                 String  tipo =  types.getJSONObject(x).getJSONObject("type").getString("name") ;
                    if (generosPokemon.indexOf(tipo)==-1){
                        generosPokemon.add(tipo);
                    }
                }

                if (elementoListado.imagen == null || elementoListado.imagen == "null") {
                    System.out.println("ERROR POKEMON -->" + elementoListado.name + " NO AÑIADIDO POR FALTA DE IMAGEN");

                } else {
                    System.out.println(elementoListado.toString());
                    lista.add(elementoListado);
                    PokemonListFormat pokemon = new PokemonListFormat(elementoListado.getName(), elementoListado.getImagen());
                    pokemonRepository.insertarPokemon(pokemon);
                }

                urlConnection.disconnect();

            } catch (Exception e) {
                System.out.println("ERROR POKEMON -->" + elementoListado.name + " NO DISPONIBLE EN LA API");
            }
        }
        return lista;
    }

    public void cargarGeneros(){

    }

    public String getPokemonImage() {
        String image;
        PokemonListFormat pokemon = pokemonRepository.getPokemonById(ITEM);
        image = pokemon.getImagen();
        return image;
    }
}





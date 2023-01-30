package com.example.demo.network;


import com.example.demo.modelos.ElementoGeneral;
import com.example.demo.modelos.ElementoListado;
import com.example.demo.modelos.PokemonListFormat;
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
import java.util.Map;

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
    final String URL_POKEAPI_ELEMENT_DETAILS = "https://pokeapi.co/api/v2/pokemon/";
    final String URL_NETFLIX_ELEMENT = URL_NETFLIX_LIST + "/";
    URL urlGame;
    URL urlPoke;
    URL urlPokeDetails;
    URL urlNetflix;
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String response = null;
    StringBuffer stringBuffer;
    // create a client

    Integer API;
    Integer ITEM;
    String STRING_ITEM;
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

    public void setApiForElement(int api, String item) throws IOException {

        if(api==2){
            this.API = api;
            this.STRING_ITEM=item;
        }else {
            this.API = api;
            this.ITEM = Integer.valueOf(item);
        }

        setUrlForElement();
    }

    public void setUrlForElement() throws IOException {


        switch (this.API) {
            case 1:
                this.urlGame = new URL(URL_FREE_TO_PLAY_ELEMENT + ITEM);
                urlConnection = (HttpURLConnection) urlGame.openConnection();
                break;
            case 2:
                this.urlPoke = new URL(URL_POKEAPI_ELEMENT + STRING_ITEM);
                urlConnection = (HttpURLConnection) urlPoke.openConnection();
                this.urlPokeDetails =new URL(URL_POKEAPI_ELEMENT_DETAILS + STRING_ITEM);
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
                    elemento.setTipo("juego");
                    elemento.setId(jsonObject.getInt("id"));
                    elemento.setName(jsonObject.getString("title"));
                    elemento.setImage(jsonObject.getString("thumbnail"));
                    elemento.setDescription(jsonObject.getString("description"));
                    elemento.setGenero(jsonObject.getString("genre"));
                    elemento.setPublisher(jsonObject.getString("publisher"));
                    elemento.setVersion(jsonObject.getString("release_date"));
                    HashMap<Object, Object> detalles = new HashMap<>();
                    detalles.put("os", jsonObject.getJSONObject("minimum_system_requirements").getString("os"));
                    detalles.put("processor", jsonObject.getJSONObject("minimum_system_requirements").getString("processor"));
                    detalles.put("memory", jsonObject.getJSONObject("minimum_system_requirements").getString("memory"));
                    detalles.put("graphics", jsonObject.getJSONObject("minimum_system_requirements").getString("graphics"));
                    detalles.put("storage", jsonObject.getJSONObject("minimum_system_requirements").getString("storage"));

                    elemento.setDetalles(detalles);
                    break;
                case 2:
                    elemento = new ElementoGeneral();
                    elemento.setTipo("pokemon");
                    elemento.setId(jsonObject.getInt("id"));
                    elemento.setName(jsonObject.getJSONObject("pokemon").getString("name"));
                    elemento.setVersion(jsonObject.getJSONObject("version_group").getString("name"));
                    elemento.setGenero(parseTiposPokemon(jsonObject.getJSONArray("types")));  // AQUI TENGO GENEROS
                    elemento.setPublisher("GAME FREAK");
                    elemento.setImage(getPokemonImage());
                    elemento.setDescription(getPokemonDescription());

                    //cierro la conexion anterior y abro la siguiente
                    urlConnection.disconnect();
                    urlConnection =(HttpURLConnection) urlPokeDetails.openConnection();
                    conectar();

                    while ((line = reader.readLine()) != null) {
                        stringBuffer.append(line);
                    }

                    if (stringBuffer.length() == 0) {
                        return null;
                    }
                    JSONObject  jsonObject2;
                    response = stringBuffer.toString();
                    jsonObject2 = new JSONObject(response);

                    JSONArray stats = new JSONArray();
                    JSONArray abilities = new JSONArray();

                    stats=jsonObject2.getJSONArray("stats");
                    abilities=jsonObject2.getJSONArray("abilities");
                    Integer height =jsonObject2.getInt("height");
                    Integer weight =jsonObject2.getInt("weight");

                    String [] statNames ={"hp","attack","defense","special-attack","special-defense","speed"};

                    HashMap<Object, Object> cosas = new HashMap<>();
                    Map<String, Integer> mapaStats= new HashMap<>();

                    for (int i = 0; i <stats.length() ; i++) {
                        JSONObject statObject= stats.getJSONObject(i);
                        Integer valor = statObject.getInt("base_stat");
                        mapaStats.put(statNames[i].replace("-","_"),valor);
                    }
                    cosas.put("stats", mapaStats);
                    Map cosasRow = new HashMap<>();
                    cosasRow.put("height", height);
                    cosasRow.put("weight", weight);
                    cosas.put("dimensiones", cosasRow);

                    Map<String,String> mapAbilities= new HashMap<>();
                    List<String> habilidades= new ArrayList<>();
                    for (int i = 0; i <abilities.length() ; i++) {
                        JSONObject abilitieObject= abilities.getJSONObject(i);
                        JSONObject innerObject =abilitieObject.getJSONObject("ability");
                        String ability =innerObject.getString("name");

                      habilidades.add(ability);
                    }
                    cosas.put("habilidades",habilidades);

                    elemento.setDetalles(cosas);

                    break;
                case 3:
                    elemento = new ElementoGeneral();
                    elemento.setTipo("serie");
                    elemento.setId(jsonObject.getInt("id"));
                    elemento.setName(jsonObject.getString("name"));
                    elemento.setImage(jsonObject.getJSONObject("image").getString("medium"));
                    elemento.setDescription(jsonObject.getString("summary"));
                    elemento.setPublisher(jsonObject.getJSONObject("network").getString("name"));
                    elemento.setVersion(jsonObject.getString("premiered"));
                    elemento.setGenero(parseGeneros(jsonObject.getJSONArray("genres")));
                    HashMap<Object, Object> cosasNetflix = new HashMap<>();
                    cosasNetflix.put("duracion", jsonObject.getString("runtime"));
                    cosasNetflix.put("web", jsonObject.getString("officialSite"));
                    cosasNetflix.put("inicio", jsonObject.getString("premiered"));
                    cosasNetflix.put("fin", jsonObject.getString("ended"));
                    cosasNetflix.put("hora", jsonObject.getJSONObject("schedule").getString("time"));
                    List<String> listaDias = new ArrayList<>();
                    JSONArray dias=jsonObject.getJSONObject("schedule").getJSONArray("days");
                    for (int i = 0; i < dias.length() ; i++) {
                        listaDias.add(dias.getString(i));
                    }
                    cosasNetflix.put("dias", listaDias);

                    elemento.setDetalles(cosasNetflix);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                        elementoListado.genres = jsonObject.getString("genre").trim();
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
                    elementoListado.genres="";
                    for (String genero:genres) {
                        if(generosSeries.indexOf(genero)==-1 && !genero.equals("")){
                            generosSeries.add(genero);
                        }
                        elementoListado.genres=elementoListado.genres +genero +";";
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

        URL urlPoke = new URL("https://pokeapi.co/api/v2/pokemon-species/" + STRING_ITEM);
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
                elementoListado.genres="";
                for (int x = 0; x < types.length(); x++) {
                 String  tipo =  types.getJSONObject(x).getJSONObject("type").getString("name") ;
                    if (generosPokemon.indexOf(tipo)==-1){
                        generosPokemon.add(tipo);
                    }
                    elementoListado.genres= elementoListado.getGenres() + tipo + ";";
                }

                if (elementoListado.imagen == null || elementoListado.imagen == "null") {
                    System.out.println("ERROR POKEMON -->" + elementoListado.name + " NO AÑADIDO POR FALTA DE IMAGEN");
                 /*   PokemonListFormat pokemon = new PokemonListFormat();
                    pokemonRepository.insertarPokemon(pokemon);
                    pokemon.setName("ERROR");
                    pokemonRepository.borrarPokemon(pokemon);*/

                } else {
                    System.out.println(elementoListado.toString());
                    lista.add(elementoListado);
                    PokemonListFormat pokemon = new PokemonListFormat(elementoListado.getName(), elementoListado.getImagen() ,elementoListado.getGenres());
                    pokemonRepository.insertarPokemon(pokemon);

                }

                urlConnection.disconnect();

            } catch (Exception e) {
                System.out.println("ERROR POKEMON -->" + elementoListado.name + " NO DISPONIBLE EN LA API");
             /*   PokemonListFormat pokemon = new PokemonListFormat();
                pokemon.setName("ERROR");
               pokemonRepository.insertarPokemon(pokemon);
                pokemonRepository.borrarPokemon(pokemon);*/
            }
        }
        return lista;
    }

    public void cargarGeneros(){

    }

    public String getPokemonImage() {
        String image;
        PokemonListFormat pokemon = pokemonRepository.getPokemonByName(STRING_ITEM);
        image = pokemon.getImagen();
        return image;
    }
}





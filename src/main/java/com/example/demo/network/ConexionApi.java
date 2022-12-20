package com.example.demo.network;

import com.example.demo.controllers.MainController;
import com.example.demo.daos.PokemonDao;
import com.example.demo.daos.UsuarioDao;
import com.example.demo.daos.repoPokemon;
import com.example.demo.modelos.ElementoGeneral;
import com.example.demo.modelos.ElementoListado;
import com.example.demo.modelos.PokemonListFormat;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
public class ConexionApi {

   /* @Autowired
    repoPokemon repPokemon;
    */
    @Autowired
    PokemonDao pokemonDao;
    public static ConexionApi conexionApi;
    final String URL_FREE_TO_PLAY_LIST = "https://www.freetogame.com/api/games";
    final String URL_POKEAPI_LIST = "https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0";
    final String URL_NETFLIX_LIST = "https://api.tvmaze.com/shows";
    final String URL_FREE_TO_PLAY_ELEMENT = "https://www.freetogame.com/api/game?id=";
    final String URL_POKEAPI_ELEMENT = "https://pokeapi.co/api/v2/pokemon-form/";
    final String URL_NETFLIX_ELEMENT = URL_NETFLIX_LIST+"/";
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

    public ConexionApi() throws MalformedURLException {}

    public void setApiForList(int api) throws IOException {
        this.API=api;
        setUrlForList();
    }

    public void setApiForElement(int api ,int item) throws IOException {
        this.API=api;
        this.ITEM =item;
        setUrlForElement();
    }

    public void setUrlForElement() throws IOException {


        switch (this.API) {
            case 1:
                this.urlGame = new URL(URL_FREE_TO_PLAY_ELEMENT + ITEM);
                urlConnection = (HttpURLConnection) urlGame.openConnection();
                break;
            case 2:
                this.urlPoke = new URL(URL_POKEAPI_ELEMENT +ITEM);
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

            ElementoGeneral elemento = new ElementoGeneral();

            try {
                JSONObject jsonObject = new JSONObject(response);

                switch (API){
                    case 1 :
                        elemento.setId(jsonObject.getInt("id"));
                        elemento.setName(jsonObject.getString("title"));
                        elemento.setImage(jsonObject.getString("thumbnail"));
                        elemento.setDescription(jsonObject.getString("description"));
                        elemento.setGenero(jsonObject.getString("genre"));
                        elemento.setPublisher(jsonObject.getString("publisher"));
                        elemento.setVersion(jsonObject.getString("release_date"));
                        break;
                    case 2 :
                        elemento.setId(jsonObject.getInt("id"));
                        elemento.setName(jsonObject.getJSONObject("pokemon").getString("name"));
                        elemento.setVersion(jsonObject.getJSONObject("version_group").getString("name"));
                        elemento.setGenero(parseTiposPokemon(jsonObject.getJSONArray("types")));
                        elemento.setPublisher("GAME FREAK");
                        elemento.setImage(getPokemonImage());
                        elemento.setDescription( getPokemonDescription());
                        break;
                    case 3 :
                        elemento.setId(jsonObject.getInt("id"));
                        elemento.setName(jsonObject.getString("name"));
                        elemento.setImage(jsonObject.getJSONObject("image").getString("medium"));
                        elemento.setDescription(jsonObject.getString("summary"));
                        elemento.setPublisher(jsonObject.getJSONObject("network").getString("name"));
                        elemento.setVersion(jsonObject.getString("premiered"));
                        elemento.setGenero(parseGeneros(jsonObject.getJSONArray("genres")));
                        break;
                }

            }catch (Exception e){

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
        listaElementos= new ArrayList<>(){};

        try {
            if (API==1){
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
            if (API==2){

                List<PokemonListFormat> list= pokemonDao.getAllPokemon();
                if (list==null || list.size()==0){
                    listaElementos=cargarPokemons();
                }else {
                    listaElementos=  ElementoListado.parse(list);
                }
            }
            if (API==3){
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaElementos;

    }

    public String parseGeneros(JSONArray array) throws JSONException {

        String generos="";

        for (int i = 0; i <array.length() ; i++) {
            generos=generos+array.get(i)+";";
        }
        return generos;
    }

    public String parseTiposPokemon(JSONArray array) throws JSONException {
        String tipos="";
        for (int i = 0; i <array.length() ; i++) {
            tipos=tipos+array.getJSONObject(i).getJSONObject("type").getString("name") + ";";
        }
        return tipos;
    }

    public String getPokemonDescription() throws IOException, JSONException {



        URL urlPoke = new URL("https://pokeapi.co/api/v2/pokemon-species/"+ITEM);
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
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray=jsonObject.getJSONArray("flavor_text_entries");

        String descripcion="";
        for (int i = 0; i <jsonArray.length() ; i++) {
            if(jsonArray.getJSONObject(i).getJSONObject("language").getString("name").equals("en")){
               descripcion=descripcion+jsonArray.getJSONObject(i).getString("flavor_text") +";";
            }
        }
        descripcion=descripcion.replaceAll("\\n"," ");
        descripcion=descripcion.replaceAll("\\u000c"," ");
        return descripcion;
    }
    public List<ElementoListado> cargarPokemons() throws IOException, JSONException {


        URL urlPoke = new URL(URL_POKEAPI_LIST);
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

                if (elementoListado.imagen==null){
                    System.out.println("ERROR POKEMON -->" +elementoListado.name + " NO AÑIADIDO POR FALTA DE IMAGEN");

                }else{
                    System.out.println(elementoListado.toString());
                    lista.add(elementoListado);
                    PokemonListFormat pokemon=  new PokemonListFormat(elementoListado.getName(), elementoListado.getImagen());
                    pokemonDao.save(pokemon);
                }

                urlConnection.disconnect();

            }catch (Exception e){
                System.out.println("ERROR POKEMON -->"+  elementoListado.name+" NO DISPONIBLE EN LA API");
            }


        }
        return lista;
    }

    public String getPokemonImage(){
        String image;
        PokemonListFormat pokemon= pokemonDao.getReferenceById(ITEM);
        image=pokemon.getImagen();
        return image;
    }
}





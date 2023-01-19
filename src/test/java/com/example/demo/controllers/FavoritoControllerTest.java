package com.example.demo.controllers;

import com.example.demo.dto.FavoritoDTO;
import com.example.demo.modelos.Favorito;
import com.example.demo.modelos.claves.Favorito_id;
import com.example.demo.security.AuthRequest;
import com.example.demo.security.AuthResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.Collection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
public class FavoritoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private String token;

    //Antes de realizar los test tenemos que obtener el token para poder hacer las peticiones
    @BeforeEach
    public void init() {
        AuthRequest authRequest =new AuthRequest();
        authRequest.setUsername("dani");
        authRequest.setPassword("1");
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(authRequest);
            MockHttpServletRequestBuilder requestBuilder = post("/auth/login");
            requestBuilder.content(jsonString);
            requestBuilder.contentType(MediaType.APPLICATION_JSON);
            ResultActions resultActions;
            resultActions = this.mockMvc.perform(requestBuilder);
            String resultado= resultActions.andReturn().getResponse().getContentAsString();
            Gson gson = new Gson(); // Or use new GsonBuilder().create();
            AuthResponse authResponse = gson.fromJson(resultado, AuthResponse.class);
            token=authResponse.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @Transactional
    public void testCambiarFavorito() throws Exception {

        FavoritoDTO favoritoDTO = new FavoritoDTO();
        favoritoDTO.setApp_id("1");
        favoritoDTO.setElemento_id("1");

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(favoritoDTO);
        MockHttpServletRequestBuilder requestBuilder = post("/favorito/cambiarFavorito");
        requestBuilder.header("Authorization", "Bearer " + this.token);
        requestBuilder.content(jsonString);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions;
        resultActions = this.mockMvc.perform(requestBuilder);
        String resultado= resultActions.andReturn().getResponse().getContentAsString();

        Assert.assertTrue( resultado.equals("FAVORITO GUARDADO"));

        FavoritoDTO favoritoDTO2 = new FavoritoDTO();
        favoritoDTO2.setApp_id("1");
        favoritoDTO2.setElemento_id("1");

        //Ahora comprobamos que se elimina al volver hacer la peticion con la misma id compuesta

        ObjectMapper mapper2 = new ObjectMapper();
        String jsonString2 = mapper2.writeValueAsString(favoritoDTO2);
        MockHttpServletRequestBuilder requestBuilder2 = post("/favorito/cambiarFavorito");
        requestBuilder2.header("Authorization", "Bearer " + this.token);
        requestBuilder2.content(jsonString2);
        requestBuilder2.contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions2;
        resultActions2 = this.mockMvc.perform(requestBuilder2);
        String resultado2= resultActions2.andReturn().getResponse().getContentAsString();
        Assert.assertTrue( resultado2.equals("FAVORITO ELIMINADO"));

    }

    @Test
    public void testCambiarFavoritosErrorApp() throws Exception {


        FavoritoDTO favoritoDTO = new FavoritoDTO();
        favoritoDTO.setApp_id("99");
        favoritoDTO.setElemento_id("1");

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(favoritoDTO);
        MockHttpServletRequestBuilder requestBuilder = post("/favorito/cambiarFavorito");
        requestBuilder.header("Authorization", "Bearer " + this.token);
        requestBuilder.content(jsonString);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions;
        resultActions = this.mockMvc.perform(requestBuilder);
        String resultado= resultActions.andReturn().getResponse().getContentAsString();

        Assert.assertTrue( resultado.equals("false"));
    }
    @Test
    public void testDameFavoritos() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = get("/favorito/dameFavoritos?app_id=1");
        requestBuilder.header("Authorization", "Bearer " + this.token);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions;
        resultActions = this.mockMvc.perform(requestBuilder);
        String resultado= resultActions.andReturn().getResponse().getContentAsString();
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<Integer>>(){}.getType();
        Collection<Integer> coleccionElement = gson.fromJson(resultado, collectionType);
        Assert.assertTrue("la lista de favoritos no esta vacia" , coleccionElement!=null);
    }


    @Test
    public void testDameFavoritosErrorApp() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = get("/favorito/dameFavoritos?app_id=99");
        requestBuilder.header("Authorization", "Bearer " + this.token);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions;
        resultActions = this.mockMvc.perform(requestBuilder);
        String resultado= resultActions.andReturn().getResponse().getContentAsString();

        Assert.assertTrue( resultado.equals("false"));
    }



}

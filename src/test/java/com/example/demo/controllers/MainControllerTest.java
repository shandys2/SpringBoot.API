package com.example.demo.controllers;

import com.example.demo.modelos.ElementoListado;
import com.example.demo.security.AuthRequest;
import com.example.demo.security.AuthResponse;
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

import java.lang.reflect.Type;
import java.util.Collection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

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
            Gson gson = new Gson();
            AuthResponse authResponse = gson.fromJson(resultado, AuthResponse.class);
            token=authResponse.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDameListado() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = get("/main/dameListado?api=1");
        requestBuilder.header("Authorization", "Bearer " + this.token);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions;
        resultActions = this.mockMvc.perform(requestBuilder);
        resultActions.andDo(print());
        String resultado= resultActions.andReturn().getResponse().getContentAsString();
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ElementoListado>>(){}.getType();
        Collection<ElementoListado> coleccionElement = gson.fromJson(resultado, collectionType);
        Assert.assertTrue("la lista de elementos no esta vacia" , coleccionElement!=null);

    }

    @Test
    public void testValidadorDameListado() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = get("/main/dameListado?user_id=1");
        requestBuilder.header("Authorization", "Bearer " + this.token);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        ResultActions resultActions;
        resultActions = this.mockMvc.perform(requestBuilder);
        resultActions.andDo(print());
        String resultado= resultActions.andReturn().getResponse().getContentAsString();
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ElementoListado>>(){}.getType();
        Collection<ElementoListado> coleccionElement = gson.fromJson(resultado, collectionType);
        Assert.assertTrue("la lista de elementos esta vacia" , coleccionElement==null);

    }



}

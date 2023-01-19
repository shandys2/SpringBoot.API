package com.example.demo.controllers;

import com.example.demo.dto.ComentarioDTO;
import com.example.demo.security.AuthRequest;
import com.example.demo.security.AuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class ComentarioControllerTest {

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
    public void testCrearComentario(){
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setApp_id(1);
        comentarioDTO.setHora("22:22");
        comentarioDTO.setComment_text(" soy un comentario de test");

        comentarioDTO.setElemento_id("1");

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(comentarioDTO);
            MockHttpServletRequestBuilder requestBuilder = post("/comentario/crearComentario");
            requestBuilder.header("Authorization", "Bearer " + this.token);
            requestBuilder.content(jsonString);
            requestBuilder.contentType(MediaType.APPLICATION_JSON);
            ResultActions resultActions;
            resultActions = this.mockMvc.perform(requestBuilder);
            String resultado= resultActions.andReturn().getResponse().getContentAsString();
            Assert.assertTrue(resultado.equals("true"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @Transactional
    public void testCrearComentarioErrorApp(){
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setApp_id(5);
        comentarioDTO.setHora("22:22");
        comentarioDTO.setComment_text(" soy un comentario de test");

        comentarioDTO.setElemento_id("1");

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(comentarioDTO);
            MockHttpServletRequestBuilder requestBuilder = post("/comentario/crearComentario");
            requestBuilder.header("Authorization", "Bearer " + this.token);
            requestBuilder.content(jsonString);
            requestBuilder.contentType(MediaType.APPLICATION_JSON);
            ResultActions resultActions;
            resultActions = this.mockMvc.perform(requestBuilder);
            String resultado= resultActions.andReturn().getResponse().getContentAsString();
            Assert.assertTrue(resultado.equals("false"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

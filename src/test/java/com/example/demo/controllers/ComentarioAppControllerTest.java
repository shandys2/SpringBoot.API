package com.example.demo.controllers;
import com.example.demo.dto.ComentarioAppDTO;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.ComentarioApp;
import com.example.demo.modelos.Usuario;
import com.example.demo.repositories.AppRepository;
import com.example.demo.repositories.ComentarioAppRepository;
import com.example.demo.repositories.UsuarioRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
public class ComentarioAppControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ComentarioAppRepository comentarioAppRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AppRepository appRepository;

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

        ComentarioAppDTO comentarioAppDTO = new ComentarioAppDTO();
        comentarioAppDTO.setApp_id(1);
        comentarioAppDTO.setHora("22:22");
        comentarioAppDTO.setComment_text(" soy un comentario de test");
        comentarioAppDTO.setUser_id(1);


        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(comentarioAppDTO);
            MockHttpServletRequestBuilder requestBuilder = post("/comentariosApp/crearComentarioApp");
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

        ComentarioAppDTO comentarioAppDTO = new ComentarioAppDTO();
        comentarioAppDTO.setApp_id(999);
        comentarioAppDTO.setHora("22:22");
        comentarioAppDTO.setComment_text(" soy un comentario con errores");
        comentarioAppDTO.setUser_id(1);


        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(comentarioAppDTO);
            MockHttpServletRequestBuilder requestBuilder = post("/comentariosApp/crearComentarioApp");
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

    @Test
    @Transactional
    public void testCrearComentarioErrorUsuario(){

        ComentarioAppDTO comentarioAppDTO = new ComentarioAppDTO();
        comentarioAppDTO.setApp_id(1);
        comentarioAppDTO.setHora("22:22");
        comentarioAppDTO.setComment_text(" soy un comentario con errores");
        comentarioAppDTO.setUser_id(99);


        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(comentarioAppDTO);
            MockHttpServletRequestBuilder requestBuilder = post("/comentariosApp/crearComentarioApp");
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

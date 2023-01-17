package com.example.demo.controllers;

import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.security.AuthRequest;
import com.example.demo.security.AuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Before
	public void init() {
	 System.out.println("iniciando el before");
	}
	@Test
	public void testUsuarioRepo() {
		Assert.assertNotNull("usuarioRepository no es null " , usuarioRepository);
	}
	@Test
	@Transactional
	void testCrearUsuario() throws IOException, InterruptedException {
		Usuario usuario =new Usuario();
		usuario.setNombre("juan");
		usuario.setPassword("1");
		usuario.setEmail("juan@gmail.com");
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(usuario);
			MockHttpServletRequestBuilder requestBuilder = post("/auth/crearUsuario");
			requestBuilder.content(jsonString);
			requestBuilder.contentType(MediaType.APPLICATION_JSON);

			ResultActions resultActions;
			resultActions = this.mockMvc.perform(requestBuilder);
			resultActions.andDo(print());
			String resultado= resultActions.andReturn().getResponse().getContentAsString();
			Gson gson = new Gson();
			Usuario usuarioResponse = gson.fromJson(resultado, Usuario.class);
			Assert.assertTrue("el username existe ", usuarioResponse.getUsername().equals("juan"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testLogin() throws IOException, InterruptedException {
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
			resultActions.andDo(print());
			String resultado= resultActions.andReturn().getResponse().getContentAsString();
			Gson gson = new Gson(); // Or use new GsonBuilder().create();
			AuthResponse authResponse = gson.fromJson(resultado, AuthResponse.class);
			Assert.assertTrue("El token no es null", authResponse.getAccessToken()!=null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testValidadorCrearUsuario() throws IOException, InterruptedException {
		Usuario usuario =new Usuario();
		usuario.setNombre("");
		usuario.setPassword(null);
		usuario.setEmail("       ");
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(usuario);
			MockHttpServletRequestBuilder requestBuilder = post("/auth/crearUsuario");
			requestBuilder.content(jsonString);
			requestBuilder.contentType(MediaType.APPLICATION_JSON);
			ResultActions resultActions;
			resultActions = this.mockMvc.perform(requestBuilder);
			resultActions.andDo(print());
			String resultado= resultActions.andReturn().getResponse().getContentAsString();

			Gson gson = new Gson();
			Type collectionType = new TypeToken<Collection<ResponseEntity<Object>>>(){}.getType();
			Collection<Aplicacion> coleccionApps = gson.fromJson(resultado, collectionType);
			Assert.assertTrue("la lista de errores no esta vacia" , coleccionApps!=null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testValidadorLogin() throws IOException, InterruptedException {
		AuthRequest authRequest =new AuthRequest();
		authRequest.setUsername("");
		authRequest.setPassword(null);
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(authRequest);
			MockHttpServletRequestBuilder requestBuilder = post("/auth/login");
			requestBuilder.content(jsonString);
			requestBuilder.contentType(MediaType.APPLICATION_JSON);
			ResultActions resultActions;
			resultActions = this.mockMvc.perform(requestBuilder);
			resultActions.andDo(print());
			String resultado= resultActions.andReturn().getResponse().getContentAsString();


			Gson gson = new Gson();
			Type collectionType = new TypeToken<Collection<ResponseEntity<Object>>>(){}.getType();
			Collection<Aplicacion> coleccionApps = gson.fromJson(resultado, collectionType);
			Assert.assertTrue("la lista de errores no esta vacia" , coleccionApps!=null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Ejecutar el test con la base de datos vacia, sino intentara meter datos duplicados y no pasará el test

	/*@Test
	void testDemoData() throws IOException, InterruptedException {

		try {
			MockHttpServletRequestBuilder requestBuilder = get("/auth/demo");
			requestBuilder.contentType(MediaType.APPLICATION_JSON);
			ResultActions resultActions;
			resultActions = this.mockMvc.perform(requestBuilder);
			resultActions.andDo(print());
			String resultado= resultActions.andReturn().getResponse().getContentAsString();
			Assert.assertTrue(resultado.equals("datos cargados correctamente"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
}

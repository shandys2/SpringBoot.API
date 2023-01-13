package com.example.demo;

import com.example.demo.controllers.AuthController;
import com.example.demo.modelos.Usuario;
import com.example.demo.security.AuthRequest;
import com.example.demo.security.AuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.xml.crypto.Data;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testToken() throws IOException, InterruptedException {
		AuthRequest authRequest =new AuthRequest();
		authRequest.setUsername("eustaquio");
		authRequest.setPassword("a");
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
			Assert.assertTrue("El token no esta vacio", authResponse.getAccessToken()!=null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}

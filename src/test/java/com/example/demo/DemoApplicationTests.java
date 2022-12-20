package com.example.demo;

import com.example.demo.modelos.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootTest
class DemoApplicationTests {


	@Test
	void contextLoads() {
	}

	@Test
	void contextLoads1() throws IOException, InterruptedException {


		Usuario usuario =new Usuario();
		usuario.setNombre("shandys");
		usuario.setPassword("123");
		usuario.setEmail("lal2adsadfsadqwa@gmail.com");


		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody = objectMapper.writeValueAsString(usuario);

		HttpClient client = HttpClient.newHttpClient();


		HttpRequest request = HttpRequest.newBuilder()
										 .uri(URI.create("http://localhost:8080/crearUsuario"))
				                         .header("Content-Type", "application/json")
										 .POST(HttpRequest.BodyPublishers.ofString(requestBody))
				                         .build();

		HttpResponse<String> response = client.send(request,
				HttpResponse.BodyHandlers.ofString());


		System.out.println(response.body());
	}

}

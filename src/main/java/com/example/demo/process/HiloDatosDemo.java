package com.example.demo.process;

import com.example.demo.modelos.Usuario;
import com.example.demo.security.AuthRequest;
import com.example.demo.security.AuthResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.coyote.Request;



import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HiloDatosDemo extends Thread {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    StringBuilder resultado;

    @Override
    public void run() {

        URL url = null;

        try {
            url = new URL("http://localhost:8080/auth/demo");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if (con.getResponseCode() == 200) {
                con.disconnect();
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // form body
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("dani");
        authRequest.setPassword("1");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(authRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create("http://localhost:8080/auth/login"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Gson gson = new Gson();
        AuthResponse usuarioResponse = gson.fromJson(response.body(), AuthResponse.class);
        // print status code
        System.out.println(response.statusCode());
        // print response body
        System.out.println(response.body());

        HiloDatosPokemon cargarDatosPokemon = new HiloDatosPokemon(usuarioResponse.getAccessToken());
        cargarDatosPokemon.start();

    }
}

package com.example.demo.process;

import com.example.demo.security.AuthRequest;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HiloDatosPokemon extends Thread {

    StringBuilder resultado;
    String token;
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public HiloDatosPokemon(String token) {
        this.token = token;
    }

    @Override
    public void run() {

        URL url = null;

        try {
            url = new URL("http://localhost:8080/main/dameListado?api=2&item");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization","Bearer "+ token);
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


    }
}
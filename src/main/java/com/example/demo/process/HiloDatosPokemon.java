package com.example.demo.process;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.http.HttpClient;

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
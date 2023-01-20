package com.example.demo.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class CargarDatos extends Thread{


    StringBuilder resultado;

    @Override
    public void run(){
        URL url = null;
        try {
            url = new URL("http://localhost:8080/auth/demo");
            HttpURLConnection  con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
           /* if(con.getResponseCode()==200){
                rd.close();
            }*/
            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String linea;
            resultado = new StringBuilder();

            while ((linea = rd.readLine()) != null) {
                resultado.append(linea);
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

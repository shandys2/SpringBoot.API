package com.example.demo;


import com.example.demo.configuracion.WebConfig;
import com.example.demo.process.HiloDatosDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Import(WebConfig.class)
@SpringBootApplication
@ComponentScan({"com.example.demo"})
@EntityScan({"com.example.demo"})
@EnableJpaRepositories({"com.example.demo"})
public class API_SERVICE {

    public static void main(String[] args) {


		SpringApplication.run(API_SERVICE.class, args);

        HiloDatosDemo cargarDatosDemo = new HiloDatosDemo();

        try{ cargarDatosDemo.run();}
        catch (Exception e){
            System.out.println("Los datos ya estaban caragados");
        }
    }
}

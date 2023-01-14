package com.example.demo.security;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class AuthRequest {

    @NotBlank(message = "El username no puede ser nulo, cadena vacia o espacios en blanco")
    String username;
    @NotBlank(message = "El password no puede ser nulo, cadena vacia o espacios en blanco")
    String password;

    public AuthRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

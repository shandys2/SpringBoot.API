package com.example.demo.controllers;

import com.example.demo.modelos.Usuario;
import com.example.demo.repositories.DemoDataRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.security.AuthRequest;
import com.example.demo.security.AuthResponse;
import com.example.demo.security.JwtTokenUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtUtil;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    DemoDataRepository demoDataRepository;

    @PostMapping(value = "/crearUsuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Usuario crearUsuario(@RequestBody @Valid Usuario usuario) {

        usuarioRepository.insertarUsuario(usuario);
        return usuario;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {

        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword())
            );

            Usuario user = (Usuario) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getId(), user.getUsername(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/demo")
    public String cargarDatos() throws IOException {
        try {
            demoDataRepository.cargarDatosPrueba();
            return "datos cargados correctamente";
        } catch (Exception exception) {
            return "Hubo un probleme con la carga de datos de prueba     :" + exception.getLocalizedMessage();
        }

    }

}

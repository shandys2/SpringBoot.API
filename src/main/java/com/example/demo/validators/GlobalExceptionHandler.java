package com.example.demo.validators;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, Map<String, Object>> respuesta = new HashMap<>();
        List<Map<String, Object>> res = new ArrayList<>();
        List<ObjectError> listaErrores = ex.getAllErrors();
        System.out.println("*************       YA SE ESTA LIANDO!!!       ***************");
        int i = 0;
        for (ObjectError error : listaErrores) {
            FieldError fieldError = (FieldError) error;
            Map<String, Object> mapError = new HashMap<>();
            mapError.put("objeto", fieldError.getObjectName());
            mapError.put("mensaje", fieldError.getDefaultMessage());
            mapError.put("valorEnviado", fieldError.getRejectedValue());
            mapError.put("campo", fieldError.getField());
            mapError.put("codigo", fieldError.getCodes()[0]);
            respuesta.put(String.valueOf(i), mapError);
            res.add(mapError);
            i++;
            System.out.println("ERROR  -> " + " OBJETO :  " + fieldError.getObjectName() + "    MENSAJE : " + fieldError.getDefaultMessage() + "    VALOR ENVIADO : " + fieldError.getRejectedValue());
        }

        System.out.println("*************       FIN REGISTRO ERROR       ***************");
        return new ResponseEntity<>(res, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
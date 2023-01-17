package com.example.demo.validators;

import com.example.demo.modelos.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UsuarioValidator {

    @Autowired
    UsuarioRepository usuarioRepository;

    public boolean esUsuarioValido(int user_id){

            Usuario usuario =usuarioRepository.getUsuario(user_id);
            if(usuario==null){
                return false;
            }
            if(usuario.getId()==null)
              return false;
            else
              return true;



    }

}

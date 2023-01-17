package com.example.demo.validators;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AppValidator {

    public boolean esAppValida(int id){
        if(id == 1 || id == 2 || id == 3){
           return true;
        }else {
            return false;
        }

    }
}

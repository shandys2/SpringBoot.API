package com.example.demo.repositories;

import com.example.demo.daos.AplicacionDao;
import com.example.demo.modelos.Aplicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppRepository {

    @Autowired
    AplicacionDao aplicacionDao;

    public Object insertarApp(Aplicacion app){
        aplicacionDao.save(app);
        return "";
    }

    public Aplicacion getApp(int id){
        Aplicacion app= aplicacionDao.getReferenceById(id);
        return app;
    }

}

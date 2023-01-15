package com.example.demo.repositories;

import com.example.demo.daos.AplicacionDao;
import com.example.demo.dto.AplicacionDTO;
import com.example.demo.dto.ComentarioAppMin;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.ComentarioApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AppRepository {

    @Autowired
    AplicacionDao aplicacionDao;
    @Autowired
    ComentarioAppRepository comentarioAppRepository;

    public Object insertarApp(Aplicacion app) {
        aplicacionDao.save(app);
        return "";
    }
    public Aplicacion getApp(Integer id) {
        Aplicacion app = aplicacionDao.getReferenceById(id);
        return app;
    }
    public AplicacionDTO getAppWithComments(int id) {

        Aplicacion app = aplicacionDao.getReferenceById(id);
        List<ComentarioApp> listaComentariosApp= comentarioAppRepository.getComentariosApp(app);
        List<ComentarioAppMin> listaComentariosMin=new ArrayList<>();
        for (ComentarioApp comentario: listaComentariosApp) {
            ComentarioAppMin comentarioAppMin = new ComentarioAppMin();
            comentarioAppMin.setHora(comentario.getHora());
            comentarioAppMin.setUsername(comentario.getUser_id().getUsername());
            comentarioAppMin.setComment_text(comentario.getComment_text());
            listaComentariosMin.add(comentarioAppMin);
        }
        AplicacionDTO appDTO =new AplicacionDTO(app.getApp_id(),app.getNombre(),app.getMediaPuntos(),app.getDescripcion(),listaComentariosMin);

        return appDTO;
    }
    public List<Aplicacion> getAllApps() {

        List<Aplicacion> listaAplicaciones= (List<Aplicacion>) aplicacionDao.getAllApps();

        return listaAplicaciones;
    }
}

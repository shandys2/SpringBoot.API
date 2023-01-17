package com.example.demo.repositories;

import com.example.demo.daos.AplicacionDao;
import com.example.demo.dto.AplicacionDTO;
import com.example.demo.dto.ComentarioMin;
import com.example.demo.modelos.Aplicacion;
import com.example.demo.modelos.ComentarioApp;
import com.example.demo.validators.AppValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
    @Autowired
    RankingAppRepository rankingAppRepository;


    public Object insertarApp(Aplicacion app) {
        aplicacionDao.save(app);
        return "";
    }

    public Aplicacion getApp(Integer id) {
        Aplicacion app = aplicacionDao.getReferenceById(id);
        return app;
    }

    public AplicacionDTO getAppWithComments(int id) throws JsonProcessingException {

        Aplicacion app = aplicacionDao.getReferenceById(id);

        List<ComentarioApp> listaComentariosApp = comentarioAppRepository.getComentariosApp(app);
        List<ComentarioMin> listaComentariosMin = new ArrayList<>();
        for (ComentarioApp comentario : listaComentariosApp) {
            ComentarioMin comentarioAppMin = new ComentarioMin();
            comentarioAppMin.setHora(comentario.getHora());
            comentarioAppMin.setUsername(comentario.getUser_id().getUsername());
            comentarioAppMin.setComment_text(comentario.getComment_text());
            listaComentariosMin.add(comentarioAppMin);
        }

        Object object = rankingAppRepository.mediaApp(id);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);
        json = json.replaceAll("]", "").replaceAll("\\[", "");
        String[] tupla = json.split(",");

        AplicacionDTO appDTO = new AplicacionDTO(app.getApp_id(), app.getNombre(), app.getMediaPuntos(), app.getDescripcion(), listaComentariosMin);

        appDTO.setMediaPuntos(Double.parseDouble(tupla[0]));

        return appDTO;
    }

    public List<Aplicacion> getAllApps() throws JsonProcessingException {

        List<Object> o = rankingAppRepository.mediaApps();
        List<Aplicacion> listaAplicaciones =  aplicacionDao.getAllApps();

        for (int i = 0; i < listaAplicaciones.size(); i++) {
            Object fila = o.get(i);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(fila);
            json = json.replaceAll("]", "").replaceAll("\\[", "");
            String[] tupla = json.split(",");
            //  MediaPuntosDTO mediaPuntosDTO = (MediaPuntosDTO) fila;
            listaAplicaciones.get(i).setMediaPuntos(Double.parseDouble(tupla[0]));
            aplicacionDao.save(listaAplicaciones.get(i));
        }

        return listaAplicaciones;
    }


}

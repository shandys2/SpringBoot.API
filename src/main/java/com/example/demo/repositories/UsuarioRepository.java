package com.example.demo.repositories;

import com.example.demo.daos.UsuarioDao;
import com.example.demo.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioRepository {

    @Autowired
    UsuarioDao usuarioDao;

    public Object insertarUsuario(Usuario usuario) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuarioDao.save(usuario);

        return "";
    }

    public Usuario getUsuario(Integer id) {
        try{
            Usuario usuario = usuarioDao.getUsuarioById(id);
            return usuario;
        }catch (Exception e){
            return new Usuario();
        }


    }
    public Boolean isValid(Integer id) {

        Usuario usuario = usuarioDao.getReferenceById(id);
        if(usuario.getId()==null)
        return false;
        else
            return true;

    }


    public Usuario getUsuarioByName(String nombre) throws Exception {
        Usuario usuario = null;
        try {
            usuario = usuarioDao.getUsuarioByName(nombre);
        } catch (Exception e) {
            throw new Exception();
        }
        return usuario;
    }

    public void borrarUsuario(Integer id) throws Exception {
        try {
            usuarioDao.deleteById(id);
        } catch (Exception e) {
            throw new Exception();
        }
    }
}

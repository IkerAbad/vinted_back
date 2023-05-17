package com.example.ad_vinted_api.service;

import com.example.ad_vinted_api.domain.Usuario;
import com.example.ad_vinted_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService extends BaseService<Usuario,Long, UsuarioRepository>{
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void deleteById(Long id) {
        if (findById(id).isPresent()){
            usuarioRepository.deleteById(id);
        }else {
            System.out.println("La peticion no existe");
        }
    }

    public Usuario updateUserById(Usuario nusuario) {
        Optional<Usuario> usuario= null;
        try{
            usuario= usuarioRepository.findById(usuario.get().getId());
            if (usuario.isPresent()){
                usuarioRepository.save(nusuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario.get();
    }

    public Usuario addUsuario(Usuario nUsuario){
        Usuario usuario=nUsuario;
        usuarioRepository.save(usuario);
        return usuario;
    }
}

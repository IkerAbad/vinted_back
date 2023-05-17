package com.example.ad_vinted_api.controller;

import com.example.ad_vinted_api.domain.Usuario;
import com.example.ad_vinted_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins= "*")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUser(){
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        Optional<Usuario> usuario= usuarioService.findById(id);
        return new ResponseEntity<>(usuario.get(),HttpStatus.OK);
    }
    @PostMapping("/usuarios/add")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario nUsuario){
        return new ResponseEntity<>(usuarioService.addUsuario(nUsuario),HttpStatus.OK);
    }

    @PutMapping("/usuarios/update")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuarioActualizar){
        return new ResponseEntity<>(usuarioService.updateUserById(usuarioActualizar),HttpStatus.OK);
    }
    @DeleteMapping("usuarios/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        usuarioService.deleteById(id);
    }
}

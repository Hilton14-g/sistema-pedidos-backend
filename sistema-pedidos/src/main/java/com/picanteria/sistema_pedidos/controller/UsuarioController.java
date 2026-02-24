package com.picanteria.sistema_pedidos.controller;

import com.picanteria.sistema_pedidos.model.Usuario;
import com.picanteria.sistema_pedidos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login(@RequestParam String user, @RequestParam String pass) {
        return usuarioRepository.findByUsername(user)
                .filter(u -> u.getPassword().equals(pass))
                .map(u -> "Bienvenido " + u.getNombreCompleto() + " (" + u.getRol() + ")")
                .orElse("Usuario o clave incorrectos");
    }
}
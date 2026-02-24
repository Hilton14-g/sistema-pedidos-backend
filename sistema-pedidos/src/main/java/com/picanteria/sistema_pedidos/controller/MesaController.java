package com.picanteria.sistema_pedidos.controller;

import com.picanteria.sistema_pedidos.model.Mesa;
import com.picanteria.sistema_pedidos.service.MesaService;
import com.picanteria.sistema_pedidos.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin(origins = "*", allowedHeaders = "*") // <--- PERMITE TODO PARA QUE FUNCIONE EN CUALQUER RED
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public List<Mesa> obtenerMesas() {
        return mesaRepository.findAll();
    }

    @GetMapping("/{id}/ocupar")
    public String ocuparMesa(@PathVariable Integer id) {
        return mesaService.cambiarEstadoMesa(id, "OCUPADA");
    }

    @GetMapping("/{id}/liberar")
    public String liberarMesa(@PathVariable Integer id) {
        return mesaService.cambiarEstadoMesa(id, "LIBRE");
    }
}
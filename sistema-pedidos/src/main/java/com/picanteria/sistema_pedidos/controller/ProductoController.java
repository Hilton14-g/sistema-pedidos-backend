package com.picanteria.sistema_pedidos.controller;

import com.picanteria.sistema_pedidos.model.Producto;
import com.picanteria.sistema_pedidos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*", allowedHeaders = "*") // <--- IMPORTANTE para que el celular cargue los platos
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }
}
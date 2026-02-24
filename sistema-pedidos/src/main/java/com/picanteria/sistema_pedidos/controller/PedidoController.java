package com.picanteria.sistema_pedidos.controller;

import com.picanteria.sistema_pedidos.model.Pedido;
import com.picanteria.sistema_pedidos.model.DetallePedido;
import com.picanteria.sistema_pedidos.repository.PedidoRepository;
import com.picanteria.sistema_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*") // <--- CORREGIDO: Ahora permite pedidos desde celulares
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping("/crear") // <--- Esta es la ruta: /api/pedidos/crear
    public Pedido crearPedido(@RequestBody PedidoRequest request) {
        return pedidoService.crearPedido(request.getPedido(), request.getDetalles());
    }

    @GetMapping("/pendientes")
    public List<Pedido> listarPendientes() {
        return pedidoRepository.findAll().stream()
                .filter(p -> "PENDIENTE".equals(p.getEstado()))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/listo")
    public Pedido marcarComoListo(@PathVariable Integer id) {
        return pedidoService.marcarComoListo(id);
    }

    public static class PedidoRequest {
        private Pedido pedido;
        private List<DetallePedido> detalles;
        public Pedido getPedido() { return pedido; }
        public void setPedido(Pedido pedido) { this.pedido = pedido; }
        public List<DetallePedido> getDetalles() { return detalles; }
        public void setDetalles(List<DetallePedido> detalles) { this.detalles = detalles; }
    }
}
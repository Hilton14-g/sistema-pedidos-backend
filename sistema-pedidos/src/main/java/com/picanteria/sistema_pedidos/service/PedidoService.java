package com.picanteria.sistema_pedidos.service;

import com.picanteria.sistema_pedidos.model.*;
import com.picanteria.sistema_pedidos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private MesaRepository mesaRepository;

    @Transactional
    public Pedido crearPedido(Pedido pedido, List<DetallePedido> detalles) {
        Mesa mesa = mesaRepository.findById(pedido.getMesa().getId())
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        mesa.setEstado("OCUPADA");
        mesaRepository.save(mesa);

        pedido.setMesa(mesa);
        pedido.setEstado("PENDIENTE");
        double total = 0;
        Pedido nuevoPedido = pedidoRepository.save(pedido);

        for (DetallePedido detalle : detalles) {
            Producto prod = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            detalle.setPedido(nuevoPedido);
            detalle.setPrecioUnitario(prod.getPrecio());
            total += (detalle.getPrecioUnitario() * detalle.getCantidad());
            detallePedidoRepository.save(detalle);
        }

        nuevoPedido.setTotal(total);
        return pedidoRepository.save(nuevoPedido);
    }

    @Transactional
    public Pedido marcarComoListo(Integer pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setEstado("LISTO");
        return pedidoRepository.save(pedido);
    }
}
package com.picanteria.sistema_pedidos.repository;

import com.picanteria.sistema_pedidos.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    // Esto nos servirá para listar todos los platos de un pedido (para la cocina)
    List<DetallePedido> findByPedidoId(Integer pedidoId);
}
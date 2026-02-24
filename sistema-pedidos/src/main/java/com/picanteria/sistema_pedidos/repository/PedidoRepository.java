package com.picanteria.sistema_pedidos.repository;

import com.picanteria.sistema_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    // Esto nos servirá luego para ver qué pedidos tiene una mesa específica
    List<Pedido> findByMesaId(Integer mesaId);
}
package com.picanteria.sistema_pedidos.service;

import com.picanteria.sistema_pedidos.model.Mesa;
import com.picanteria.sistema_pedidos.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public String cambiarEstadoMesa(Integer idMesa, String nuevoEstado) {
        Optional<Mesa> oMesa = mesaRepository.findById(idMesa);
        if (oMesa.isPresent()) {
            Mesa mesa = oMesa.get();
            mesa.setEstado(nuevoEstado);
            mesaRepository.save(mesa);
            return "La Mesa " + idMesa + " ahora esta " + nuevoEstado;
        }
        return "Error: Mesa no encontrada";
    }
}
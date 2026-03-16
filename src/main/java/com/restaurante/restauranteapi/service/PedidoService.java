package com.restaurante.restauranteapi.service;

import com.restaurante.restauranteapi.model.Pedido;
import com.restaurante.restauranteapi.model.Pedidoplato;
import com.restaurante.restauranteapi.model.Plato;
import com.restaurante.restauranteapi.repository.PedidoPlatoRepository;
import com.restaurante.restauranteapi.repository.PedidoRepository;
import com.restaurante.restauranteapi.repository.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private PedidoPlatoRepository pedidoPlatoRepository;

    public List<Pedido> getAllPedidos() {
        List<Pedido> lista = pedidoRepository.findAll();
        return lista.isEmpty() ? new ArrayList<>() : lista;
    }

    public Pedido getPedidoById(Long id) throws Exception {
        return pedidoRepository.findById(id).orElseThrow(() -> new Exception("Pedido no encontrado"));
    }

    public Pedido createPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(Long id, Pedido pedido) throws Exception {
        Optional<Pedido> optional = pedidoRepository.findById(id);
        if(optional.isPresent()){
            Pedido existing = optional.get();
            existing.setEstado(pedido.getEstado());
            existing.setCliente(pedido.getCliente());
            return pedidoRepository.save(existing);
        } else {
            throw new Exception("Pedido no encontrado");
        }
    }

    public void deletePedido(Long id) throws Exception {
        Optional<Pedido> optional = pedidoRepository.findById(id);
        if(optional.isPresent()){
            pedidoRepository.delete(optional.get());
        } else {
            throw new Exception("Pedido no encontrado");
        }
    }

    public List<Pedido> getPedidosPorCliente(Long clienteId){
        List<Pedido> lista = pedidoRepository.findPedidosByCliente(clienteId);
        return lista.isEmpty() ? new ArrayList<>() : lista;
    }


    public Pedido addPlatoToPedido(Long pedidoId, Long platoId, int cantidad) throws Exception {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new Exception("Pedido no encontrado"));

        Plato plato = platoRepository.findById(platoId).orElseThrow(() -> new Exception("Plato no encontrado"));

        Pedidoplato pedidoplato = new Pedidoplato();
        pedidoplato.setPedido(pedido);
        pedidoplato.setPlato(plato);
        pedidoplato.setCantidad(cantidad);

        pedido.getPedidoplatoes().add(pedidoplato);

        BigDecimal precioPlatoTotal = plato.getPrecio().multiply(BigDecimal.valueOf(cantidad));
        pedido.setPrecioTotal(pedido.getPrecioTotal().add(precioPlatoTotal));

        pedidoPlatoRepository.save(pedidoplato);
        return pedidoRepository.save(pedido);
    }
}
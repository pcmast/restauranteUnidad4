package com.restaurante.restauranteapi.repository;

import com.restaurante.restauranteapi.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("SELECT p FROM Pedido p JOIN p.cliente c WHERE c.id = :clienteId")
    List<Pedido> findPedidosByCliente(@Param("clienteId") Long clienteId);
}

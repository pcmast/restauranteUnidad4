package com.restaurante.restauranteapi.repository;

import com.restaurante.restauranteapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c WHERE c.email LIKE %:email%")
    List<Cliente> findByEmailLike(@Param("email") String email);

    @Query("SELECT DISTINCT c FROM Cliente c JOIN c.pedidos p")
    List<Cliente> findClientesConPedidos();


}

package com.restaurante.restauranteapi.repository;

import com.restaurante.restauranteapi.model.Plato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatoRepository extends JpaRepository<Plato, Long> {
}

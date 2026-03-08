package com.restaurante.restauranteapi.repository;

import com.restaurante.restauranteapi.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}

package com.restaurante.restauranteapi.repository;

import com.restaurante.restauranteapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

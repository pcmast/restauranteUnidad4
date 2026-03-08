package com.restaurante.restauranteapi.repository;

import com.restaurante.restauranteapi.model.PlatoIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlatoIngredienteRepository extends JpaRepository<PlatoIngrediente, Long> {
    @Query("SELECT pi FROM PlatoIngrediente pi WHERE pi.ingrediente.id = :ingredienteId")
    List<PlatoIngrediente> findByIngredienteId(@Param("ingredienteId") Long ingredienteId);

    @Query("SELECT pi FROM PlatoIngrediente pi WHERE pi.plato.id = :platoId")
    List<PlatoIngrediente> findByPlatoId(@Param("platoId") Long platoId);
}
package com.restaurante.restauranteapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "plato_ingrediente")
public class PlatoIngrediente {
    @EmbeddedId
    private PlatoIngredienteId id;

    @MapsId("platoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "plato_id", nullable = false)
    private Plato plato;

    @MapsId("ingredienteId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Ingrediente ingrediente;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

}
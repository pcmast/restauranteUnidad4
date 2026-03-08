package com.restaurante.restauranteapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PlatoIngredienteId implements Serializable {
    private static final long serialVersionUID = -6283979788580762528L;
    @NotNull
    @Column(name = "plato_id", nullable = false)
    private Long platoId;

    @NotNull
    @Column(name = "ingrediente_id", nullable = false)
    private Long ingredienteId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlatoIngredienteId entity = (PlatoIngredienteId) o;
        return Objects.equals(this.ingredienteId, entity.ingredienteId) &&
                Objects.equals(this.platoId, entity.platoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredienteId, platoId);
    }

}
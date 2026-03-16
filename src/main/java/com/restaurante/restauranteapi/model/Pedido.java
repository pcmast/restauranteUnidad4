package com.restaurante.restauranteapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "pedido")
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private Instant fecha;

    @NotNull
    @Column(name = "estado", nullable = false)
    private String estado;

    @NotNull
    @Column(name = "precio_Total", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioTotal;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    @JsonIgnore
    private Set<Pedidoplato> pedidoplatoes = new LinkedHashSet<>();

    public Pedido() {
        this.fecha = Instant.now();
        this.estado = "PENDIENTE";
        this.precioTotal = BigDecimal.ZERO;
    }
}
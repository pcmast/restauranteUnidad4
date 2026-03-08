package com.restaurante.restauranteapi.controller;
import com.restaurante.restauranteapi.model.Plato;
import com.restaurante.restauranteapi.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platos")
public class PlatoController {
    @Autowired
    private PlatoService platoService;

    @GetMapping
    public ResponseEntity<List<Plato>> getAllPlatos() {
        return ResponseEntity.ok(platoService.getAllPlatos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plato> getPlatoById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(platoService.getPlatoById(id));
    }

    @PostMapping
    public ResponseEntity<Plato> createPlato(@RequestBody Plato plato) {
        return ResponseEntity.status(HttpStatus.CREATED).body(platoService.createPlato(plato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plato> updatePlato(@PathVariable Long id, @RequestBody Plato plato) throws Exception {
        return ResponseEntity.ok(platoService.updatePlato(id, plato));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletePlato(@PathVariable Long id) throws Exception {
        platoService.deletePlato(id);
        return HttpStatus.ACCEPTED;
    }

    @PostMapping("/{platoId}/ingredientes/{ingredienteId}")
    public ResponseEntity<Plato> addIngredienteToPlato(@PathVariable Long platoId, @PathVariable Long ingredienteId, @RequestParam int cantidad) throws Exception {
        return ResponseEntity.ok(platoService.addIngredienteToPlato(platoId, ingredienteId, cantidad));
    }

    @GetMapping("/ingrediente/{ingredienteId}")
    public ResponseEntity<List<Plato>> getPlatosPorIngrediente(@PathVariable Long ingredienteId) {
        return ResponseEntity.ok(platoService.getPlatosPorIngrediente(ingredienteId));
    }
}
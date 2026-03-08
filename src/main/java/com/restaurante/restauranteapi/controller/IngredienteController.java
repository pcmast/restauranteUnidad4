package com.restaurante.restauranteapi.controller;
import com.restaurante.restauranteapi.model.Ingrediente;
import com.restaurante.restauranteapi.model.Plato;
import com.restaurante.restauranteapi.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public ResponseEntity<List<Ingrediente>> getAllIngredientes() {
        return ResponseEntity.ok(ingredienteService.getAllIngredientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> getIngredienteById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(ingredienteService.getIngredienteById(id));
    }

    @PostMapping
    public ResponseEntity<Ingrediente> createIngrediente(@RequestBody Ingrediente ingrediente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredienteService.createIngrediente(ingrediente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> updateIngrediente(@PathVariable Long id, @RequestBody Ingrediente ingrediente) throws Exception {
        return ResponseEntity.ok(ingredienteService.updateIngrediente(id, ingrediente));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteIngrediente(@PathVariable Long id) throws Exception {
        ingredienteService.deleteIngrediente(id);
        return HttpStatus.ACCEPTED;
    }

    @PostMapping("/{ingredienteId}/platos/{platoId}")
    public ResponseEntity<Plato> addIngredienteToPlato(@PathVariable Long platoId, @PathVariable Long ingredienteId, @RequestParam int cantidad) throws Exception {
        Plato plato = ingredienteService.addIngredienteToPlato(platoId, ingredienteId, cantidad);
        return ResponseEntity.ok(plato);
    }

    @GetMapping("/{ingredienteId}/platos")
    public ResponseEntity<List<Plato>> getPlatosPorIngrediente(@PathVariable Long ingredienteId) {
        return ResponseEntity.ok(ingredienteService.getPlatosPorIngrediente(ingredienteId));
    }
}

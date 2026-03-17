package com.restaurante.restauranteapi.controller;
import com.restaurante.restauranteapi.model.Ingrediente;
import com.restaurante.restauranteapi.model.Plato;
import com.restaurante.restauranteapi.service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtener todos los ingredientes", description = "Devuelve la lista completa de ingredientes")
    @ApiResponse(responseCode = "200", description = "Lista de ingredientes obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<Ingrediente>> getAllIngredientes() {
        return ResponseEntity.ok(ingredienteService.getAllIngredientes());
    }

    @Operation(summary = "Obtener ingrediente por ID", description = "Devuelve un ingrediente específico según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingrediente encontrado"),
            @ApiResponse(responseCode = "404", description = "Ingrediente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> getIngredienteById(@Parameter(description = "ID del ingrediente a buscar") @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(ingredienteService.getIngredienteById(id));
    }

    @Operation(summary = "Crear un nuevo ingrediente", description = "Agrega un ingrediente a la base de datos")
    @ApiResponse(responseCode = "201", description = "Ingrediente creado correctamente")
    @PostMapping
    public ResponseEntity<Ingrediente> createIngrediente(@Parameter(description = "Objeto ingrediente a crear") @RequestBody Ingrediente ingrediente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredienteService.createIngrediente(ingrediente));
    }

    @Operation(summary = "Actualizar ingrediente existente", description = "Actualiza un ingrediente según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingrediente actualizado"),
            @ApiResponse(responseCode = "404", description = "Ingrediente no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> updateIngrediente(
            @Parameter(description = "ID del ingrediente a actualizar")
            @PathVariable Long id,
            @Parameter(description = "Objeto ingrediente con nuevos datos")
            @RequestBody Ingrediente ingrediente) throws Exception {
        return ResponseEntity.ok(ingredienteService.updateIngrediente(id, ingrediente));
    }

    @Operation(summary = "Eliminar ingrediente por ID", description = "Elimina un ingrediente según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Ingrediente eliminado"),
            @ApiResponse(responseCode = "404", description = "Ingrediente no encontrado")
    })
    @DeleteMapping("/{id}")
    public HttpStatus deleteIngrediente(@Parameter(description = "ID del ingrediente a eliminar") @PathVariable Long id) throws Exception {
        ingredienteService.deleteIngrediente(id);
        return HttpStatus.ACCEPTED;
    }

    @Operation(summary = "Agregar ingrediente a un plato", description = "Añade un ingrediente a un plato específico indicando la cantidad")
    @ApiResponse(responseCode = "200", description = "Ingrediente agregado al plato correctamente")
    @PostMapping("/{ingredienteId}/platos/{platoId}")
    public ResponseEntity<Plato> addIngredienteToPlato(
            @Parameter(description = "ID del plato") @PathVariable Long platoId,
            @Parameter(description = "ID del ingrediente") @PathVariable Long ingredienteId,
            @Parameter(description = "Cantidad del ingrediente a agregar") @RequestParam int cantidad) throws Exception {
        Plato plato = ingredienteService.addIngredienteToPlato(platoId, ingredienteId, cantidad);
        return ResponseEntity.ok(plato);
    }

    @Operation(summary = "Obtener platos por ingrediente", description = "Devuelve la lista de platos que contienen un ingrediente específico")
    @ApiResponse(responseCode = "200", description = "Lista de platos obtenida correctamente")
    @GetMapping("/{ingredienteId}/platos")
    public ResponseEntity<List<Plato>> getPlatosPorIngrediente(@Parameter(description = "ID del ingrediente") @PathVariable Long ingredienteId) {
        return ResponseEntity.ok(ingredienteService.getPlatosPorIngrediente(ingredienteId));
    }
}
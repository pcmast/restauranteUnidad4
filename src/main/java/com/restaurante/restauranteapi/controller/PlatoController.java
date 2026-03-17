package com.restaurante.restauranteapi.controller;
import com.restaurante.restauranteapi.model.Plato;
import com.restaurante.restauranteapi.service.PlatoService;
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
@RequestMapping("/platos")
public class PlatoController {

    @Autowired
    private PlatoService platoService;

    @Operation(summary = "Obtener todos los platos", description = "Devuelve la lista completa de platos")
    @ApiResponse(responseCode = "200", description = "Lista de platos obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<Plato>> getAllPlatos() {
        return ResponseEntity.ok(platoService.getAllPlatos());
    }

    @Operation(summary = "Obtener plato por ID", description = "Devuelve un plato específico según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plato encontrado"),
            @ApiResponse(responseCode = "404", description = "Plato no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Plato> getPlatoById(@Parameter(description = "ID del plato a buscar") @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(platoService.getPlatoById(id));
    }

    @Operation(summary = "Crear un nuevo plato", description = "Agrega un plato a la base de datos")
    @ApiResponse(responseCode = "201", description = "Plato creado correctamente")
    @PostMapping
    public ResponseEntity<Plato> createPlato(@Parameter(description = "Objeto Plato a crear") @RequestBody Plato plato) {
        return ResponseEntity.status(HttpStatus.CREATED).body(platoService.createPlato(plato));
    }

    @Operation(summary = "Actualizar plato existente", description = "Actualiza un plato según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plato actualizado"),
            @ApiResponse(responseCode = "404", description = "Plato no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Plato> updatePlato(
            @Parameter(description = "ID del plato a actualizar")
            @PathVariable Long id,
            @Parameter(description = "Objeto Plato con los datos actualizados")
            @RequestBody Plato plato) throws Exception {
        return ResponseEntity.ok(platoService.updatePlato(id, plato));
    }

    @Operation(summary = "Eliminar plato por ID", description = "Elimina un plato según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Plato eliminado"),
            @ApiResponse(responseCode = "404", description = "Plato no encontrado")
    })
    @DeleteMapping("/{id}")
    public HttpStatus deletePlato(@Parameter(description = "ID del plato a eliminar") @PathVariable Long id) throws Exception {
        platoService.deletePlato(id);
        return HttpStatus.ACCEPTED;
    }

    @Operation(summary = "Agregar ingrediente a un plato", description = "Añade un ingrediente a un plato específico indicando la cantidad")
    @ApiResponse(responseCode = "200", description = "Ingrediente agregado al plato correctamente")
    @PostMapping("/{platoId}/ingredientes/{ingredienteId}")
    public ResponseEntity<Plato> addIngredienteToPlato(
            @Parameter(description = "ID del plato") @PathVariable Long platoId,
            @Parameter(description = "ID del ingrediente") @PathVariable Long ingredienteId,
            @Parameter(description = "Cantidad del ingrediente a agregar") @RequestParam int cantidad) throws Exception {
        return ResponseEntity.ok(platoService.addIngredienteToPlato(platoId, ingredienteId, cantidad));
    }

    @Operation(summary = "Obtener platos por ingrediente", description = "Devuelve la lista de platos que contienen un ingrediente específico")
    @ApiResponse(responseCode = "200", description = "Lista de platos obtenida correctamente")
    @GetMapping("/ingrediente/{ingredienteId}")
    public ResponseEntity<List<Plato>> getPlatosPorIngrediente(@Parameter(description = "ID del ingrediente") @PathVariable Long ingredienteId) {
        return ResponseEntity.ok(platoService.getPlatosPorIngrediente(ingredienteId));
    }
}
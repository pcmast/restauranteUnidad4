package com.restaurante.restauranteapi.controller;

import com.restaurante.restauranteapi.model.Categoria;
import com.restaurante.restauranteapi.service.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Obtener todas las categorías", description = "Devuelve la lista completa de categorías")
    @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.getAllCategorias());
    }

    @Operation(summary = "Obtener categoría por ID", description = "Devuelve una categoría específica según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@Parameter(description = "ID de la categoría a obtener") @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(categoriaService.getCategoriaById(id));
    }

    @Operation(summary = "Crear una nueva categoría")
    @ApiResponse(responseCode = "201", description = "Categoría creada correctamente")
    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@Parameter(description = "Objeto categoría a crear") @RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.createCategoria(categoria));
    }

    @Operation(summary = "Actualizar categoría existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoría actualizada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@Parameter(description = "ID de la categoría a actualizar") @PathVariable Long id,
            @Parameter(description = "Objeto categoría con nuevos datos")
            @RequestBody Categoria categoria) throws Exception {
        return ResponseEntity.ok(categoriaService.updateCategoria(id, categoria));
    }

    @Operation(summary = "Eliminar categoría por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Categoría eliminada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @DeleteMapping("/{id}")
    public HttpStatus deleteCategoria(@Parameter(description = "ID de la categoría a eliminar") @PathVariable Long id) throws Exception {
        categoriaService.deleteCategoria(id);
        return HttpStatus.ACCEPTED;
    }
}
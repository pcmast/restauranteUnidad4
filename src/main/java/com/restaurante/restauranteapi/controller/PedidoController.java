package com.restaurante.restauranteapi.controller;

import com.restaurante.restauranteapi.model.Pedido;
import com.restaurante.restauranteapi.service.PedidoService;
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
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Obtener todos los pedidos", description = "Devuelve la lista completa de pedidos")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos(){
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @Operation(summary = "Obtener pedido por ID", description = "Devuelve un pedido específico según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@Parameter(description = "ID del pedido a buscar") @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(pedidoService.getPedidoById(id));
    }

    @Operation(summary = "Crear un nuevo pedido", description = "Registra un pedido en la base de datos")
    @ApiResponse(responseCode = "201", description = "Pedido creado correctamente")
    @PostMapping
    public ResponseEntity<Pedido> createPedido(@Parameter(description = "Objeto pedido a crear") @RequestBody Pedido pedido){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.createPedido(pedido));
    }

    @Operation(summary = "Actualizar pedido existente", description = "Actualiza un pedido según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido actualizado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(
            @Parameter(description = "ID del pedido a actualizar")
            @PathVariable Long id,
            @Parameter(description = "Objeto pedido con los datos actualizados")
            @RequestBody Pedido pedido) throws Exception {
        return ResponseEntity.ok(pedidoService.updatePedido(id, pedido));
    }

    @Operation(summary = "Eliminar pedido por ID", description = "Elimina un pedido según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Pedido eliminado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @DeleteMapping("/{id}")
    public HttpStatus deletePedido(@Parameter(description = "ID del pedido a eliminar") @PathVariable Long id) throws Exception {
        pedidoService.deletePedido(id);
        return HttpStatus.ACCEPTED;
    }

    @Operation(summary = "Obtener pedidos por cliente", description = "Devuelve la lista de pedidos de un cliente específico")
    @ApiResponse(responseCode = "200", description = "Pedidos del cliente obtenidos correctamente")
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> getPedidosPorCliente(@Parameter(description = "ID del cliente") @PathVariable Long clienteId){
        return ResponseEntity.ok(pedidoService.getPedidosPorCliente(clienteId));
    }

    @Operation(summary = "Agregar plato a un pedido", description = "Añade un plato a un pedido indicando la cantidad")
    @ApiResponse(responseCode = "200", description = "Plato agregado al pedido correctamente")
    @PostMapping("/{pedidoId}/platos/{platoId}")
    public ResponseEntity<Pedido> addPlatoToPedido(
            @Parameter(description = "ID del pedido") @PathVariable Long pedidoId,
            @Parameter(description = "ID del plato") @PathVariable Long platoId,
            @Parameter(description = "Cantidad del plato a agregar") @RequestParam int cantidad) throws Exception {
        Pedido pedido = pedidoService.addPlatoToPedido(pedidoId, platoId, cantidad);
        return ResponseEntity.ok(pedido);
    }
}
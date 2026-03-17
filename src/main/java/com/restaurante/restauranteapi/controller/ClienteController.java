package com.restaurante.restauranteapi.controller;


import com.restaurante.restauranteapi.model.Cliente;
import com.restaurante.restauranteapi.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtener todos los clientes", description = "Devuelve la lista completa de clientes")
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida correctamente")
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> list = clienteService.getAllClientes();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Obtener cliente por ID", description = "Devuelve un cliente según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@Parameter(description = "ID del cliente a buscar") @PathVariable Long id) throws Exception {
        Cliente cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Registra un nuevo cliente en la base de datos")
    @ApiResponse(responseCode = "201", description = "Cliente creado correctamente")
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@Parameter(description = "Objeto cliente a crear") @RequestBody Cliente cliente){
        Cliente createdCliente = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
    }

    @Operation(summary = "Actualizar cliente existente", description = "Actualiza los datos de un cliente según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente actualizado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(
            @Parameter(description = "ID del cliente a actualizar")
            @PathVariable Long id,
            @Parameter(description = "Objeto cliente con los datos actualizados")
            @RequestBody Cliente updatedCliente) throws Exception {
        Cliente clienteUpdated = clienteService.updateCliente(id, updatedCliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteUpdated);
    }

    @Operation(summary = "Eliminar cliente por ID", description = "Elimina un cliente de la base de datos según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Cliente eliminado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus deleteClienteById(@Parameter(description = "ID del cliente a eliminar") @PathVariable Long id) throws Exception {
        clienteService.deleteCliente(id);
        return HttpStatus.ACCEPTED;
    }

    @Operation(summary = "Buscar clientes por email", description = "Devuelve la lista de clientes que coincidan con el email indicado")
    @ApiResponse(responseCode = "200", description = "Clientes encontrados correctamente")
    @CrossOrigin
    @GetMapping("/buscar-email")
    public ResponseEntity<List<Cliente>> getClientesPorEmail(@Parameter(description = "Email a buscar") @RequestParam String email){
        return ResponseEntity.ok(clienteService.getClientesPorEmail(email));
    }

    @Operation(summary = "Obtener clientes con pedidos", description = "Devuelve la lista de clientes que tienen pedidos registrados")
    @ApiResponse(responseCode = "200", description = "Clientes con pedidos obtenidos correctamente")
    @CrossOrigin
    @GetMapping("/con-pedidos")
    public ResponseEntity<List<Cliente>> getClientesConPedidos(){
        return ResponseEntity.ok(clienteService.getClientesConPedidos());
    }

}
package com.restaurante.restauranteapi.controller;


import com.restaurante.restauranteapi.model.Cliente;
import com.restaurante.restauranteapi.service.ClienteService;
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

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> list = clienteService.getAllClientes();
        return ResponseEntity.ok(list);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) throws Exception {
        Cliente cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
        Cliente createdCliente = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente updatedCliente) throws Exception {
        Cliente clienteUpdated = clienteService.updateCliente(id, updatedCliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteUpdated);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus deleteClienteById(@PathVariable Long id) throws Exception {
        clienteService.deleteCliente(id);
        return HttpStatus.ACCEPTED;
    }

    @CrossOrigin
    @GetMapping("/buscar-email")
    public ResponseEntity<List<Cliente>> getClientesPorEmail(@RequestParam String email){
        return ResponseEntity.ok(clienteService.getClientesPorEmail(email));
    }

    @CrossOrigin
    @GetMapping("/con-pedidos")
    public ResponseEntity<List<Cliente>> getClientesConPedidos(){
        return ResponseEntity.ok(clienteService.getClientesConPedidos());
    }

}
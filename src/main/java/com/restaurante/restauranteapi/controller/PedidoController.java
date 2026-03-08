package com.restaurante.restauranteapi.controller;

import com.restaurante.restauranteapi.model.Pedido;
import com.restaurante.restauranteapi.service.PedidoService;
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

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos(){
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(pedidoService.getPedidoById(id));
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.createPedido(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) throws Exception {
        return ResponseEntity.ok(pedidoService.updatePedido(id, pedido));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletePedido(@PathVariable Long id) throws Exception {
        pedidoService.deletePedido(id);
        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> getPedidosPorCliente(@PathVariable Long clienteId){
        return ResponseEntity.ok(pedidoService.getPedidosPorCliente(clienteId));
    }

    @PostMapping("/{pedidoId}/platos/{platoId}")
    public ResponseEntity<Pedido> addPlatoToPedido(@PathVariable Long pedidoId, @PathVariable Long platoId, @RequestParam int cantidad) throws Exception {
        Pedido pedido = pedidoService.addPlatoToPedido(pedidoId, platoId, cantidad);
        return ResponseEntity.ok(pedido);
    }
}
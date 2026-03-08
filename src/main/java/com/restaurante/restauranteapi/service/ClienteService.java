package com.restaurante.restauranteapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.restaurante.restauranteapi.model.Cliente;
import com.restaurante.restauranteapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        List<Cliente> clientesList = clienteRepository.findAll();
        return clientesList.size() > 0 ? clientesList : new ArrayList<>();
    }

    public Cliente getClienteById(Long id) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            return cliente.get();
        }else{
            throw new Exception("No existe Cliente para el id: " + id);
        }
    }

    public Cliente createCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente cliente) throws Exception {
        if(cliente != null && cliente.getId() != null){
            Optional<Cliente> clienteOptional = clienteRepository.findById(id);
            if(clienteOptional.isPresent()){
                Cliente newCliente = clienteOptional.get();
                newCliente.setNombre(cliente.getNombre());
                newCliente.setEmail(cliente.getEmail());
                return clienteRepository.save(newCliente);
            }else{
                throw new Exception("No existe Cliente para el id: " + id);
            }
        }else{
            throw new Exception("No hay id en el cliente a actualizar");
        }
    }

    public void deleteCliente(Long id) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            clienteRepository.delete(clienteOptional.get());
        }else{
            throw new Exception("No existe Cliente para el id: " + id);
        }
    }

    public List<Cliente> getClientesPorEmail(String email){
        List<Cliente> lista = clienteRepository.findByEmailLike(email);
        return lista.isEmpty() ? new ArrayList<>() : lista;
    }

    public List<Cliente> getClientesConPedidos(){
        List<Cliente> lista = clienteRepository.findClientesConPedidos();
        return lista.isEmpty() ? new ArrayList<>() : lista;
    }

}
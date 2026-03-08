package com.restaurante.restauranteapi.service;
import com.restaurante.restauranteapi.model.Ingrediente;
import com.restaurante.restauranteapi.model.Plato;
import com.restaurante.restauranteapi.model.PlatoIngrediente;
import com.restaurante.restauranteapi.repository.IngredienteRepository;
import com.restaurante.restauranteapi.repository.PlatoRepository;
import com.restaurante.restauranteapi.repository.PlatoIngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {
    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private PlatoIngredienteRepository platoIngredienteRepository;

    // CRUD Ingrediente
    public List<Ingrediente> getAllIngredientes() {
        List<Ingrediente> lista = ingredienteRepository.findAll();
        return lista.isEmpty() ? new ArrayList<>() : lista;
    }

    public Ingrediente getIngredienteById(Long id) throws Exception {
        return ingredienteRepository.findById(id)
                .orElseThrow(() -> new Exception("Ingrediente no encontrado"));
    }

    public Ingrediente createIngrediente(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public Ingrediente updateIngrediente(Long id, Ingrediente ingrediente) throws Exception {
        Optional<Ingrediente> optional = ingredienteRepository.findById(id);
        if(optional.isPresent()) {
            Ingrediente existing = optional.get();
            existing.setNombre(ingrediente.getNombre());
            existing.setStock(ingrediente.getStock());
            return ingredienteRepository.save(existing);
        } else {
            throw new Exception("Ingrediente no encontrado");
        }
    }

    public void deleteIngrediente(Long id) throws Exception {
        Optional<Ingrediente> optional = ingredienteRepository.findById(id);
        if(optional.isPresent()) {
            ingredienteRepository.delete(optional.get());
        } else {
            throw new Exception("Ingrediente no encontrado");
        }
    }

    // Relación N:M: agregar ingrediente a plato
    public Plato addIngredienteToPlato(Long platoId, Long ingredienteId, int cantidad) throws Exception {
        Plato plato = platoRepository.findById(platoId)
                .orElseThrow(() -> new Exception("Plato no encontrado"));

        Ingrediente ingrediente = ingredienteRepository.findById(ingredienteId)
                .orElseThrow(() -> new Exception("Ingrediente no encontrado"));

        PlatoIngrediente pi = new PlatoIngrediente();
        pi.setPlato(plato);
        pi.setIngrediente(ingrediente);
        pi.setCantidad(cantidad);

        platoIngredienteRepository.save(pi);

        return plato;
    }


    public List<Plato> getPlatosPorIngrediente(Long ingredienteId){
        List<PlatoIngrediente> listaPI = platoIngredienteRepository.findByIngredienteId(ingredienteId);
        List<Plato> platos = new ArrayList<>();
        for (PlatoIngrediente pi : listaPI) {
            platos.add(pi.getPlato());
        }
        return platos;
    }
}
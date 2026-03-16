package com.restaurante.restauranteapi.service;
import com.restaurante.restauranteapi.model.Plato;
import com.restaurante.restauranteapi.model.PlatoIngrediente;
import com.restaurante.restauranteapi.model.Ingrediente;
import com.restaurante.restauranteapi.model.PlatoIngredienteId;
import com.restaurante.restauranteapi.repository.PlatoIngredienteRepository;
import com.restaurante.restauranteapi.repository.PlatoRepository;
import com.restaurante.restauranteapi.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlatoService {
    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private PlatoIngredienteRepository platoIngredienteRepository;

    public List<Plato> getAllPlatos() {
        List<Plato> lista = platoRepository.findAll();
        return lista.isEmpty() ? new ArrayList<>() : lista;
    }

    public Plato getPlatoById(Long id) throws Exception {
        return platoRepository.findById(id)
                .orElseThrow(() -> new Exception("Plato no encontrado"));
    }

    public Plato createPlato(Plato plato) {
        return platoRepository.save(plato);
    }

    public Plato updatePlato(Long id, Plato plato) throws Exception {
        Optional<Plato> optional = platoRepository.findById(id);
        if(optional.isPresent()) {
            Plato existing = optional.get();
            existing.setNombre(plato.getNombre());
            existing.setPrecio(plato.getPrecio());
            existing.setDescripcion(plato.getDescripcion());
            existing.setCategoria(plato.getCategoria());
            return platoRepository.save(existing);
        } else {
            throw new Exception("Plato no encontrado");
        }
    }

    public void deletePlato(Long id) throws Exception {
        Optional<Plato> optional = platoRepository.findById(id);
        if(optional.isPresent()) {
            platoRepository.delete(optional.get());
        } else {
            throw new Exception("Plato no encontrado");
        }
    }

    public Plato addIngredienteToPlato(Long platoId, Long ingredienteId, int cantidad) throws Exception {
        Plato plato = platoRepository.findById(platoId)
                .orElseThrow(() -> new Exception("Plato no encontrado"));

        Ingrediente ingrediente = ingredienteRepository.findById(ingredienteId).orElseThrow(() -> new Exception("Ingrediente no encontrado"));

        PlatoIngrediente pi = new PlatoIngrediente();

        PlatoIngredienteId piId = new PlatoIngredienteId();
        piId.setPlatoId(plato.getId());
        piId.setIngredienteId(ingrediente.getId());

        pi.setId(piId);
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
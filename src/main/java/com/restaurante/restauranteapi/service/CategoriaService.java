package com.restaurante.restauranteapi.service;
import com.restaurante.restauranteapi.model.Categoria;
import com.restaurante.restauranteapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        List<Categoria> lista = categoriaRepository.findAll();
        return lista.isEmpty() ? new ArrayList<>() : lista;
    }

    public Categoria getCategoriaById(Long id) throws Exception {
        return categoriaRepository.findById(id).orElseThrow(() -> new Exception("Categoria no encontrada"));
    }

    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Long id, Categoria categoria) throws Exception {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if(optional.isPresent()) {
            Categoria existing = optional.get();
            existing.setNombre(categoria.getNombre());
            return categoriaRepository.save(existing);
        } else {
            throw new Exception("Categoria no encontrada");
        }
    }

    public void deleteCategoria(Long id) throws Exception {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if(optional.isPresent()) {
            categoriaRepository.delete(optional.get());
        } else {
            throw new Exception("Categoria no encontrada");
        }
    }

}

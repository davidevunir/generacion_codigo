package msa_proyectos.service;

import msa_proyectos.entities.Proyecto;
import org.springframework.stereotype.Service;
import msa_proyectos.persistence.ProyectoRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProyectoService {
    private final ProyectoRepository proyectoRepository;

    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    public List<Proyecto> findAll() {
        return proyectoRepository.findAll();
    }

    public Proyecto save(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public Proyecto findById(UUID id) {
        return proyectoRepository.findById(id).orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
    }

    public void delete(UUID id) {
        proyectoRepository.deleteById(id);
    }
}

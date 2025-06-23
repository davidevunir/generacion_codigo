package com.msa.historiasUsu_Resp.repository.impl;

import static lombok.AccessLevel.PRIVATE;
import com.msa.historiasUsu_Resp.repository.IDHistoriaRepository;
import com.msa.historiasUsu_Resp.repository.jpa.HistoriaJpaRepository;
import com.msa.historiasUsu_Resp.repository.model.Historia;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class HistoriaRepository implements IDHistoriaRepository {

    HistoriaJpaRepository repository;

    @Override
    public List<Historia> getAll() {
        return repository.findAll();
    }

    @Override
    public Historia getById(UUID id) {
        return repository.findById(id).orElse(null);
    }
    @Override
    public List<Historia> getByProyectoId(UUID proyectoId) {
        return repository.findByProyectoId(proyectoId);
    }
    @Override
    public List<Historia> getByResponsableId(UUID responsableId) {
        return repository.findByResponsableId(responsableId);
    }
    @Override
    public Historia save(Historia historia) {
        return repository.save(historia);
    }
    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }



}

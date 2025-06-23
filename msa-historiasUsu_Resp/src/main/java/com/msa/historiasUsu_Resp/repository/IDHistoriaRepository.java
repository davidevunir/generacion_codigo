package com.msa.historiasUsu_Resp.repository;

import com.msa.historiasUsu_Resp.repository.model.Historia;
import java.util.List;
import java.util.UUID;


public interface IDHistoriaRepository {

    List<Historia> getAll();
    Historia getById(UUID id);
    List<Historia> getByProyectoId(UUID proyectoId);
    List<Historia> getByResponsableId(UUID responsableId);
    Historia save(Historia historia);
    void deleteById(UUID id);

}

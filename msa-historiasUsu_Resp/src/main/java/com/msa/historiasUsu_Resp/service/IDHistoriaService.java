package com.msa.historiasUsu_Resp.service;


import com.msa.historiasUsu_Resp.dto.GenericResponse;
import com.msa.historiasUsu_Resp.repository.model.Historia;
import java.util.UUID;

public interface  IDHistoriaService {
    GenericResponse<Object> getAll();
    GenericResponse<Object> getById(UUID id);
    GenericResponse<Object> getByProyectoId(UUID proyectoId);
    GenericResponse<Object> getByResponsableId(UUID responsableId);
    GenericResponse<Object> createHistoria(Historia historia);
    GenericResponse<Object> updateHistoria(UUID id, Historia historia);
    GenericResponse<Object> deleteHistoria(UUID id);


}

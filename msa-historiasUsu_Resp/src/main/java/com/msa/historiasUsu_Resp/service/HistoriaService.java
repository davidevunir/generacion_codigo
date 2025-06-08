package com.msa.historiasUsu_Resp.service;

import com.msa.historiasUsu_Resp.repository.model.Historia;
import com.msa.historiasUsu_Resp.dto.GenericResponse;

import java.util.UUID;

public interface HistoriaService {
    GenericResponse<Object> listarTodos();
    GenericResponse<Object> obtenerPorId(UUID id);
    GenericResponse<Object> crear(Historia historia);
    GenericResponse<Object> actualizar(UUID id, Historia historia);
    GenericResponse<Object> eliminar(UUID id);
}

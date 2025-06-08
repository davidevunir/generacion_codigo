package com.msa.historiasUsu_Resp.service;

import com.msa.historiasUsu_Resp.repository.model.Responsable;
import com.msa.historiasUsu_Resp.dto.GenericResponse;

import java.util.UUID;


public interface ResponsableService {
    GenericResponse<Object> listarTodos();
    GenericResponse<Object> obtenerPorId(UUID id);
    GenericResponse<Object> crear(Responsable responsable);
    GenericResponse<Object> actualizar(UUID id, Responsable responsable);
    GenericResponse<Object> eliminar(UUID id);


}

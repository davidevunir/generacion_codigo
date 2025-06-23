package com.msa.historiasUsu_Resp.service.impl;

import static lombok.AccessLevel.PRIVATE;


import com.msa.historiasUsu_Resp.dto.GenericResponse;
import com.msa.historiasUsu_Resp.repository.IDHistoriaRepository;
import com.msa.historiasUsu_Resp.repository.model.Historia;
import com.msa.historiasUsu_Resp.repository.model.Historia.Estado;
import com.msa.historiasUsu_Resp.service.IDHistoriaService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class HistoriaService implements IDHistoriaService {

    IDHistoriaRepository repository;

    @Override
    public GenericResponse<Object> getAll() {
        return GenericResponse.builder()
            .data(repository.getAll())
            .message("OK")
            .build();
    }

    @Override
    public GenericResponse<Object> getById(UUID id) {
        return GenericResponse.builder()
            .data(repository.getById(id))
            .message("OK")
            .build();
    }

    @Override
    public GenericResponse<Object> getByProyectoId(UUID proyectoId) {
        List<Historia> historias = repository.getByProyectoId(proyectoId);
        return GenericResponse.builder()
            .data(historias)
            .message(historias.isEmpty() ? "No se encontraron historias para el proyectoId: " + proyectoId : "OK")
            .build();
    }

    @Override
    public GenericResponse<Object> getByResponsableId(UUID responsableId) {
        List<Historia> historias = repository.getByResponsableId(responsableId);
        return GenericResponse.builder()
            .data(historias)
            .message(historias.isEmpty() ? "No se encontraron historias para el responsableId: " + responsableId : "OK")
            .build();
    }

    @Override
    @Transactional
    public GenericResponse<Object> createHistoria(Historia historia) {

        if (historia.getProyectoId() == null || historia.getResponsableId() == null) {
            return GenericResponse.builder()
                .message("El proyectoId y el responsableId son obligatorios")
                .build();
        }

        if (historia.getDescripcion() == null || historia.getDescripcion().isEmpty()) {
                 historia.setDescripcion("");
        }
        if (historia.getEstado() == null ) {
            historia.setEstado(Estado.ACTIVO);
        }

        try {
            Historia historiaGuardada = repository.save(historia);
            return GenericResponse.builder()
                .data(historiaGuardada)
                .message("Historia creada exitosamente")
                .build();
        } catch (Exception e) {
            return GenericResponse.builder()
                .message("Error al crear la historia: " + e.getMessage())
                .build();
        }

    }

    @Override
    @Transactional
    public GenericResponse<Object> updateHistoria(UUID id, Historia historiaDetails) {

        try {
            Historia historia = repository.getById(id);
            if (historia == null) {
                return GenericResponse.builder()
                        .message("Historia no encontrada con id: " + id)
                        .build();
            }

            if (historiaDetails.getProyectoId() != null) {
                historia.setProyectoId(historiaDetails.getProyectoId());
            }
            if (historiaDetails.getResponsableId() != null) {
                historia.setResponsableId(historiaDetails.getResponsableId());
            }
            if (historiaDetails.getDescripcion() != null && !historiaDetails.getDescripcion().isEmpty()) {
                historia.setDescripcion(historiaDetails.getDescripcion());
            }
            if (historiaDetails.getEstado() != null) {
                historia.setEstado(historiaDetails.getEstado());
            }



                Historia historiaActualizada = repository.save(historia);
                return GenericResponse.builder()
                        .data(historiaActualizada)
                        .message("Historia actualizada exitosamente")
                        .build();
        }catch (Exception e) {
            return GenericResponse.builder()
                    .message("Error al actualizar la historia: " + e.getMessage())
                    .build();
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteHistoria(UUID id) {

        try {

            if (repository.getById(id) == null) {
                return GenericResponse.builder()
                        .message("Historia no encontrada con id: " + id)
                        .build();
            }

            repository.deleteById(id);
            return GenericResponse.builder()
                .message("Historia eliminada exitosamente")
                .build();
        } catch (Exception e) {
            return GenericResponse.builder()
                .message("Error al eliminar la historia: " + e.getMessage())
                .build();
        }
    }

}

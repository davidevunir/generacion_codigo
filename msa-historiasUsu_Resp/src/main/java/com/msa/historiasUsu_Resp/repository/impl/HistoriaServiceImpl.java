package com.msa.historiasUsu_Resp.repository.impl;
import com.msa.historiasUsu_Resp.dto.GenericResponse;
import com.msa.historiasUsu_Resp.repository.model.Historia;
import com.msa.historiasUsu_Resp.repository.jpa.HistoriaRepository;
import com.msa.historiasUsu_Resp.service.HistoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
public class HistoriaServiceImpl implements HistoriaService {

    private final HistoriaRepository historiaRepository;

    @Autowired
    public HistoriaServiceImpl(HistoriaRepository historiaRepository) {
        this.historiaRepository = historiaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public GenericResponse<Object> listarTodos() {
        try {
            List<Historia> historias = historiaRepository.findAll();
            if (historias.isEmpty()) {
                return GenericResponse.success("No se encontraron historias registradas");
            }
            return GenericResponse.success(historias);
        } catch (Exception e) {
            return GenericResponse.error("Error al listar historias: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public GenericResponse<Object> obtenerPorId(UUID id) {
        try {
            if (id == null) {
                return GenericResponse.error("El ID no puede ser nulo");
            }
            Optional<Historia> historiaOptional = historiaRepository.findById(id);
            if (historiaOptional.isEmpty()) {
                return GenericResponse.error("No se encontró la historia con ID: " + id);
            }
            return GenericResponse.success(historiaOptional.get());
        } catch (Exception e) {
            return GenericResponse.error("Error al obtener la historia: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> crear(Historia historia) {
        try {
            if (historia == null) {
                return GenericResponse.error("La historia no puede ser nula");
            }

            // Validaciones de campos obligatorios
            if (historia.getProyectoId() == null) {
                return GenericResponse.error("El ID del proyecto es obligatorio");
            }
            if (historia.getResponsableId() == null) {
                return GenericResponse.error("El ID del responsable es obligatorio");
            }
            if (historia.getEstado() == null || historia.getEstado().equals("")) {
                return GenericResponse.error("El estado es obligatorio");
            }


            // Limpiar el ID para asegurar que es una nueva entrada
            historia.setId(null);
            Historia nuevaHistoria = historiaRepository.save(historia);
            return GenericResponse.success(nuevaHistoria);
        } catch (Exception e) {
            return GenericResponse.error("Error al crear la historia: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> actualizar(UUID id, Historia historia) {
        try {
            if (id == null) {
                return GenericResponse.error("El ID no puede ser nulo");
            }
            if (historia == null) {
                return GenericResponse.error("La historia no puede ser nula");
            }

            Optional<Historia> historiaExistente = historiaRepository.findById(id);
            if (historiaExistente.isEmpty()) {
                return GenericResponse.error("No se encontró la historia con ID: " + id);
            }


            // Validaciones de campos obligatorios
            if (historia.getProyectoId() == null) {
                return GenericResponse.error("El ID del proyecto es obligatorio");
            }
            if (historia.getResponsableId() == null) {
                return GenericResponse.error("El ID del responsable es obligatorio");
            }
            if (historia.getEstado() == null || historia.getEstado().equals("") ) {
                return GenericResponse.error("El estado es obligatorio");
            }

            historia.setId(id);
            Historia historiaActualizada = historiaRepository.save(historia);
            return GenericResponse.success(historiaActualizada);
        } catch (Exception e) {
            return GenericResponse.error("Error al actualizar la historia: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> eliminar(UUID id) {
        try {
            if (id == null) {
                return GenericResponse.error("El ID no puede ser nulo");
            }
            if (!historiaRepository.existsById(id)) {
                return GenericResponse.error("No se encontró la historia con ID: " + id);
            }
            historiaRepository.deleteById(id);
            return GenericResponse.success("Historia eliminada exitosamente");
        } catch (Exception e) {
            return GenericResponse.error("Error al eliminar la historia: " + e.getMessage());
        }
    }
}


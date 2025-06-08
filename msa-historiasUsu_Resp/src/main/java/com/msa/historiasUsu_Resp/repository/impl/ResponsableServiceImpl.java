package com.msa.historiasUsu_Resp.repository.impl;

import com.msa.historiasUsu_Resp.dto.GenericResponse;
import com.msa.historiasUsu_Resp.repository.model.Responsable;
import com.msa.historiasUsu_Resp.repository.jpa.ResponsableRepository;
import com.msa.historiasUsu_Resp.service.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ResponsableServiceImpl implements ResponsableService {

    private final ResponsableRepository responsableRepository;

    @Autowired
    public ResponsableServiceImpl(ResponsableRepository responsableRepository) {
        this.responsableRepository = responsableRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public GenericResponse<Object> listarTodos() {
        try {
            List<Responsable> responsables = responsableRepository.findAll();
            if (responsables.isEmpty()) {
                return GenericResponse.success("No se encontraron responsables registrados");
            }
            return GenericResponse.success(responsables);
        } catch (Exception e) {
            return GenericResponse.error("Error al listar responsables: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public GenericResponse<Object> obtenerPorId(UUID id) {
        try {
            if (id == null) {
                return GenericResponse.error("El ID no puede ser nulo");
            }
            Optional<Responsable> responsableOptional = responsableRepository.findById(id);
            if (responsableOptional.isEmpty()) {
                return GenericResponse.error("No se encontró el responsable con ID: " + id);
            }
            return GenericResponse.success(responsableOptional.get());
        } catch (Exception e) {
            return GenericResponse.error("Error al obtener el responsable: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> crear(Responsable responsable) {
        try {
            if (responsable == null) {
                return GenericResponse.error("El responsable no puede ser nulo");
            }
            // Validaciones de campos obligatorios
            if (responsable.getNombre() == null || responsable.getNombre().trim().isEmpty()) {
                return GenericResponse.error("El nombre es obligatorio");
            }
            if (responsable.getEmail() == null || responsable.getEmail().trim().isEmpty()) {
                return GenericResponse.error("El email es obligatorio");
            }
            // Validación básica de formato de email
            if (!responsable.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                return GenericResponse.error("El formato del email no es válido");
            }

            // Limpiar el ID para asegurar que es una nueva entrada
            responsable.setId(null);
            Responsable nuevoResponsable = responsableRepository.save(responsable);
            return GenericResponse.success(nuevoResponsable);
        } catch (Exception e) {
            return GenericResponse.error("Error al crear el responsable: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> actualizar(UUID id, Responsable responsable) {
        try {
            if (id == null) {
                return GenericResponse.error("El ID no puede ser nulo");
            }
            if (responsable == null) {
                return GenericResponse.error("El responsable no puede ser nulo");
            }

            Optional<Responsable> responsableExistente = responsableRepository.findById(id);
            if (responsableExistente.isEmpty()) {
                return GenericResponse.error("No se encontró el responsable con ID: " + id);
            }

            // Validaciones de campos obligatorios
            if (responsable.getNombre() == null || responsable.getNombre().trim().isEmpty()) {
                return GenericResponse.error("El nombre es obligatorio");
            }
            if (responsable.getEmail() == null || responsable.getEmail().trim().isEmpty()) {
                return GenericResponse.error("El email es obligatorio");
            }
            // Validación básica de formato de email
            if (!responsable.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                return GenericResponse.error("El formato del email no es válido");
            }

            responsable.setId(id);
            Responsable responsableActualizado = responsableRepository.save(responsable);
            return GenericResponse.success(responsableActualizado);
        } catch (Exception e) {
            return GenericResponse.error("Error al actualizar el responsable: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> eliminar(UUID id) {
        try {
            if (id == null) {
                return GenericResponse.error("El ID no puede ser nulo");
            }
            if (!responsableRepository.existsById(id)) {
                return GenericResponse.error("No se encontró el responsable con ID: " + id);
            }
            responsableRepository.deleteById(id);
            return GenericResponse.success("Responsable eliminado exitosamente");
        } catch (Exception e) {
            return GenericResponse.error("Error al eliminar el responsable: " + e.getMessage());
        }
    }
}


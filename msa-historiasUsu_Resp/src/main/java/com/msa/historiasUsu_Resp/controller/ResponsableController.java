package com.msa.historiasUsu_Resp.controller;

import com.msa.historiasUsu_Resp.dto.GenericResponse;
import com.msa.historiasUsu_Resp.repository.model.Responsable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.msa.historiasUsu_Resp.service.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;


import java.util.UUID;

@RestController
@RequestMapping("/api/responsables")
@RequiredArgsConstructor
@Tag(name = "Responsable", description = "API para gestionar responsables")

public class ResponsableController {

    @Autowired
    private ResponsableService responsableService;

    @Operation(summary = "Obtener todos los responsables")
    @ApiResponse(responseCode = "200", description = "Lista de responsables encontrada")
    @GetMapping
    public ResponseEntity<GenericResponse<Object>> listarTodos() {
        return ResponseEntity.ok(responsableService.listarTodos());
    }

    @Operation(summary = "Obtener un responsable por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Responsable encontrado"),
            @ApiResponse(responseCode = "404", description = "Responsable no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<Object>> obtenerPorId(
            @Parameter(description = "ID del responsable a obtener")
            @PathVariable UUID id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new GenericResponse<>("El ID no debe ser nulo."));
        }
        return ResponseEntity.ok(responsableService.obtenerPorId(id));
    }

    @Operation(summary = "Crear un nuevo responsable")
    @ApiResponse(responseCode = "200", description = "Responsable creado exitosamente")
    @PostMapping

    public ResponseEntity<GenericResponse<Object>> crear(
            @Parameter(description = "Responsable a crear")
            @RequestBody Responsable responsable) {
        if (responsable.getId() != null) {
            return ResponseEntity.badRequest().body(new GenericResponse<>("El ID no debe ser proporcionado al crear un nuevo responsable."));
        }
        return ResponseEntity.ok(responsableService.crear(responsable));
    }

    @Operation(summary = "Actualizar un responsable existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Responsable actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Responsable no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<Object>> actualizar(
            @Parameter(description = "ID del responsable a actualizar")
            @PathVariable UUID id,
            @RequestBody Responsable responsable) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new GenericResponse<>("El ID no debe ser nulo."));
        }
        return ResponseEntity.ok(responsableService.actualizar(id, responsable));
    }

    @Operation(summary = "Eliminar un responsable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Responsable eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Responsable no encontrado")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Object>> eliminar(
            @Parameter(description = "ID del responsable a eliminar")
            @PathVariable UUID id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new GenericResponse<>("El ID no debe ser nulo."));
        }
        return ResponseEntity.ok(responsableService.eliminar(id));
    }
}


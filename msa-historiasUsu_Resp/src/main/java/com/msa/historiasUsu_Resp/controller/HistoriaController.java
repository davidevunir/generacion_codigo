package com.msa.historiasUsu_Resp.controller;

import com.msa.historiasUsu_Resp.dto.GenericResponse;
import com.msa.historiasUsu_Resp.repository.model.Historia;
import com.msa.historiasUsu_Resp.service.HistoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



import java.util.UUID;

@RestController
@RequestMapping("/api/historias")
@RequiredArgsConstructor
@Tag(name = "Historia de Usuario", description = "API para gestionar historias de usuario")

public class HistoriaController {

    @Autowired
    private HistoriaService historiaService;

    @Operation(summary = "Obtener todas las historias de usuario")
    @ApiResponse(responseCode = "200", description = "Lista de historias encontrada")
    @GetMapping
    public ResponseEntity<GenericResponse<Object>> listarTodos() {
        return ResponseEntity.ok(historiaService.listarTodos());
    }

    @Operation(summary = "Obtener una historia de usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historia encontrada"),
            @ApiResponse(responseCode = "404", description = "Historia no encontrada")
    })

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<Object>> obtenerPorId(
            @Parameter(description = "ID de la historia de usuario a obtener")
            @PathVariable UUID id) {
        return ResponseEntity.ok(historiaService.obtenerPorId(id));
    }

    @Operation(summary = "Crear una nueva historia de usuario")
    @ApiResponse(responseCode = "200", description = "Historia creada exitosamente")
    @PostMapping
    public ResponseEntity<GenericResponse<Object>> crear(
            @Parameter(description = "Historia de usuario a crear")
            @RequestBody Historia historia) {
        return ResponseEntity.ok(historiaService.crear(historia));
    }

    @Operation(summary = "Actualizar una historia de usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historia actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Historia no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<Object>> actualizar(
            @Parameter(description = "ID de la historia de usuario a actualizar")
            @PathVariable UUID id,
            @Parameter(description = "Historia de usuario con los nuevos datos")
            @RequestBody Historia historia) {
        if (historia.getId() == null) {
            historia.setId(id);
        }
        return ResponseEntity.ok(historiaService.actualizar(id, historia));
    }

    @Operation(summary = "Eliminar una historia de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Historia eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Historia no encontrada")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Object>> eliminar(
            @Parameter(description = "ID de la historia de usuario a eliminar")
            @PathVariable UUID id) {
        return ResponseEntity.ok(historiaService.eliminar(id));
    }
}

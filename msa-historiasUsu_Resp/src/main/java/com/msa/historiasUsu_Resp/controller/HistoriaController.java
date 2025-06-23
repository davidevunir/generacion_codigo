package com.msa.historiasUsu_Resp.controller;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.*;

import com.msa.historiasUsu_Resp.dto.GenericResponse;
import com.msa.historiasUsu_Resp.repository.model.Historia;
import com.msa.historiasUsu_Resp.service.IDHistoriaService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/historias")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class HistoriaController {

    IDHistoriaService service;

    @GetMapping
    public ResponseEntity<GenericResponse<Object>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<Object>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/proyectos/{proyectoId}")
    public ResponseEntity<GenericResponse<Object>> getByProyectoId(@PathVariable UUID proyectoId) {
        return ResponseEntity.ok(service.getByProyectoId(proyectoId));
    }

    @GetMapping("/responsables/{responsableId}")
    public ResponseEntity<GenericResponse<Object>> getByResponsableId(@PathVariable UUID responsableId) {
        return ResponseEntity.ok(service.getByResponsableId(responsableId));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<Object>> createHistoria(@RequestBody Historia historia) {
        GenericResponse<Object> response = service.createHistoria(historia);

        if (response == null) {
            response = GenericResponse.builder()
                    .message("Error: No se pudo crear la historia.")
                    .build();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response);
        }

        if ("La historia ya existe".equals(response.getMessage())) {
            return ResponseEntity.status(IM_USED).body(response);
        }

        return ResponseEntity.status(CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<Object>> updateHistoria(@PathVariable UUID id, @RequestBody Historia historiaDetails) {
        GenericResponse<Object> response = service.updateHistoria(id, historiaDetails);

        if (response == null) {
            response = GenericResponse.builder()
                    .message("Error: No se pudo actualizar la historia.")
                    .build();
            return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
        }

        if ("Historia no encontrada".equals(response.getMessage())) {
            return new ResponseEntity<>(response, NOT_FOUND);
        }
        return new ResponseEntity<>(response, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Object>> deleteHistoria(@PathVariable UUID id) {
        GenericResponse<Object> response = service.deleteHistoria(id);
        if (response == null) {
            response = GenericResponse.builder()
                    .message("Error: No se pudo eliminar la historia.")
                    .build();
            return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
        }

        if (response.getMessage().contains("no encontrada")) {
            return new ResponseEntity<>(response, NOT_FOUND);
        }

        return new ResponseEntity<>(response, NO_CONTENT);
    }

}

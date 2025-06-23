package msa_proyectos.controller;

import msa_proyectos.entities.Proyecto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import msa_proyectos.service.ProyectoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/proyectos")
@Slf4j
@Tag(name = "Proyecto Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre una base de datos relacional..")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @Operation(summary = "Listar todos los proyectos")
    @GetMapping
    public List<Proyecto> getAll() {
        return proyectoService.findAll();
    }

    @Operation(summary = "Crear un nuevo proyecto")
    @PostMapping
    public Proyecto create(@RequestBody Proyecto proyecto) {
        return proyectoService.save(proyecto);
    }

    @Operation(summary = "Buscar un proyecto por ID")
    @GetMapping("/{id}")
    public Proyecto getById(@PathVariable UUID id) {
        return proyectoService.findById(id);
    }

    @Operation(summary = "Eliminar un proyecto por ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        proyectoService.delete(id);
    }
}
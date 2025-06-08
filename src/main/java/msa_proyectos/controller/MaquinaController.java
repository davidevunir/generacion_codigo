package msa_proyectos.controller;

import msa_proyectos.entities.Maquina;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import msa_proyectos.service.MaquinaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/maquinas")
@Slf4j
@Tag(name = "Maquina Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre una base de datos relacional..")
public class MaquinaController {

    private final MaquinaService maquinaService;

    public MaquinaController(MaquinaService maquinaService) {
        this.maquinaService = maquinaService;
    }

    @Operation(summary = "Listar todas las máquinas")
    @GetMapping
    public List<Maquina> getAll() {
        return maquinaService.findAll();
    }

    @Operation(summary = "Crear una nueva máquina")
    @PostMapping
    public Maquina create(@RequestBody Maquina maquina) {
        return maquinaService.save(maquina);
    }

    @Operation(summary = "Buscar una máquina por ID")
    @GetMapping("/{id}")
    public Maquina getById(@PathVariable UUID id) {
        return maquinaService.findById(id);
    }

    @Operation(summary = "Eliminar una máquina por ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        maquinaService.delete(id);
    }
}
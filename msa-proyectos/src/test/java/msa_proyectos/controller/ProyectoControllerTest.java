package msa_proyectos.controller;

import msa_proyectos.entities.Proyecto;
import msa_proyectos.service.ProyectoService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProyectoController.class)
public class ProyectoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProyectoService proyectoService;

    @Test
    void testGetAllProyectos() throws Exception {
        Proyecto proyecto = new Proyecto("Sistema de Ventas", "Descripción", LocalDate.of(2024, 11, 1), true);

        Mockito.when(proyectoService.findAll()).thenReturn(List.of(proyecto));

        mockMvc.perform(get("/api/proyectos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Sistema de Ventas")))
                .andExpect(jsonPath("$[0].activo", is(true)));
    }

    @Test
    void testGetProyectoById() throws Exception {
        UUID id = UUID.randomUUID();
        Proyecto proyecto = new Proyecto("E-learning", "Cursos en línea", LocalDate.of(2025, 2, 10), true);

        Mockito.when(proyectoService.findById(id)).thenReturn(proyecto);

        mockMvc.perform(get("/api/proyectos/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("E-learning")))
                .andExpect(jsonPath("$.activo", is(true)));
    }

    @Test
    void testCreateProyecto() throws Exception {
        Proyecto input = new Proyecto( "Nuevo Proyecto", "Descripción nueva", LocalDate.now(), true);
        Proyecto saved = new Proyecto( "Nuevo Proyecto", "Descripción nueva", LocalDate.now(), true);

        Mockito.when(proyectoService.save(Mockito.any(Proyecto.class))).thenReturn(saved);

        mockMvc.perform(post("/api/proyectos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Nuevo Proyecto")))
                .andExpect(jsonPath("$.activo", is(true)));
    }

    @Test
    void testDeleteProyecto() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/api/proyectos/" + id))
                .andExpect(status().isOk());
    }
}


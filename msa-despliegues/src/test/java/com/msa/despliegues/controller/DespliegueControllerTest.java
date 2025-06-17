package com.msa.despliegues.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.despliegues.dto.GenericResponse;
import com.msa.despliegues.repository.model.Despliegue;
import com.msa.despliegues.service.IDespliegueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DespliegueController Tests")
class DespliegueControllerTest {

  @Mock
  private IDespliegueService service;

  @InjectMocks
  private DespliegueController controller;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  private UUID testId;
  private UUID proyectoId;
  private UUID maquinaId;
  private Despliegue despliegue;
  private GenericResponse<Object> successResponse;
  private GenericResponse<Object> notFoundResponse;
  private GenericResponse<Object> errorResponse;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    objectMapper = new ObjectMapper();

    testId = UUID.randomUUID();
    proyectoId = UUID.randomUUID();
    maquinaId = UUID.randomUUID();

    despliegue = new Despliegue();

    successResponse = new GenericResponse<>();
    successResponse.setMessage("Operación realizada exitosamente");
    successResponse.setData(despliegue);

    notFoundResponse = new GenericResponse<>();
    notFoundResponse.setMessage("Despliegue no encontrado");

    errorResponse = new GenericResponse<>();
    errorResponse.setMessage("Error interno");
  }

  @Test
  @DisplayName("GET /despliegues - Debería retornar todos los despliegues")
  void testGetAll_ShouldReturnAllDespliegues() throws Exception {
    List<Despliegue> despliegues = Arrays.asList(despliegue);
    GenericResponse<Object> response = new GenericResponse<>();
    response.setData(despliegues);
    response.setMessage("Despliegues obtenidos exitosamente");

    when(service.getAll()).thenReturn(response);

    mockMvc.perform(get("/despliegues"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Despliegues obtenidos exitosamente"));

    verify(service, times(1)).getAll();
  }

  @Test
  @DisplayName("GET /despliegues/{id} - Debería retornar despliegue por ID")
  void testGetById_ShouldReturnDespliegueById() throws Exception {
    when(service.getById(testId)).thenReturn(successResponse);

    mockMvc.perform(get("/despliegues/{id}", testId))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Operación realizada exitosamente"));

    verify(service, times(1)).getById(testId);
  }

  @Test
  @DisplayName("GET /despliegues/proyectos/{proyectoId} - Debería retornar despliegues por proyecto")
  void testGetByProyectoId_ShouldReturnDesplieguesByProyecto() throws Exception {
    when(service.getByProyectoId(proyectoId)).thenReturn(successResponse);

    mockMvc.perform(get("/despliegues/proyectos/{proyectoId}", proyectoId))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Operación realizada exitosamente"));

    verify(service, times(1)).getByProyectoId(proyectoId);
  }

  @Test
  @DisplayName("GET /despliegues/maquinas/{maquinaId} - Debería retornar despliegues por máquina")
  void testGetByMaquinaId_ShouldReturnDesplieguesByMaquina() throws Exception {
    when(service.getByMaquinaId(maquinaId)).thenReturn(successResponse);

    mockMvc.perform(get("/despliegues/maquinas/{maquinaId}", maquinaId))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Operación realizada exitosamente"));

    verify(service, times(1)).getByMaquinaId(maquinaId);
  }

  @Test
  @DisplayName("POST /despliegues - Debería crear despliegue exitosamente")
  void testCreateDespliegue_Success_ShouldReturnCreated() throws Exception {
    when(service.createDespliegue(any(Despliegue.class))).thenReturn(successResponse);

    mockMvc.perform(post("/despliegues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(despliegue)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Operación realizada exitosamente"));

    verify(service, times(1)).createDespliegue(any(Despliegue.class));
  }

  @Test
  @DisplayName("POST /despliegues - Debería retornar bad request cuando falla la creación")
  void testCreateDespliegue_Failure_ShouldReturnBadRequest() throws Exception {
    when(service.createDespliegue(any(Despliegue.class))).thenReturn(errorResponse);

    mockMvc.perform(post("/despliegues")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(despliegue)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Error interno"));

    verify(service, times(1)).createDespliegue(any(Despliegue.class));
  }

  @Test
  @DisplayName("PUT /despliegues/{id} - Debería actualizar despliegue exitosamente")
  void testUpdateDespliegue_Success_ShouldReturnOk() throws Exception {
    when(service.updateDespliegue(eq(testId), any(Despliegue.class))).thenReturn(successResponse);

    mockMvc.perform(put("/despliegues/{id}", testId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(despliegue)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Operación realizada exitosamente"));

    verify(service, times(1)).updateDespliegue(eq(testId), any(Despliegue.class));
  }

  @Test
  @DisplayName("PUT /despliegues/{id} - Debería retornar not found cuando no existe el despliegue")
  void testUpdateDespliegue_NotFound_ShouldReturnNotFound() throws Exception {
    when(service.updateDespliegue(eq(testId), any(Despliegue.class))).thenReturn(notFoundResponse);

    mockMvc.perform(put("/despliegues/{id}", testId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(despliegue)))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Despliegue no encontrado"));

    verify(service, times(1)).updateDespliegue(eq(testId), any(Despliegue.class));
  }

  @Test
  @DisplayName("PUT /despliegues/{id} - Debería retornar bad request cuando falla la actualización")
  void testUpdateDespliegue_Failure_ShouldReturnBadRequest() throws Exception {
    when(service.updateDespliegue(eq(testId), any(Despliegue.class))).thenReturn(errorResponse);

    mockMvc.perform(put("/despliegues/{id}", testId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(despliegue)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Error interno"));

    verify(service, times(1)).updateDespliegue(eq(testId), any(Despliegue.class));
  }

  @Test
  @DisplayName("DELETE /despliegues/{id} - Debería eliminar despliegue exitosamente")
  void testDeleteDespliegue_Success_ShouldReturnNoContent() throws Exception {
    when(service.deleteDespliegue(testId)).thenReturn(successResponse);

    mockMvc.perform(delete("/despliegues/{id}", testId))
        .andExpect(status().isNoContent());

    verify(service, times(1)).deleteDespliegue(testId);
  }

  @Test
  @DisplayName("DELETE /despliegues/{id} - Debería retornar not found cuando no existe el despliegue")
  void testDeleteDespliegue_NotFound_ShouldReturnNotFound() throws Exception {
    when(service.deleteDespliegue(testId)).thenReturn(notFoundResponse);

    mockMvc.perform(delete("/despliegues/{id}", testId))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Despliegue no encontrado"));

    verify(service, times(1)).deleteDespliegue(testId);
  }

  @Test
  @DisplayName("DELETE /despliegues/{id} - Debería retornar internal server error cuando falla la eliminación")
  void testDeleteDespliegue_Failure_ShouldReturnInternalServerError() throws Exception {
    when(service.deleteDespliegue(testId)).thenReturn(errorResponse);

    mockMvc.perform(delete("/despliegues/{id}", testId))
        .andExpect(status().isInternalServerError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Error interno"));

    verify(service, times(1)).deleteDespliegue(testId);
  }
}

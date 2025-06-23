package com.msa.despliegues.service.impl;

import com.msa.despliegues.dto.GenericResponse;
import com.msa.despliegues.repository.IDespliegueRepository;
import com.msa.despliegues.repository.model.Despliegue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DespliegueService Tests")
class DespliegueServiceTest {

  @Mock
  private IDespliegueRepository repository;

  @InjectMocks
  private DespliegueService service;

  private UUID testId;
  private UUID proyectoId;
  private UUID maquinaId;
  private Despliegue despliegue;
  private List<Despliegue> desplieguesList;

  @BeforeEach
  void setUp() {
    testId = UUID.randomUUID();
    proyectoId = UUID.randomUUID();
    maquinaId = UUID.randomUUID();

    despliegue = new Despliegue();
    despliegue.setId(testId);
    despliegue.setProyectoId(proyectoId);
    despliegue.setMaquinaId(maquinaId);
    despliegue.setFechaDespliegue(LocalDate.now());
    despliegue.setActivo(true);

    desplieguesList = Arrays.asList(despliegue);
  }

  @Test
  @DisplayName("getAll - Debería retornar todos los despliegues")
  void testGetAll_ShouldReturnAllDespliegues() {
    // Given
    when(repository.getAll()).thenReturn(desplieguesList);

    // When
    GenericResponse<Object> response = service.getAll();

    // Then
    assertNotNull(response);
    assertEquals("OK", response.getMessage());
    assertEquals(desplieguesList, response.getData());
    verify(repository, times(1)).getAll();
  }

  @Test
  @DisplayName("getById - Debería retornar despliegue por ID")
  void testGetById_ShouldReturnDespliegueById() {
    // Given
    when(repository.getById(testId)).thenReturn(despliegue);

    // When
    GenericResponse<Object> response = service.getById(testId);

    // Then
    assertNotNull(response);
    assertEquals("OK", response.getMessage());
    assertEquals(despliegue, response.getData());
    verify(repository, times(1)).getById(testId);
  }

  @Test
  @DisplayName("getByProyectoId - Debería retornar despliegues cuando existen")
  void testGetByProyectoId_WhenDesplieguesExist_ShouldReturnDespliegues() {
    // Given
    when(repository.getByProyectoId(proyectoId)).thenReturn(desplieguesList);

    // When
    GenericResponse<Object> response = service.getByProyectoId(proyectoId);

    // Then
    assertNotNull(response);
    assertEquals("OK", response.getMessage());
    assertEquals(desplieguesList, response.getData());
    verify(repository, times(1)).getByProyectoId(proyectoId);
  }

  @Test
  @DisplayName("getByProyectoId - Debería retornar mensaje cuando no existen despliegues")
  void testGetByProyectoId_WhenNoDesplieguesExist_ShouldReturnEmptyMessage() {
    // Given
    List<Despliegue> emptyList = Collections.emptyList();
    when(repository.getByProyectoId(proyectoId)).thenReturn(emptyList);

    // When
    GenericResponse<Object> response = service.getByProyectoId(proyectoId);

    // Then
    assertNotNull(response);
    assertEquals("No se encontraron despliegues para el proyectoId: " + proyectoId, response.getMessage());
    assertEquals(emptyList, response.getData());
    verify(repository, times(1)).getByProyectoId(proyectoId);
  }

  @Test
  @DisplayName("getByMaquinaId - Debería retornar despliegues cuando existen")
  void testGetByMaquinaId_WhenDesplieguesExist_ShouldReturnDespliegues() {
    // Given
    when(repository.getByMaquinaId(maquinaId)).thenReturn(desplieguesList);

    // When
    GenericResponse<Object> response = service.getByMaquinaId(maquinaId);

    // Then
    assertNotNull(response);
    assertEquals("OK", response.getMessage());
    assertEquals(desplieguesList, response.getData());
    verify(repository, times(1)).getByMaquinaId(maquinaId);
  }

  @Test
  @DisplayName("getByMaquinaId - Debería retornar mensaje cuando no existen despliegues")
  void testGetByMaquinaId_WhenNoDesplieguesExist_ShouldReturnEmptyMessage() {
    // Given
    List<Despliegue> emptyList = Collections.emptyList();
    when(repository.getByMaquinaId(maquinaId)).thenReturn(emptyList);

    // When
    GenericResponse<Object> response = service.getByMaquinaId(maquinaId);

    // Then
    assertNotNull(response);
    assertEquals("No se encontraron despliegues para el maquinaId: " + maquinaId, response.getMessage());
    assertEquals(emptyList, response.getData());
    verify(repository, times(1)).getByMaquinaId(maquinaId);
  }

  @Test
  @DisplayName("createDespliegue - Debería crear despliegue exitosamente")
  void testCreateDespliegue_Success_ShouldCreateDespliegue() {
    // Given
    Despliegue nuevoDespliegue = new Despliegue();
    nuevoDespliegue.setProyectoId(proyectoId);
    nuevoDespliegue.setMaquinaId(maquinaId);

    when(repository.save(any(Despliegue.class))).thenReturn(despliegue);

    // When
    GenericResponse<Object> response = service.createDespliegue(nuevoDespliegue);

    // Then
    assertNotNull(response);
    assertEquals("Despliegue creado exitosamente", response.getMessage());
    assertEquals(despliegue, response.getData());

    // Verificar que se setean valores por defecto
    assertNotNull(nuevoDespliegue.getFechaDespliegue());
    assertTrue(nuevoDespliegue.getActivo());

    verify(repository, times(1)).save(nuevoDespliegue);
  }

  @Test
  @DisplayName("createDespliegue - Debería fallar cuando proyectoId es null")
  void testCreateDespliegue_WhenProyectoIdIsNull_ShouldReturnError() {
    // Given
    Despliegue despliegueInvalido = new Despliegue();
    despliegueInvalido.setMaquinaId(maquinaId);
    // proyectoId es null

    // When
    GenericResponse<Object> response = service.createDespliegue(despliegueInvalido);

    // Then
    assertNotNull(response);
    assertEquals("proyectoId y maquinaId son requeridos", response.getMessage());
    assertNull(response.getData());
    verify(repository, never()).save(any(Despliegue.class));
  }

  @Test
  @DisplayName("createDespliegue - Debería fallar cuando maquinaId es null")
  void testCreateDespliegue_WhenMaquinaIdIsNull_ShouldReturnError() {
    // Given
    Despliegue despliegueInvalido = new Despliegue();
    despliegueInvalido.setProyectoId(proyectoId);
    // maquinaId es null

    // When
    GenericResponse<Object> response = service.createDespliegue(despliegueInvalido);

    // Then
    assertNotNull(response);
    assertEquals("proyectoId y maquinaId son requeridos", response.getMessage());
    assertNull(response.getData());
    verify(repository, never()).save(any(Despliegue.class));
  }

  @Test
  @DisplayName("createDespliegue - Debería preservar fecha y activo si ya están seteados")
  void testCreateDespliegue_WhenFechaAndActivoAreSet_ShouldPreserveValues() {
    // Given
    Despliegue despliegueConValores = new Despliegue();
    despliegueConValores.setProyectoId(proyectoId);
    despliegueConValores.setMaquinaId(maquinaId);
    LocalDate fechaEspecifica = LocalDate.of(2024, 1, 1);
    despliegueConValores.setFechaDespliegue(fechaEspecifica);
    despliegueConValores.setActivo(false);

    when(repository.save(any(Despliegue.class))).thenReturn(despliegueConValores);

    // When
    GenericResponse<Object> response = service.createDespliegue(despliegueConValores);

    // Then
    assertNotNull(response);
    assertEquals("Despliegue creado exitosamente", response.getMessage());
    assertEquals(fechaEspecifica, despliegueConValores.getFechaDespliegue());
    assertFalse(despliegueConValores.getActivo());

    verify(repository, times(1)).save(despliegueConValores);
  }

  @Test
  @DisplayName("createDespliegue - Debería manejar excepción del repositorio")
  void testCreateDespliegue_WhenRepositoryThrowsException_ShouldReturnError() {
    // Given
    Despliegue nuevoDespliegue = new Despliegue();
    nuevoDespliegue.setProyectoId(proyectoId);
    nuevoDespliegue.setMaquinaId(maquinaId);

    String errorMessage = "Error de base de datos";
    when(repository.save(any(Despliegue.class))).thenThrow(new RuntimeException(errorMessage));

    // When
    GenericResponse<Object> response = service.createDespliegue(nuevoDespliegue);

    // Then
    assertNotNull(response);
    assertEquals("Error al crear el despliegue: " + errorMessage, response.getMessage());
    assertNull(response.getData());
    verify(repository, times(1)).save(nuevoDespliegue);
  }

  @Test
  @DisplayName("updateDespliegue - Debería actualizar despliegue exitosamente")
  void testUpdateDespliegue_Success_ShouldUpdateDespliegue() {
    // Given
    UUID nuevoProyectoId = UUID.randomUUID();
    UUID nuevaMaquinaId = UUID.randomUUID();
    LocalDate nuevaFecha = LocalDate.now().plusDays(1);

    Despliegue despliegueActualizado = new Despliegue();
    despliegueActualizado.setProyectoId(nuevoProyectoId);
    despliegueActualizado.setMaquinaId(nuevaMaquinaId);
    despliegueActualizado.setFechaDespliegue(nuevaFecha);
    despliegueActualizado.setActivo(false);

    when(repository.getById(testId)).thenReturn(despliegue);
    when(repository.save(any(Despliegue.class))).thenReturn(despliegue);

    // When
    GenericResponse<Object> response = service.updateDespliegue(testId, despliegueActualizado);

    // Then
    assertNotNull(response);
    assertEquals("Despliegue actualizado exitosamente", response.getMessage());
    assertEquals(despliegue, response.getData());

    // Verificar que se actualizaron los campos
    assertEquals(nuevoProyectoId, despliegue.getProyectoId());
    assertEquals(nuevaMaquinaId, despliegue.getMaquinaId());
    assertEquals(nuevaFecha, despliegue.getFechaDespliegue());
    assertFalse(despliegue.getActivo());

    verify(repository, times(1)).getById(testId);
    verify(repository, times(1)).save(despliegue);
  }

  @Test
  @DisplayName("updateDespliegue - Debería fallar cuando despliegue no existe")
  void testUpdateDespliegue_WhenDespliegueNotExists_ShouldReturnError() {
    // Given
    when(repository.getById(testId)).thenReturn(null);

    // When
    GenericResponse<Object> response = service.updateDespliegue(testId, despliegue);

    // Then
    assertNotNull(response);
    assertEquals("Despliegue no encontrado con id: " + testId, response.getMessage());
    assertNull(response.getData());
    verify(repository, times(1)).getById(testId);
    verify(repository, never()).save(any(Despliegue.class));
  }

  @Test
  @DisplayName("updateDespliegue - Debería actualizar solo campos no nulos")
  void testUpdateDespliegue_WhenPartialUpdate_ShouldUpdateOnlyNonNullFields() {
    // Given
    UUID originalProyectoId = despliegue.getProyectoId();
    UUID originalMaquinaId = despliegue.getMaquinaId();
    LocalDate originalFecha = despliegue.getFechaDespliegue();
    Boolean originalActivo = despliegue.getActivo();

    Despliegue actualizacionParcial = new Despliegue();
    actualizacionParcial.setProyectoId(UUID.randomUUID()); // Solo este campo se actualiza
    // Los demás campos son null

    when(repository.getById(testId)).thenReturn(despliegue);
    when(repository.save(any(Despliegue.class))).thenReturn(despliegue);

    // When
    GenericResponse<Object> response = service.updateDespliegue(testId, actualizacionParcial);

    // Then
    assertNotNull(response);
    assertEquals("Despliegue actualizado exitosamente", response.getMessage());

    // Verificar que solo se actualizó el campo no nulo
    assertEquals(actualizacionParcial.getProyectoId(), despliegue.getProyectoId());
    assertEquals(originalMaquinaId, despliegue.getMaquinaId());
    assertEquals(originalFecha, despliegue.getFechaDespliegue());
    assertEquals(originalActivo, despliegue.getActivo());

    verify(repository, times(1)).getById(testId);
    verify(repository, times(1)).save(despliegue);
  }

  @Test
  @DisplayName("updateDespliegue - Debería manejar excepción del repositorio")
  void testUpdateDespliegue_WhenRepositoryThrowsException_ShouldReturnError() {
    // Given
    when(repository.getById(testId)).thenReturn(despliegue);
    String errorMessage = "Error de base de datos";
    when(repository.save(any(Despliegue.class))).thenThrow(new RuntimeException(errorMessage));

    // When
    GenericResponse<Object> response = service.updateDespliegue(testId, despliegue);

    // Then
    assertNotNull(response);
    assertEquals("Error al actualizar el despliegue: " + errorMessage, response.getMessage());
    assertNull(response.getData());
    verify(repository, times(1)).getById(testId);
    verify(repository, times(1)).save(despliegue);
  }

  @Test
  @DisplayName("deleteDespliegue - Debería eliminar despliegue exitosamente")
  void testDeleteDespliegue_Success_ShouldDeleteDespliegue() {
    // Given
    when(repository.getById(testId)).thenReturn(despliegue);
    doNothing().when(repository).deleteById(testId);

    // When
    GenericResponse<Object> response = service.deleteDespliegue(testId);

    // Then
    assertNotNull(response);
    assertEquals("Despliegue eliminado exitosamente con id: " + testId, response.getMessage());
    assertNull(response.getData());
    verify(repository, times(1)).getById(testId);
    verify(repository, times(1)).deleteById(testId);
  }

  @Test
  @DisplayName("deleteDespliegue - Debería fallar cuando despliegue no existe")
  void testDeleteDespliegue_WhenDespliegueNotExists_ShouldReturnError() {
    // Given
    when(repository.getById(testId)).thenReturn(null);

    // When
    GenericResponse<Object> response = service.deleteDespliegue(testId);

    // Then
    assertNotNull(response);
    assertEquals("Despliegue no encontrado con id: " + testId, response.getMessage());
    assertNull(response.getData());
    verify(repository, times(1)).getById(testId);
    verify(repository, never()).deleteById(any(UUID.class));
  }

  @Test
  @DisplayName("deleteDespliegue - Debería manejar excepción del repositorio")
  void testDeleteDespliegue_WhenRepositoryThrowsException_ShouldReturnError() {
    // Given
    when(repository.getById(testId)).thenReturn(despliegue);
    String errorMessage = "Error al eliminar";
    doThrow(new RuntimeException(errorMessage)).when(repository).deleteById(testId);

    // When
    GenericResponse<Object> response = service.deleteDespliegue(testId);

    // Then
    assertNotNull(response);
    assertEquals("Error al eliminar el despliegue: " + errorMessage, response.getMessage());
    assertNull(response.getData());
    verify(repository, times(1)).getById(testId);
    verify(repository, times(1)).deleteById(testId);
  }
}
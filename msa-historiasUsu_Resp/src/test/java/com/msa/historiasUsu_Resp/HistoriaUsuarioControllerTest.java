package com.msa.historiasUsu_Resp;

import com.msa.historiasUsu_Resp.controller.HistoriaController;
import com.msa.historiasUsu_Resp.dto.GenericResponse;
import com.msa.historiasUsu_Resp.repository.model.Historia;
import com.msa.historiasUsu_Resp.service.IDHistoriaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoriaControllerTest {

    @Mock
    private IDHistoriaService service;

    @InjectMocks
    private HistoriaController historiaController;
    private AutoCloseable closeable;


    private Historia historia;
    private UUID id;
    private UUID proyectoId;
    private UUID responsableId;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        proyectoId = UUID.randomUUID();
        responsableId = UUID.randomUUID();

        historia = new Historia();
        historia.setId(id);
        historia.setProyectoId(proyectoId);
        historia.setResponsableId(responsableId);
        historia.setDescripcion("Historia de prueba");
        historia.setEstado(Historia.Estado.ACTIVO);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }


    @Test
    @DisplayName("Debería obtener todas las historias")
    void getAll() {
        // Arrange
        List<Historia> historias = Arrays.asList(historia);
        GenericResponse<Object> expectedResponse = GenericResponse.builder()
                .data(historias)
                .message("OK")
                .build();
        when(service.getAll()).thenReturn(expectedResponse);

        // Act
        ResponseEntity<GenericResponse<Object>> response = historiaController.getAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    @DisplayName("Debería obtener historia por ID")
    void getById() {
        // Arrange
        GenericResponse<Object> expectedResponse = GenericResponse.builder()
                .data(historia)
                .message("OK")
                .build();
        when(service.getById(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<GenericResponse<Object>> response = historiaController.getById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    @DisplayName("Debería obtener historias por ID de proyecto")
    void getByProyectoId() {
        // Arrange
        GenericResponse<Object> expectedResponse = GenericResponse.builder()
                .data(Arrays.asList(historia))
                .message("OK")
                .build();
        when(service.getByProyectoId(proyectoId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<GenericResponse<Object>> response = historiaController.getByProyectoId(proyectoId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    @DisplayName("Debería obtener historias por ID de responsable")
    void getByResponsableId() {
        // Arrange
        GenericResponse<Object> expectedResponse = GenericResponse.builder()
                .data(Arrays.asList(historia))
                .message("OK")
                .build();
        when(service.getByResponsableId(responsableId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<GenericResponse<Object>> response = historiaController.getByResponsableId(responsableId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    @DisplayName("Debería crear una historia exitosamente")
    void createHistoria_Success() {
        // Arrange
        GenericResponse<Object> expectedResponse = GenericResponse.builder()
                .data(historia)
                .message("Historia creada exitosamente")
                .build();
        when(service.createHistoria(any(Historia.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<GenericResponse<Object>> response = historiaController.createHistoria(historia);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    @DisplayName("Debería manejar error cuando la historia ya existe")
    void createHistoria_AlreadyExists() {
        // Arrange
        GenericResponse<Object> expectedResponse = GenericResponse.builder()
                .message("La historia ya existe")
                .build();
        when(service.createHistoria(any(Historia.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<GenericResponse<Object>> response = historiaController.createHistoria(historia);

        // Assert
        assertEquals(HttpStatus.IM_USED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    @DisplayName("Debería actualizar una historia exitosamente")
    void updateHistoria_Success() {
        // Arrange
        GenericResponse<Object> expectedResponse = GenericResponse.builder()
                .data(historia)
                .message("Historia actualizada exitosamente")
                .build();
        when(service.updateHistoria(eq(id), any(Historia.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<GenericResponse<Object>> response = historiaController.updateHistoria(id, historia);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    @DisplayName("Debería manejar error cuando la historia a actualizar no existe")
    void updateHistoria_NotFound() {
        // Arrange
        GenericResponse<Object> expectedResponse = GenericResponse.builder()
                .message("Historia no encontrada")
                .build();
        when(service.updateHistoria(eq(id), any(Historia.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<GenericResponse<Object>> response = historiaController.updateHistoria(id, historia);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    @DisplayName("Debería eliminar una historia exitosamente")
    void deleteHistoria_Success() {
        // Arrange
        GenericResponse<Object> expectedResponse = GenericResponse.builder()
                .message("Historia eliminada exitosamente")
                .build();
        when(service.deleteHistoria(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<GenericResponse<Object>> response = historiaController.deleteHistoria(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    @DisplayName("Debería manejar error cuando la historia a eliminar no existe")
    void deleteHistoria_NotFound() {
        // Arrange
        GenericResponse<Object> expectedResponse = GenericResponse.builder()
                .message("Historia no encontrada con id: " + id)
                .build();
        when(service.deleteHistoria(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<GenericResponse<Object>> response = historiaController.deleteHistoria(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse, response.getBody());
    }
}
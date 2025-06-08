package msa_proyectos.controller;

import msa_proyectos.entities.Maquina;
import msa_proyectos.entities.TipoMaquina;
import msa_proyectos.service.MaquinaService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean; // Puedes mantenerlo de momento
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


@WebMvcTest(MaquinaController.class)
public class MaquinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MaquinaService maquinaService;

    @Test
    void testGetAllMaquinas() throws Exception {
        Maquina maquina = new Maquina();
        maquina.setNombre("Servidor de Producción 1");
        maquina.setUbicacion("DataCenter Norte");
        maquina.setTipo(TipoMaquina.FISICO);

        Mockito.when(maquinaService.findAll()).thenReturn(List.of(maquina));

        mockMvc.perform(get("/api/maquinas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Servidor de Producción 1")))
                .andExpect(jsonPath("$[0].ubicacion", is("DataCenter Norte")))
                .andExpect(jsonPath("$[0].tipo", is("FISICO")));
    }

    // GET /{id}
    @Test
    void testGetMaquinaById() throws Exception {
        UUID id = UUID.randomUUID();
        Maquina maquina = new Maquina("Servidor QA", "DataCenter Sur", TipoMaquina.FISICO);

        Mockito.when(maquinaService.findById(id)).thenReturn(maquina);

        mockMvc.perform(get("/api/maquinas/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Servidor QA")))
                .andExpect(jsonPath("$.ubicacion", is("DataCenter Sur")))
                .andExpect(jsonPath("$.tipo", is("FISICO")));
    }

    // POST /
    @Test
    void testCreateMaquina() throws Exception {
        Maquina input = new Maquina("Servidor Nuevo", "DataCenter Oeste", TipoMaquina.VIRTUAL);
        Maquina saved = new Maquina("Servidor Nuevo", "DataCenter Oeste", TipoMaquina.VIRTUAL);

        Mockito.when(maquinaService.save(Mockito.any(Maquina.class))).thenReturn(saved);

        mockMvc.perform(post("/api/maquinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Servidor Nuevo")))
                .andExpect(jsonPath("$.ubicacion", is("DataCenter Oeste")))
                .andExpect(jsonPath("$.tipo", is("VIRTUAL")));
    }



    // DELETE /{id}
    @Test
    void testDeleteMaquina() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/api/maquinas/" + id))
                .andExpect(status().isOk());
    }
}

package msa_proyectos.service;

import msa_proyectos.entities.Maquina;
import msa_proyectos.entities.TipoMaquina;
import msa_proyectos.persistence.MaquinaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MaquinaServiceTest {

    @Mock
    private MaquinaRepository maquinaRepository;

    @InjectMocks
    private MaquinaService maquinaService;

    @Test
    void testObtenerTodasLasMaquinas() {
        List<Maquina> lista = List.of(new Maquina("Servidor A", "Ubicación X", TipoMaquina.FISICO));
        Mockito.when(maquinaRepository.findAll()).thenReturn(lista);

        List<Maquina> resultado = maquinaService.findAll();

        assertEquals(1, resultado.size());
        assertEquals("Servidor A", resultado.get(0).getNombre());
    }
}


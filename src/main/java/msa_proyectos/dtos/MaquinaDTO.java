package msa_proyectos.dtos;

import lombok.Builder;
import lombok.Data;
import msa_proyectos.entities.TipoMaquina;

import java.util.UUID;

@Data
@Builder
public class MaquinaDTO {
    private UUID id;
    private String nombre;
    private String ubicacion;
    private TipoMaquina tipo;
}

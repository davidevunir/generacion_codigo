package msa_proyectos.entities;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "maquina")
public class Maquina {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String nombre;

    private String ubicacion;

    @Enumerated(EnumType.STRING)
    private TipoMaquina tipo;
}

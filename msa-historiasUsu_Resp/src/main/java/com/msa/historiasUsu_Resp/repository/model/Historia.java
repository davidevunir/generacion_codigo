package com.msa.historiasUsu_Resp.repository.model;


import jakarta.persistence.*;
import lombok.experimental.FieldDefaults;
import java.util.UUID;
import static jakarta.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PRIVATE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity
@Table(name = "HistoriaUsu")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Historia {

    @Id
    @GeneratedValue(strategy = AUTO)
    UUID id;

    UUID proyectoId;

    UUID responsableId;

    String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    public enum Estado {
        ACTIVO,
        INACTIVO,
        PENDIENTE,
        COMPLETADO
    }
}

package com.msa.historiasUsu_Resp.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.experimental.FieldDefaults;
import java.util.UUID;
import static jakarta.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PRIVATE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    Enum estado;


}
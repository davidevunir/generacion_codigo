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
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setTipo(TipoMaquina tipo) {
        this.tipo = tipo;
    }
    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public TipoMaquina getTipo() {
        return tipo;
    }
    public Maquina() {
        // Constructor sin argumentos requerido por JPA y para tests simples
    }

    public Maquina(String nombre, String ubicacion, TipoMaquina tipo) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
    }
}

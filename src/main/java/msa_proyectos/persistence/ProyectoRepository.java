package msa_proyectos.persistence;

import msa_proyectos.entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProyectoRepository extends JpaRepository<Proyecto, UUID> {
}
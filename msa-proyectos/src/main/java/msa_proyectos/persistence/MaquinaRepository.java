package msa_proyectos.persistence;

import msa_proyectos.entities.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaquinaRepository extends JpaRepository<Maquina, UUID> {
}

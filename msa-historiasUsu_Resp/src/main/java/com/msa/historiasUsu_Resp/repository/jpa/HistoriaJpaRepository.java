package com.msa.historiasUsu_Resp.repository.jpa;

import com.msa.historiasUsu_Resp.repository.model.Historia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;
import java.util.List;

@Repository
public interface HistoriaJpaRepository extends ListCrudRepository<Historia, UUID> {
   List<Historia> findByProyectoId(UUID proyectoId);
   List<Historia> findByResponsableId(UUID responsableId);

}
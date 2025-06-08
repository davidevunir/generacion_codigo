package com.msa.historiasUsu_Resp.repository.jpa;

import com.msa.historiasUsu_Resp.repository.model.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, UUID> {
}

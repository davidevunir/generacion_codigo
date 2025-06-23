package msa_proyectos.service;

import msa_proyectos.entities.Maquina;
import org.springframework.stereotype.Service;
import msa_proyectos.persistence.MaquinaRepository;

import java.util.List;
import java.util.UUID;
@Service
public class MaquinaService {
    private final MaquinaRepository maquinaRepository;

    public MaquinaService(MaquinaRepository maquinaRepository) {
        this.maquinaRepository = maquinaRepository;
    }

    public List<Maquina> findAll() {
        return maquinaRepository.findAll();
    }

    public Maquina save(Maquina maquina) {
        return maquinaRepository.save(maquina);
    }

    public Maquina findById(UUID id) {
        return maquinaRepository.findById(id).orElseThrow(() -> new RuntimeException("Máquina no encontrada"));
    }

    public void delete(UUID id) {
        maquinaRepository.deleteById(id);
    }
}

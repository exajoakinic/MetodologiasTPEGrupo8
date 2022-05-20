package com.turno.facil.services;

import com.turno.facil.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicoService extends BaseService<Medico> {

    public MedicoService(JpaRepository<Medico, Long> repository) {
        super(repository);
    }
}

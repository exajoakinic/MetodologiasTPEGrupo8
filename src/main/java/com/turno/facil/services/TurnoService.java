package com.turno.facil.services;

import com.turno.facil.models.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TurnoService extends BaseService<Turno> {

    public TurnoService(JpaRepository<Turno, Long> repository) {
        super(repository);
    }
}

package com.turno.facil.services;

import com.turno.facil.models.Turno;
import com.turno.facil.repositories.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TurnoService extends BaseService<Turno> {
    private TurnoRepository turnoRepository;
    @Autowired
    public TurnoService(JpaRepository<Turno, Long> repository, TurnoRepository turnoRepository) {
        super(repository);
        this.turnoRepository = turnoRepository;
    }

    public List<Turno> findByDates(LocalDate from, LocalDate to) {
        return turnoRepository.findByDates(from, to);
    }
}

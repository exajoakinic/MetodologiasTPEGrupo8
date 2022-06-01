package com.turno.facil.repositories;

import com.turno.facil.models.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    /* Query para encontrar todos los elementos en un rango de fechas. */
    @Query("SELECT t FROM Turno t WHERE t.fecha >= date(:dateFrom) AND t.fecha <= date(:dateTo) + 1 AND t.disponible = true")
    public List<Turno> findByDates(@Param("dateFrom") LocalDate from, @Param("dateTo") LocalDate to);
}

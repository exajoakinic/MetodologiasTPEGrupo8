package com.turno.facil.controllers;

import com.turno.facil.models.BetweenDatesDTO;
import com.turno.facil.models.Turno;
import com.turno.facil.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    /* Define endpoint para obtener todos los turnos */
    @GetMapping("/")
    public List<Turno> findAll() {
        return turnoService.findAll();
    }

    /* Define endpoint para obtener un turno por id */
    @GetMapping("/{id}")
    public Turno findById(@PathVariable Long id) {
        return turnoService.findById(id);
    }

    /* Define endpoint para actualizar un turno */
    @PutMapping("/{id}")
    public Turno update(@RequestBody Turno turno, @PathVariable Long id) {
        return turnoService.update(turno, id);
    }

    /* Define endpoint para guardar un turno */
    @PostMapping("/")
    public Turno save(@RequestBody Turno turno) {
        return turnoService.save(turno);
    }

    /* Define endpoint para borrar turno por id */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        turnoService.delete(id);
    }

    /* Define endpoint para obtener todos los turnos en un rango de fechas */
    @GetMapping("/turnosdisponibles/{from}/{to}")
    public List<Turno> findByDates(@PathVariable String from, @PathVariable String to) {
        return turnoService.findByDates(LocalDate.parse(from), LocalDate.parse(to));
    }


}

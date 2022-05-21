package com.turno.facil.controllers;

import com.turno.facil.models.BetweenDatesDTO;
import com.turno.facil.models.Turno;
import com.turno.facil.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @GetMapping("/")
    public List<Turno> findAll() {
        return turnoService.findAll();
    }

    @GetMapping("/{id}")
    public Turno findById(@PathVariable Long id) {
        return turnoService.findById(id);
    }

    @PutMapping("/{id}")
    public Turno update(@RequestBody Turno turno, @PathVariable Long id) {
        return turnoService.update(turno, id);
    }

    @PostMapping("/")
    public Turno save(@RequestBody Turno turno) {
        return turnoService.save(turno);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        turnoService.delete(id);
    }

    @PostMapping("/disponiblesentrefechas")
    public Turno findByDates(@PathVariable Long id) {
        return turnoService.findById(id);
    }

    @GetMapping("/entrefechas")
    public List<Turno> findByDates(@RequestBody BetweenDatesDTO betweenDates) {
        return turnoService.findByDates(betweenDates.getFrom(), betweenDates.getTo());
    }



}

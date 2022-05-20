package com.turno.facil.controllers;

import com.turno.facil.models.Turno;
import com.turno.facil.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}

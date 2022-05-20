package com.turno.facil.controllers;

import com.turno.facil.models.Medico;
import com.turno.facil.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/")
    public List<Medico> findAll() {
        return medicoService.findAll();
    }

    @GetMapping("/{id}")
    public Medico findById(@PathVariable Long id) {
        return medicoService.findById(id);
    }

    @PutMapping("/{id}")
    public Medico update(@RequestBody Medico medico, @PathVariable Long id) {
        return medicoService.update(medico, id);
    }

    @PostMapping("/")
    public Medico save(@RequestBody Medico medico) {
        return medicoService.save(medico);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        medicoService.delete(id);
    }
}

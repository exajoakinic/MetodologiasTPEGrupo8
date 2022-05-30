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

    /* Define endpoint para encontrar todos los medicos */
    @GetMapping("/")
    public List<Medico> findAll() {
        return medicoService.findAll();
    }

    /* Define endpoint para encontrar medico por id */
    @GetMapping("/{id}")
    public Medico findById(@PathVariable Long id) {
        return medicoService.findById(id);
    }

    /* Define endpoint para actualizar un medico por id */
    @PutMapping("/{id}")
    public Medico update(@RequestBody Medico medico, @PathVariable Long id) {
        return medicoService.update(medico, id);
    }

    /* Define endpoint para guardar un medico */
    @PostMapping("/")
    public Medico save(@RequestBody Medico medico) {
        return medicoService.save(medico);
    }

    /* Define endpoint para eliminar un medico por id */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        medicoService.delete(id);
    }
}

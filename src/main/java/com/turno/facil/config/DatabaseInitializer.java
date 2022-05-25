package com.turno.facil.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turno.facil.TurnoFacilApplication;
import com.turno.facil.models.Medico;
import com.turno.facil.models.Turno;
import com.turno.facil.services.MedicoService;
import com.turno.facil.services.TurnoService;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private MedicoService medicoService;
    @Autowired
    private TurnoService turnoService;

    @Override
    public void run(String... args) throws Exception {
        initMedicos();
        initTurnos();
    }

    public void initTurnos() {
        Turno t1 = Turno.builder()
                .disponible(true)
                .fecha(LocalDateTime.of(2022, 05, 13, 14, 30))
                .build();
        Turno t2 = Turno.builder()
                .disponible(false)
                .fecha(LocalDateTime.of(2022, 05, 13, 15, 00))
                .build();
        Turno t3 = Turno.builder()
                .disponible(false)
                .fecha(LocalDateTime.of(2022, 05, 13, 15, 30))
                .build();
        Turno t4 = Turno.builder()
                .disponible(true)
                .fecha(LocalDateTime.of(2022, 05, 13, 16, 00))
                .build();

        this.turnoService.save(t1);
        this.turnoService.save(t2);
        this.turnoService.save(t3);
        this.turnoService.save(t4);
    }

    public void initMedicos() {
        Medico m1 = Medico.builder()
                .nombre("Juan")
                .apellido("Martin")
                .build();
        Medico m2 = Medico.builder()
                .nombre("Pedro")
                .apellido("Gonzalez")
                .build();
        Medico m3 = Medico.builder()
                .nombre("Jefferson")
                .apellido("Gutierrito")
                .build();
        Medico m4 = Medico.builder()
                .nombre("Andromeda")
                .apellido("Melancula")
                .build();

        this.medicoService.save(m1);
        this.medicoService.save(m2);
        this.medicoService.save(m3);
        this.medicoService.save(m4);
    }
}

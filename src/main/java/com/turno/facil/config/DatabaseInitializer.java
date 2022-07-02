package com.turno.facil.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turno.facil.models.Medico;
import com.turno.facil.models.Turno;
import com.turno.facil.services.MedicoService;
import com.turno.facil.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
/* Initializer de algunos elementos para la bbdd. */
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private MedicoService medicoService;
    @Autowired
    private TurnoService turnoService;
    private static Logger log = Logger.getLogger(DatabaseInitializer.class.getName());

    @Override
    public void run(String... args) throws Exception {
        initMedicosAndTurnos();
    }

    /* Inicializa turnos a partir de un archivo .json y los guarda en la bbdd. */
    void mockDataLoader(List<Medico> medicos){
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Turno>> typeReference = new TypeReference<List<Turno>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/mock_data.json");
            try {
                List<Turno> turnos = mapper.readValue(inputStream,typeReference);
                int id = 0;
                for (Turno turno : turnos) {
                    turno.setMedico(medicos.get(id));
                    id++;
                    if (id == medicos.size()) {
                        id = 0;
                    }
                }
                turnoService.save(turnos);
                log.info("Mock data parsed and saved successfully.");
            } catch (IOException e){
                log.info("Error parsing mock data: " + e.getMessage());
            }
    }

    //Se inicializan médicos para testing
    public void initMedicosAndTurnos() {
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
        ArrayList<Medico> m = new ArrayList<>();
        m.add(m1);
        m.add(m2);
        m.add(m3);
        m.add(m4);
        mockDataLoader(m);
    }
}

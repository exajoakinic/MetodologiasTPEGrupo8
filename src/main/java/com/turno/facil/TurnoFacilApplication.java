package com.turno.facil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turno.facil.models.Turno;
import com.turno.facil.services.TurnoService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class TurnoFacilApplication {

	private static Logger log = Logger.getLogger(TurnoFacilApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(TurnoFacilApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(TurnoService turnoService){
		return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Turno>> typeReference = new TypeReference<List<Turno>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/mock_data.json");
			System.out.println(inputStream);
			try {
				List<Turno> users = mapper.readValue(inputStream,typeReference);
				turnoService.save(users);
				log.info("Mock data parsed and saved successfully.");
			} catch (IOException e){
				log.info("Error parsing mock data: " + e.getMessage());
			}
		};
	}
}

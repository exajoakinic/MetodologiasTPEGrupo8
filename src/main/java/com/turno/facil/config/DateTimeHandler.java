package com.turno.facil.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.time.LocalDateTime;


public class DateTimeHandler extends StdDeserializer<LocalDateTime> {

    public DateTimeHandler() {
        this(null);
    }

    protected DateTimeHandler(Class<?> vc) {
        super(vc);
    }

    /* Esto es para procesar los datos cargados, al inicializar el server, en el archivo mock_data.json */
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String dateTime = jsonParser.getText();
        return LocalDateTime.parse(dateTime);
    }
}

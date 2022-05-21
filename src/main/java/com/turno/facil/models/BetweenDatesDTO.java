package com.turno.facil.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BetweenDatesDTO {
    private LocalDate from;
    private LocalDate to;
}

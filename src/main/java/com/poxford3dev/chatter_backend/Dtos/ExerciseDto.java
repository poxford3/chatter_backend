package com.poxford3dev.chatter_backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ExerciseDto {
    private String name;
    private Integer reps;
    private Integer sets;
    private BigDecimal weight;
}

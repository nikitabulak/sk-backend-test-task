package com.nikitabulak.testtaskskbackend.modify.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Data
public class RequestDto {
    private int id;
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10000.0")
    private double add;
}

package com.elleined.locationapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDTO {
    private int id;

    @NotBlank(message = "City name must not be blank")
    private String name;

    @Positive(message = "Zip code must be positive!")
    private int zipCode;

    @Positive(message = "Province id must be positive!")
    private int provinceId;

    private String provinceName;

    private int baranggayCount;
}

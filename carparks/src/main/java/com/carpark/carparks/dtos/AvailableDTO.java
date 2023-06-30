package com.carpark.carparks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDTO {
    private String address;
    private String coordinateAsText;
    private Integer totalLots;
    private Integer lotsAvailable;
    private Double distance;
}

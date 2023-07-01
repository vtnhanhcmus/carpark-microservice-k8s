package com.carpark.carparks.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarParkDto {
    @JsonProperty("car_park_no")
    private String carParkNo;
    @JsonProperty("address")
    private String address;
    @JsonProperty("x_coord")
    private Double xCoord;
    @JsonProperty("y_coord")
    private Double yCoord;
}

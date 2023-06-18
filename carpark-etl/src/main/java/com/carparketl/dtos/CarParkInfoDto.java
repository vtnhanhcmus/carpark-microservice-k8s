package com.carparketl.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarParkInfoDto {

    @JsonProperty("total_lots")
    private Integer totalLots;

    @JsonProperty("lot_type")
    private String lotType;

    @JsonProperty("lots_available")
    private Integer lotsAvailable;
}

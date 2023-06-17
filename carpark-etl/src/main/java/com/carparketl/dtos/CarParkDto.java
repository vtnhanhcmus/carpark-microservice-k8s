package com.carparketl.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarParkDto {
    private String carParkNo;
    private String address;
    private String xCoord;
    private String yCoord;
    private String carParkType;
    private String typeOfParkingSystem;
    private String shortTermParking;
    private String freeParking;
    private String nightParking;
    private String carParkDecks;
    private String gantryHeight;
    private String carParkBasement;

    public String getCoordinate() {
        return xCoord + ", " + yCoord;
    }
}

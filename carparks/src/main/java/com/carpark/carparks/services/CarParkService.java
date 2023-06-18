package com.carpark.carparks.services;

import com.carpark.carparks.dtos.CarParkDTO;
import com.carpark.carparks.entities.CarPark;
import com.carpark.carparks.repositories.CarParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarParkService {

    @Autowired
    private CarParkRepository carParkRepository;

    public CarParkDTO findCarParkDetail(String carParkNo){
        CarPark carPark = carParkRepository.findByCarParkNo(carParkNo);
        if (carPark != null){
            return CarParkDTO.builder()
                    .carParkNo(carPark.getCarParkNo())
                    .address(carPark.getAddress())
                    .xCoord(carPark.getCoordinate().getX())
                    .yCoord(carPark.getCoordinate().getY())
                    .build();
        }
        return null;
    }
}

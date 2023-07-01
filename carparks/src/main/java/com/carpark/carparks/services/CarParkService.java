package com.carpark.carparks.services;

import com.carpark.carparks.dtos.CarParkDTO;
import com.carpark.carparks.entities.CarPark;
import com.carpark.carparks.repositories.CarParkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CarParkService {

    @Autowired
    private CarParkRepository carParkRepository;

    @Cacheable(value = "car_park_info")
    public CarParkDTO findCarParkDetail(String carParkNo){
        log.info("start get info car park no {}", carParkNo);
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

package com.carpark.carparks.services;

import com.carpark.carparks.dtos.CarParkDto;
import com.carpark.carparks.entities.CarPark;
import com.carpark.carparks.repositories.CarParkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class CarParkService {

    @Autowired
    private CarParkRepository carParkRepository;

    @Cacheable(value = "car_park_info")
    public CarParkDto findCarParkDetail(String carParkNo){
        log.info("start get info car park no {}", carParkNo);
        CarPark carPark = carParkRepository.findByCarParkNo(carParkNo);
        if (carPark != null){
            return CarParkDto.builder()
                    .carParkNo(carPark.getCarParkNo())
                    .address(carPark.getAddress())
                    .xCoord(carPark.getCoordinate().getX())
                    .yCoord(carPark.getCoordinate().getY())
                    .build();
        }
        return null;
    }

    public List<CarParkDto> listCarPark(){
        Iterable<CarPark> carParks = carParkRepository.findAll();
        List<CarParkDto> carParkDtos = new ArrayList<>();
        carParks.forEach(c -> {
            carParkDtos.add(CarParkDto.builder().carParkNo(c.getCarParkNo()).address(c.getAddress())
                    .xCoord(c.getCoordinate().getX())
                    .yCoord(c.getCoordinate().getY())
                    .build());
        });
        return carParkDtos;
    }
}

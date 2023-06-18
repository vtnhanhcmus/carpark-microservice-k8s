package com.carpark.carparks.repositories;

import com.carpark.carparks.entities.CarPark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarParkRepository extends CrudRepository<CarPark, Long> {
    CarPark findByCarParkNo(String carParkNo);
}

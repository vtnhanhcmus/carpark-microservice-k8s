package com.carparketl;

import com.carparketl.entities.CarPark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarParkRepository extends CrudRepository<CarPark, Long> {
}

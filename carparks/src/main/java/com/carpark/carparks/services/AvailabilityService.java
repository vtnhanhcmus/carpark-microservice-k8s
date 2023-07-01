package com.carpark.carparks.services;

import com.carpark.carparks.entities.Availability;
import com.carpark.carparks.repositories.AvailabilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Cacheable(value = "car_park_no_slot")
    public Boolean checkSlot(String carParkNo){
        log.info("check slot car park no {}", carParkNo);
        Availability availability = availabilityRepository.findByCarParkNo(carParkNo);
        return availability.getLotsAvailable() > 0;
    }

    @CacheEvict(value = "car_park_no_slot", key = "#carParkNo")
    public void updateSlot(String carParkNo){
        log.info("update slot for car park no {}", carParkNo);
        Availability availability = availabilityRepository.findByCarParkNo(carParkNo);
        availability.setLotsAvailable(availability.getLotsAvailable() + 1);
        availabilityRepository.save(availability);
    }
}

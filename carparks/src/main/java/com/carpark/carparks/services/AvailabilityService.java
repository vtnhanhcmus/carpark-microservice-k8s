package com.carpark.carparks.services;

import com.carpark.carparks.entities.Availability;
import com.carpark.carparks.repositories.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public Boolean checkSlot(String carParkNo){
        Availability availability = availabilityRepository.findByCarParkNo(carParkNo);
        return availability.getLotsAvailable() > 0;
    }

    public void updateSlot(String carParkNo){
        Availability availability = availabilityRepository.findByCarParkNo(carParkNo);
        availability.setLotsAvailable(availability.getLotsAvailable() + 1);
        availabilityRepository.save(availability);
    }
}

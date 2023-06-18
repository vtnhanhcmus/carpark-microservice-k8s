package com.carparketl.processers;

import com.carparketl.commons.GeometryUtil;
import com.carparketl.dtos.AvailabilityDto;
import com.carparketl.dtos.CarParkDto;
import com.carparketl.entities.Availability;
import com.carparketl.entities.CarPark;
import com.carparketl.repositories.AvailabilityRepository;
import com.carparketl.repositories.CarParkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Component
@Slf4j
public class AvailabilityProcessor implements ItemProcessor<AvailabilityDto, Availability> {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private CarParkRepository carParkRepository;


    @Override
    public Availability process(AvailabilityDto availabilityDto){
        log.info("processing convert dto to entity");

        Availability availability = availabilityRepository.findByCarParkNo(availabilityDto.getCarParkNo());
        if (availability == null){
            availability = new Availability();
            CarPark carPark = carParkRepository.findByCarParkNo(availabilityDto.getCarParkNo());
            if (carPark == null){
                return null;
            }
            availability.setCarPark(carPark);
            availability.setTotalLots(availabilityDto.getTotalLots());
            availability.setLotsAvailable(availabilityDto.getLotsAvailable());
        }
        availability.setTotalLots(availabilityDto.getTotalLots());
        availability.setLotsAvailable(availabilityDto.getLotsAvailable());
        availability.setLastUpdatedOn(new Date());
        return availability;
    }
}

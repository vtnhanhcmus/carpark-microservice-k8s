package com.carparketl.writers;

import com.carparketl.entities.Availability;
import com.carparketl.repositories.AvailabilityRepository;
import com.carparketl.utils.CarParkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AvailabilityWriter implements ItemWriter<Availability> {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Override
    public void write(List<? extends Availability> list){
        log.info("processing write availability info");
        List<Availability> data = list.stream().filter(i -> i != null).filter(CarParkUtils.distinctByKey(Availability::getCarPark)).collect(Collectors.toList());
        availabilityRepository.saveAll(data);
    }

}

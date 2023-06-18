package com.carparketl.writers;

import com.carparketl.entities.Availability;
import com.carparketl.repositories.AvailabilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AvailabilityWriter implements ItemWriter<Availability> {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Override
    public void write(List<? extends Availability> list){
        log.info("processing write availability info");
        availabilityRepository.saveAll(list);
    }
}

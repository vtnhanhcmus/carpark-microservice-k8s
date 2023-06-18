package com.carparketl.writers;

import com.carparketl.repositories.CarParkRepository;
import com.carparketl.entities.CarPark;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CarParkInfoWriter implements ItemWriter<CarPark> {

    @Autowired
    private CarParkRepository carParkRepository;

    @Override
    public void write(List<? extends CarPark> list){
        log.info("processing write car park info");
        carParkRepository.saveAll(list);
    }
}

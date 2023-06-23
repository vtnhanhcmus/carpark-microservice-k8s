package com.carparketl.writers;

import com.carparketl.repositories.CarParkRepository;
import com.carparketl.entities.CarPark;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CarParkInfoWriter implements ItemWriter<CarPark> {

    @Autowired
    private CarParkRepository carParkRepository;


    @Override
    public void write(Chunk<? extends CarPark> chunk){
        log.info("processing write car park info");
        carParkRepository.saveAll(chunk.getItems());
    }

}

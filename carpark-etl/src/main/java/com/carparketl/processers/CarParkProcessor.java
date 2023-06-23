package com.carparketl.processers;

import com.carparketl.commons.GeometryUtil;
import com.carparketl.dtos.CarParkDto;
import com.carparketl.entities.CarPark;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class CarParkProcessor implements ItemProcessor<CarParkDto, CarPark> {

    @Override
    public CarPark process(CarParkDto carParkDto){
        log.info("processing convert dto to entity");
        return CarPark.builder()
                .id(new Random().nextLong())
                .carParkNo(carParkDto.getCarParkNo())
                .address(carParkDto.getAddress())
                .coordinate(GeometryUtil.convertCoordinateToPoint(Double.parseDouble(carParkDto.getXCoord()), Double.parseDouble(carParkDto.getYCoord())))
                .build();
    }
}

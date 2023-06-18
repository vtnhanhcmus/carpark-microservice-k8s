package com.carparketl.readers;

import com.carparketl.dtos.AvailabilityDto;
import com.carparketl.dtos.AvailabilityResponse;
import com.carparketl.dtos.CarParkDataDto;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvailabilityReader implements ItemReader<AvailabilityDto> {

    @Autowired
    private final RestTemplate restTemplate;
    @Value("${car.park.availability.rest.endpoint}")
    private String availabilityEndpoint;
    private List<AvailabilityDto> data;
    private Integer nextItem = 0;

    public AvailabilityReader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public AvailabilityDto read(){

        if (CollectionUtils.isEmpty(data)){
            ResponseEntity<AvailabilityResponse> response = restTemplate.getForEntity(availabilityEndpoint, AvailabilityResponse.class);
            if (response == null) {
                data = Collections.EMPTY_LIST;
            }
            List<CarParkDataDto> carParkData = response.getBody().getItems().get(0).getCarParkData();
            data = carParkData.stream().map(this::convertToAvailability).collect(Collectors.toList());
        }

        AvailabilityDto nextAvailability = null;
        if (nextItem < data.size()){
            nextAvailability = data.get(nextItem++);
        }else {
            nextItem = 0;
            data = null;
        }
        return nextAvailability;
    }

    private AvailabilityDto convertToAvailability(CarParkDataDto carParkDataDto){
        return AvailabilityDto.builder().carParkNo(carParkDataDto.getCarParkNumber())
                .totalLots(carParkDataDto.getCarParkInfo().get(0).getTotalLots())
                .lotsAvailable(carParkDataDto.getCarParkInfo().get(0).getLotsAvailable())
                .build();
    }

}

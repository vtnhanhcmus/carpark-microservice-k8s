package com.carpark.carparks.controllers;

import com.carpark.carparks.config.CarparkProperties;
import com.carpark.carparks.dtos.CarParkDTO;
import com.carpark.carparks.services.CarParkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("carparks")
@Slf4j
public class CarParkController {

    @Autowired
    private CarParkService carParkService;

    @Autowired
    private CarparkProperties carparkProperties;
    @GetMapping("{parkNo}/detail")
    public CarParkDTO carParkDetail(@PathVariable("parkNo") String parkNo) {
        return carParkService.findCarParkDetail(parkNo);
    }

    @GetMapping("/config")
    public String testProperty(){
        log.info(carparkProperties.getMgs());
        return carparkProperties.toString();
    }

}

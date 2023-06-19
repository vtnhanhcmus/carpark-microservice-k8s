package com.carpark.carparks.controllers;

import com.carpark.carparks.dtos.CarParkDTO;
import com.carpark.carparks.services.CarParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("carparks")
public class CarParkController {

    @Autowired
    private CarParkService carParkService;

    @GetMapping("{parkNo}/detail")
    public CarParkDTO carParkDetail(@PathVariable("parkNo") String parkNo) {
        return carParkService.findCarParkDetail(parkNo);
    }

}

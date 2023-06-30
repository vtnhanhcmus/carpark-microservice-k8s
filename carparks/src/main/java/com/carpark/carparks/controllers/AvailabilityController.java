package com.carpark.carparks.controllers;

import com.carpark.carparks.services.AvailabilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("availability")
@Slf4j
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping("{parkNo}/checkSlot")
    public Boolean checkSlot(@PathVariable("parkNo") String parkNo) {

        return availabilityService.checkSlot(parkNo);
    }

    @PostMapping("{parkNo}/updateSlot")
    public ResponseEntity<Void> createBooking(@PathVariable("parkNo") String parkNo){
        log.info("process update slot ");
        availabilityService.updateSlot(parkNo);
        return ResponseEntity.noContent().build();
    }

}

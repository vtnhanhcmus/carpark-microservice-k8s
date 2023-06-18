package com.carpark.bookings.controllers;

import com.carpark.bookings.config.BookingProperties;
import com.carpark.bookings.models.Booking;
import com.carpark.bookings.services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    BookingProperties bookingProperties;

    @GetMapping("{id}")
    public Booking getAccountDetails(@PathVariable("id") Long id) {
        return bookingService.findById(id);
    }

    @GetMapping("account/config")
    public String testProperty(){
        log.info(bookingProperties.getMgs());
        return bookingProperties.toString();
    }

}

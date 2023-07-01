package com.carpark.bookings.controllers;

import com.carpark.bookings.config.BookingProperties;
import com.carpark.bookings.dtos.BookingDetailDto;
import com.carpark.bookings.dtos.BookingDto;
import com.carpark.bookings.services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    BookingProperties bookingProperties;

    @GetMapping("{id}")
    public BookingDto getBookingDetails(@PathVariable("id") Long id) {
        return bookingService.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<BookingDetailDto> createBooking(@RequestBody BookingDto bookingRequest){
        return ResponseEntity.ok(bookingService.createBooking(bookingRequest));
    }

    @GetMapping("/config")
    public String testProperty(){
        log.info(bookingProperties.getMgs());
        return bookingService.toString();
    }

}

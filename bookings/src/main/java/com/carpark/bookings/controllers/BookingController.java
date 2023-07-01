package com.carpark.bookings.controllers;

import com.carpark.bookings.config.BookingProperties;
import com.carpark.bookings.dtos.BookingDetailDto;
import com.carpark.bookings.dtos.BookingDto;
import com.carpark.bookings.services.BookingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

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
    @CircuitBreaker(name = "booking", fallbackMethod = "bookingFallBackMethod")
    @TimeLimiter(name = "booking")
    @Retry(name = "booking")
    public CompletableFuture<BookingDetailDto> createBooking(@RequestBody BookingDto bookingRequest){
        return CompletableFuture.supplyAsync(() -> bookingService.createBooking(bookingRequest));
    }

    public CompletableFuture<String> bookingFallBackMethod(BookingDto bookingRequest){
        log.info("Cannot Place Order Executing Fallback logic");
        return CompletableFuture.supplyAsync(() -> "Something went wrong with your booking with car park info " + bookingRequest.getCarParkNo());
    }

    @GetMapping("/config")
    public String testProperty(){
        log.info(bookingProperties.getMgs());
        return bookingService.toString();
    }

}

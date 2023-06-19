package com.carpark.bookings.controllers;

import com.carpark.bookings.config.BookingProperties;
import com.carpark.bookings.controllers.feign.AccountFeignService;
import com.carpark.bookings.controllers.feign.CarParkFeignService;
import com.carpark.bookings.dtos.AccountDto;
import com.carpark.bookings.dtos.BookingDetailDto;
import com.carpark.bookings.dtos.BookingDto;
import com.carpark.bookings.dtos.CarParkDTO;
import com.carpark.bookings.models.Booking;
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

    @Autowired
    AccountFeignService accountFeignService;

    @Autowired
    CarParkFeignService carParkFeignService;

    @GetMapping("{id}")
    public BookingDto getBookingDetails(@PathVariable("id") Long id) {
        return bookingService.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<BookingDetailDto> createBooking(@RequestBody BookingDto bookingRequest){
        BookingDto bookingDto = bookingService.createBooking(bookingRequest);
        CarParkDTO carParkDto = carParkFeignService.getDetailCarPark(bookingRequest.getCarParkNo());
        AccountDto accountDto = accountFeignService.getDetailAccount(bookingDto.getAccountId());
        BookingDetailDto bookingDetailDto = BookingDetailDto.builder().booking(bookingDto).carPark(carParkDto).account(accountDto).build();
        return ResponseEntity.ok(bookingDetailDto);
    }


}

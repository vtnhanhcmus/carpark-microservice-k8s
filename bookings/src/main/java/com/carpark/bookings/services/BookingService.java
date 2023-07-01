package com.carpark.bookings.services;

import com.carpark.bookings.dtos.AccountDto;
import com.carpark.bookings.dtos.BookingDetailDto;
import com.carpark.bookings.dtos.BookingDto;
import com.carpark.bookings.dtos.CarParkDTO;
import com.carpark.bookings.models.Booking;
import com.carpark.bookings.repositories.BookingRepository;
import com.carpark.bookings.services.feign.AccountFeignService;
import com.carpark.bookings.services.feign.CarParkFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AccountFeignService accountFeignService;

    @Autowired
    private CarParkFeignService carParkFeignService;

    public BookingDto findById(Long id){
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()){
            return convertToDto(booking.get());
        }
        return null;
    }

    public BookingDetailDto createBooking(BookingDto bookingRequest){
        log.info("process create booking");

        Boolean hasSlot = carParkFeignService.checkSlot(bookingRequest.getCarParkNo());
        if (hasSlot){
            CarParkDTO carParkDto = carParkFeignService.getDetailCarPark(bookingRequest.getCarParkNo());
            AccountDto accountDto = accountFeignService.getDetailAccount(bookingRequest.getAccountId());
            carParkFeignService.updateSlot(bookingRequest.getCarParkNo());
            Booking booking = convertToEntity(bookingRequest);
            bookingRepository.save(booking);
            return BookingDetailDto.builder().booking(convertToDto(booking)).carPark(carParkDto).account(accountDto).build();
        }

        return null;

    }

    private BookingDto convertToDto(Booking booking){
        return BookingDto.builder().id(booking.getId()).accountId(booking.getAccountId()).carParkNo(booking.getCarParkNo()).quantity(booking.getQuantity()).createdDate(booking.getCreatedDate()).build();
    }

    private Booking convertToEntity(BookingDto bookingDto){
        return Booking.builder().id(bookingDto.getId()).accountId(bookingDto.getAccountId()).carParkNo(bookingDto.getCarParkNo()).quantity(bookingDto.getQuantity()).createdDate(bookingDto.getCreatedDate()).createdDate(new Date()).build();
    }

}

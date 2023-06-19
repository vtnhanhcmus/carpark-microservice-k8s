package com.carpark.bookings.services;

import com.carpark.bookings.dtos.BookingDto;
import com.carpark.bookings.models.Booking;
import com.carpark.bookings.repositories.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public BookingDto findById(Long id){
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()){
            return convertToDto(booking.get());
        }
        return null;
    }

    public BookingDto createBooking(BookingDto bookingDto){
        log.info("process create booking");
        Booking booking = convertToEntity(bookingDto);
        bookingRepository.save(booking);
        return convertToDto(booking);
    }

    private BookingDto convertToDto(Booking booking){
        return BookingDto.builder().id(booking.getId()).accountId(booking.getAccountId()).carParkNo(booking.getCarParkNo()).quantity(booking.getQuantity()).createdDate(booking.getCreatedDate()).build();
    }

    private Booking convertToEntity(BookingDto bookingDto){
        return Booking.builder().id(bookingDto.getId()).accountId(bookingDto.getAccountId()).carParkNo(bookingDto.getCarParkNo()).quantity(bookingDto.getQuantity()).createdDate(bookingDto.getCreatedDate()).createdDate(new Date()).build();
    }

}

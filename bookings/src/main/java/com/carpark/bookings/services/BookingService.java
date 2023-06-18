package com.carpark.bookings.services;

import com.carpark.bookings.models.Booking;
import com.carpark.bookings.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking findById(Long id){
        Optional<Booking> account = bookingRepository.findById(id);
        return account.isPresent() ? account.get() : null;
    }

}

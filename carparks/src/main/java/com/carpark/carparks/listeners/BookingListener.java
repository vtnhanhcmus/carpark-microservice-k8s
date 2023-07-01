package com.carpark.carparks.listeners;

import com.carpark.carparks.dtos.BookingMsg;
import com.carpark.carparks.entities.Availability;
import com.carpark.carparks.repositories.AvailabilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookingListener {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @CacheEvict(value = "car_park_no_slot", key = "#bookingMsg.carParkNo")
    @KafkaListener(id = "bookingGroup", topics = "booking_topic")
    public void updateSlot(BookingMsg bookingMsg){
        log.info("listener for update slot with car park no {}", bookingMsg.getCarParkNo());

        log.info("Received: {}" + bookingMsg);
        if (bookingMsg.getMsg().startsWith("FAIL")) {
            throw new RuntimeException("FAILED");
        }

        Availability availability = availabilityRepository.findByCarParkNo(bookingMsg.getCarParkNo());
        availability.setLotsAvailable(availability.getLotsAvailable() + 1);
        availabilityRepository.save(availability);
    }

    @KafkaListener(id = "dltGroup", topics = "booking_topic.DLT")
    public void dltListen(byte[] in) {
        log.info("Received from DLT: " + new String(in));
    }
}

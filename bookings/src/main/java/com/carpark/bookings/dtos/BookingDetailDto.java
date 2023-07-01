package com.carpark.bookings.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailDto {
    private BookingDto booking;
    private AccountDto account;
    private CarParkDto carPark;
}

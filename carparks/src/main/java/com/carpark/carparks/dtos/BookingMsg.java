package com.carpark.carparks.dtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingMsg {
    private String carParkNo;
    private Integer quality;
    private String msg;
}

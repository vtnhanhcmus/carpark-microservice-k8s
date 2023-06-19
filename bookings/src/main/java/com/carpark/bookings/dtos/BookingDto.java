package com.carpark.bookings.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Long id;
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("car_park_no")
    private String carParkNo;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("created_date")
    private Date createdDate;
}

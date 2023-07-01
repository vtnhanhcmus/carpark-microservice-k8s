package com.carpark.bookings.services.feign;

import com.carpark.bookings.dtos.CarParkDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("carparks")
public interface CarParkFeignService {
    @RequestMapping(method = RequestMethod.GET, value = "carpark/{parkNo}/detail", consumes = "application/json")
    CarParkDto getDetailCarPark(@PathVariable("parkNo") String parkNo);

    @RequestMapping(method = RequestMethod.GET, value = "availability/{parkNo}/checkSlot", consumes = "application/json")
    Boolean checkSlot(@PathVariable("parkNo") String parkNo);
}

package com.carpark.bookings.controllers.feign;

import com.carpark.bookings.dtos.CarParkDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("carparks")
public interface CarParkFeignService {
    @RequestMapping(method = RequestMethod.GET, value = "carparks/{parkNo}/detail", consumes = "application/json")
    CarParkDTO getDetailCarPark(@PathVariable("parkNo") String parkNo);
}

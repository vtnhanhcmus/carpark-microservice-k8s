package com.carpark.bookings.services.feign;

import com.carpark.bookings.dtos.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("accounts")
public interface AccountFeignService {

    @RequestMapping(method = RequestMethod.GET, value = "accounts/{id}/detail", consumes = "application/json")
    AccountDto getDetailAccount(@PathVariable("id") Long id);

}

package com.carpark.carparks.repositories;

import com.carpark.carparks.entities.Availability;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, Long> {

    @Query("select a from Availability a inner join a.carPark ca where ca.carParkNo = :carParkNo")
    Availability findByCarParkNo(String carParkNo);
}

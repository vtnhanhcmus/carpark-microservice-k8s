package com.carparketl.repositories;

import com.carparketl.entities.Availability;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, Long> {
    @Query("select a from Availability a join a.carPark c where c.carParkNo = :carParkNo")
    Availability findByCarParkNo(String carParkNo);
}

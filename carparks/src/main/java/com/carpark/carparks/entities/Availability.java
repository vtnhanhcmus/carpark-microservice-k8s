package com.carpark.carparks.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Table(name = "availability")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_park_id", referencedColumnName = "id")
    private CarPark carPark;

    @Column(name = "total_lots")
    private Integer totalLots;

    @Column(name = "lots_available")
    private Integer lotsAvailable;

    @Column(name = "last_updated_on")
    private Date lastUpdatedOn;
}

package com.carparketl.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.Constants;
import org.hibernate.annotations.GenericGenerator;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "car_park")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarPark {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "car_park_no", nullable = false, unique = true)
    private String carParkNo;

    @Column(name = "address")
    private String address;

    @Column(name = "coordinate", nullable = false)
    private Point coordinate;
}


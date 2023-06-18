package com.carparketl.entities;


import lombok.*;
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
@EqualsAndHashCode
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


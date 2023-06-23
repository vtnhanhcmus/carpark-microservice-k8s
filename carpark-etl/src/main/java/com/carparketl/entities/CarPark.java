package com.carparketl.entities;


import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;


@Builder
@Entity
@Table(name = "car_park")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CarPark {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carpark_gen")
    @SequenceGenerator(name="carpark_gen", sequenceName="carpark_seq")
    private Long id;

    @Column(name = "car_park_no", nullable = false, unique = true)
    private String carParkNo;

    @Column(name = "address")
    private String address;

    @Column(name = "coordinate", nullable = false)
    private Point coordinate;
}


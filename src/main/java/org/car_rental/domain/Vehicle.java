package org.car_rental.domain;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Vehicle {

    Long id;
    String vehicleName;
    String color;
    Integer year;
    String brand;
    Long ownerId;

}

package org.car_rental.domain;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class VehicleOwner {

    Long id;
    String ownerName;
    String ownerNumber;
    String address;
    Float commission;
}

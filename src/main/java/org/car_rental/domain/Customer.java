package org.car_rental.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class Customer {

    Long id;
    String name;
    String phoneNumber;
    String cnic;
    String address;
    String referencePhoneNumber;

}

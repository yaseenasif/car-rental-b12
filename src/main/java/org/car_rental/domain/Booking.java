package org.car_rental.domain;

import lombok.*;

import java.sql.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {

    Long id;
    Long customerId;
    Long vehicleId;
    Date bookingDate;
    Float amount;

}

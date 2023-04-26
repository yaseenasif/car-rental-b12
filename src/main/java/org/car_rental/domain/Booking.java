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
    String customer;
    String vehicle;
    Date bookingDate;
    Date completeDate;
    Float amount;
    String status;
    Integer totalAmount;
    Integer commission;

}

package org.car_rental.domain;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class User {


    private Integer Id;
    private String username;
    private String password;


}

package com.jet.restaurants.service.restaurants.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Getter
    private String name;

    @Column
    @Getter
    private String city;

    @Column
    @Getter
    private String addressLine1;

    @Column
    @Getter
    private String addressLine2;

    @Column
    @Getter
    private String zipCode;

    @Column
    @Getter
    private String country;

    @Column
    @Getter
    @Setter
    @Enumerated(STRING)
    private Status status;

    public static Restaurant create(String name, String city, String addressLine1, String addressLine2, String zipCode, String country, Status status) {
        val restaurant = builder().name(name).status(status).addressLine1(addressLine1).addressLine2(addressLine2).zipCode(zipCode).city(city).country(country).build();
        return restaurant;
    }

}

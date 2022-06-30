package com.jet.restaurants.service.openclose.utils;

import com.jet.restaurants.service.restaurants.domain.Restaurant;
import com.jet.restaurants.service.restaurants.domain.Status;
import com.jet.restaurants.service.restaurants.service.event.RestaurantEvent;

import java.util.List;

public class TestDataBuilder {

    public static Restaurant restaurantDetails(Long id) {
        return Restaurant.builder()
                .name("some-name")
                .addressLine1("address-1")
                .addressLine2("address-2")
                .city("some-city")
                .country("some-country")
                .status(Status.OPEN)
                .zipCode("12345")
                .id(id)
                .build();
    }

    public static RestaurantEvent restaurantEvent() {
        return RestaurantEvent.builder()
                .id(1L)
                .status(Status.CLOSED)
                .build();
    }

    public static List<Restaurant> getListOfRestaurants() {
        return List.of(restaurantDetails(1L), restaurantDetails(2L));
    }
}

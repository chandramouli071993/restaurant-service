package com.jet.restaurants.service.restaurants.service;

import com.jet.restaurants.service.restaurants.domain.Restaurant;
import com.jet.restaurants.service.restaurants.domain.Status;
import com.jet.restaurants.service.restaurants.repository.RestaurantRepository;
import com.jet.restaurants.service.restaurants.service.event.RestaurantEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> restaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant restaurantByName(String name) {
        return restaurantRepository.findByName(name).orElseThrow(() ->
                new NoSuchElementException(String.format("No restaurant found with the name - %s", name)));
    }

    public Restaurant restaurantById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("No restaurant found with the ID - %s", id)));
    }

    public Restaurant createRestaurant(String name, String city, String addressLine1, String addressLine2, String zipCode, String country, Status status) {
        Restaurant restaurant = null;
        try {
            Restaurant exists = restaurantByName(name);
        } catch (Exception e) {
            restaurant = restaurantRepository.save(Restaurant.create(name, city, addressLine1, addressLine2, zipCode, country, status));
        }
        return restaurant;
    }

    public void updateStatus(RestaurantEvent restaurantEvent) {
        Restaurant existingRestaurantDetails = restaurantRepository.findById(restaurantEvent.getId()).orElseThrow(()
                -> new NoSuchElementException(String.format("The restaurant detail with the ID not found in DB - %s", restaurantEvent.getId().toString())));
        existingRestaurantDetails.setStatus(Status.decode(restaurantEvent.getStatus()));
        restaurantRepository.save(existingRestaurantDetails);
    }

}

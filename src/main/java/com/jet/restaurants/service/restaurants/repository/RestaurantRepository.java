package com.jet.restaurants.service.restaurants.repository;

import com.jet.restaurants.service.restaurants.domain.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository
        extends CrudRepository<Restaurant, Long> {

    List<Restaurant> findAll();

    Optional<Restaurant> findByName(String name);

}

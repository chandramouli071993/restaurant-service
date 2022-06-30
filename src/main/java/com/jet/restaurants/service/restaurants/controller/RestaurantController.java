package com.jet.restaurants.service.restaurants.controller;

import com.jet.restaurants.service.restaurants.domain.Restaurant;
import com.jet.restaurants.service.restaurants.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> restaurants() {
        return ResponseEntity.ok(restaurantService.restaurants());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Restaurant> restaurantByName(@PathVariable String name) {
        return ResponseEntity.ok(restaurantService.restaurantByName(name));
    }

}

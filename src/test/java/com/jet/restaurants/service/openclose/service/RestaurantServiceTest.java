package com.jet.restaurants.service.openclose.service;

import com.jet.restaurants.service.restaurants.domain.Restaurant;
import com.jet.restaurants.service.restaurants.domain.Status;
import com.jet.restaurants.service.restaurants.repository.RestaurantRepository;
import com.jet.restaurants.service.restaurants.service.RestaurantService;
import com.jet.restaurants.service.restaurants.service.event.RestaurantEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.jet.restaurants.service.openclose.utils.TestDataBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RestaurantServiceTest {

    private final RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);

    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        restaurantService = new RestaurantService(
                restaurantRepository
        );

    }

    @Test
    void shouldFetchAllRestaurants() {

        List<Restaurant> expected = getListOfRestaurants();
        when(restaurantRepository.findAll()).thenReturn(expected);
        List<Restaurant> actual = restaurantService.restaurants();
        assertEquals(2, actual.size());
    }

    @Test
    void shouldFetchRestaurantByName() {

        String name = "some-name";
        Restaurant expected = restaurantDetails(1L);
        when(restaurantRepository.findByName(name)).thenReturn(Optional.ofNullable(expected));
        Restaurant actual = restaurantService.restaurantByName(name);
        assertEquals(actual.getName(), name);
    }

    @Test
    void shouldThrowExceptionFetchRestaurantByName() {
        String name = "some-fake-name";
        when(restaurantRepository.findByName(name)).thenReturn(Optional.empty());
        NoSuchElementException notFoundException = assertThrows(NoSuchElementException.class, () -> restaurantService.restaurantByName(name));
        assertEquals("No restaurant found with the name - some-fake-name", notFoundException.getMessage());

    }

    @Test
    void shouldFetchRestaurantById() {

        Long id = 1L;
        Restaurant expected = restaurantDetails(1L);
        when(restaurantRepository.findById(id)).thenReturn(Optional.ofNullable(expected));
        Restaurant actual = restaurantService.restaurantById(id);
        assertEquals(actual.getName(), expected.getName());
    }

    @Test
    void shouldThrowExceptionFetchRestaurantById() {
        Long id = 1111L;
        when(restaurantRepository.findById(id)).thenReturn(Optional.empty());
        NoSuchElementException notFoundException = assertThrows(NoSuchElementException.class, () -> restaurantService.restaurantById(id));
        assertEquals("No restaurant found with the ID - 1111", notFoundException.getMessage());

    }

    @Test
    void shouldCreateRestaurant() {
        Restaurant toSave = restaurantDetails(22L);
        when(restaurantRepository.save(toSave)).thenReturn(toSave);
        Restaurant actual = restaurantService.createRestaurant(
                "some-name", "some-city", "address-1",
                "address-2", "12345", "some-country", Status.OPEN
        );
        assertNotNull(toSave.getName());
    }

    @Test
    void shouldUpdateRestaurant() {
        RestaurantEvent restaurantEvent = restaurantEvent();
        Restaurant toSave = restaurantDetails(1L);
        when(restaurantRepository.findById(1L)).thenReturn(Optional.ofNullable(toSave));

        toSave.setStatus(restaurantEvent.getStatus());
        when(restaurantRepository.save(toSave)).thenReturn(toSave);

        restaurantService.updateStatus(restaurantEvent);
        assertNotNull(toSave);
    }
}

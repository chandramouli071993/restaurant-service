package com.jet.restaurants.service.restaurants.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.jet.restaurants.service.restaurants.service.event.RestaurantEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantEventInboundService {

    private final RestaurantService restaurantService;

    @KafkaListener(id = "${kafka.consumers.consumerGroup}", topics = {"${kafka.topic.restaurant.change-status:restaurants-status}"})
    public void receive(@Payload String detail) throws JsonProcessingException {
        log.info("Handling index events. Received {} event(s)", detail);
        RestaurantEvent restaurantEvent = new Gson().fromJson(detail, RestaurantEvent.class);
        restaurantService.updateStatus(restaurantEvent);
    }
}

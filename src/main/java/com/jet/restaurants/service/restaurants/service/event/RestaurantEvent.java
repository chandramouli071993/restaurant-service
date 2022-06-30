package com.jet.restaurants.service.restaurants.service.event;

import com.jet.restaurants.service.restaurants.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantEvent {
    private Long id;
    private String status;
}

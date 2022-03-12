package com.starwars.resistancesocialnetwork.gateways.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeadquarterResponse {
    private Long id;
    private String name;
    private Point location;
    private List<RebelResponse> rebels;
}

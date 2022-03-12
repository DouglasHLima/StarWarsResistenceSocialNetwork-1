package com.starwars.resistancesocialnetwork.domains;

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
public class Headquarter {
    private Long id;
    private String name;
    private Point location;
    private List<Rebel> rebels;
}

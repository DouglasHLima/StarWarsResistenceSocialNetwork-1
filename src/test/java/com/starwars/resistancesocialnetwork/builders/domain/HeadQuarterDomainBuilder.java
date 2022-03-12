package com.starwars.resistancesocialnetwork.builders.domain;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.domains.Rebel;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.geo.Point;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class HeadQuarterDomainBuilder {
    @Builder.Default
    private Long id = 1L;
    @Builder.Default
    private String name = "Pandora";
    @Builder.Default
    private Point location = new Point(10,50);
    @Builder.Default
    private List<Rebel> rebels = List.of(
            RebelDomainBuilder.builder().build().toDomain());


    public Headquarter toDomain(){
        return Headquarter.builder()
                .id(id)
                .name(name)
                .location(location)
                .rebels(rebels)
                .build();
    }
}

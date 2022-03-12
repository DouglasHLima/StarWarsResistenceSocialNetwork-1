package com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.entities;

import lombok.*;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_HEADQUARTERS")
public class HeadquarterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HEADQUARTER_ID")
    private Long id;
    @Column(name = "HEADQUARTER_NAME", nullable = false, unique = true)
    private String name;
    @Column(name = "HEADQUARTER_LOCATION", nullable = false)
    private Point location;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<RebelEntity> rebels;
}

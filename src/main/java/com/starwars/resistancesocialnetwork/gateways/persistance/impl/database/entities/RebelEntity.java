package com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.entities;

import com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.entities.enums.GenderEntity;
import com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.entities.enums.ItemEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_REBELS")
public class RebelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REBEL_ID")
    private Long id;
    @Column(name = "REBEL_NAME", nullable = false)
    private String name;
    @Column(name = "REBEL_AGE", nullable = false)
    private Integer age;
    @Column(name = "REBEL_GENDER", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderEntity gender;
    @ManyToOne
    @JoinColumn(name = "HEADQUARTER_REBEL_ID", referencedColumnName = "HEADQUARTER_ID")
    private HeadquarterEntity location;
    @ElementCollection(fetch = FetchType.LAZY, targetClass = ItemEntity.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "REBEL_INVENTORY", nullable = false)
    private List<ItemEntity> inventory;
    @Column(name = "REBEL_REPORTS")
    private Integer reports;
    @Column(name = "REBEL_TRAITOR_STATUS")
    private Boolean traitor;

}

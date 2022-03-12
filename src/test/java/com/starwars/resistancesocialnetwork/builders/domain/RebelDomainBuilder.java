package com.starwars.resistancesocialnetwork.builders.domain;

import com.starwars.resistancesocialnetwork.domains.Rebel;
import com.starwars.resistancesocialnetwork.domains.enums.Gender;
import com.starwars.resistancesocialnetwork.domains.enums.Item;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Builder
@Data
public class RebelDomainBuilder {
    @Builder.Default
    private Long id = 1L;
    @Builder.Default
    private String name = "Luke";
    @Builder.Default
    private Integer age = 18;
    @Builder.Default
    private Gender gender = Gender.MALE;
    @Builder.Default
    private Long headquarterId = 1L;
    @Builder.Default
    private List<Item> inventory = List.of(Item.WATER);

    public Rebel toDomain(){
        return Rebel.builder()
                .id(id)
                .name(name)
                .age(age)
                .gender(gender)
                .headquarterId(headquarterId)
                .inventory(inventory)
                .build();
    }

}

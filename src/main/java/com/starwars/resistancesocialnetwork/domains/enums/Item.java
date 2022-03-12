package com.starwars.resistancesocialnetwork.domains.enums;

public enum Item {
    WEAPON(4),
    AMMO(3),
    WATER(2),
    FOOD(1);

    Integer value;

    Item(Integer value){
        this.value = value;
    }
}

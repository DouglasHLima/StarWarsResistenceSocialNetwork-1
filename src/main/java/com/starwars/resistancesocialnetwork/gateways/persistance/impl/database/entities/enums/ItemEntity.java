package com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.entities.enums;

public enum ItemEntity {
    WEAPON(4),
    AMMO(3),
    WATER(2),
    FOOD(1);

    Integer value;

    ItemEntity(Integer value){
        this.value = value;
    }
}

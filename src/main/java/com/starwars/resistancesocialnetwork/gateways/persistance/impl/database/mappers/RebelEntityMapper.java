package com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.mappers;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.domains.Rebel;
import com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.entities.RebelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RebelEntityMapper {
    RebelEntityMapper INSTANCE = Mappers.getMapper(RebelEntityMapper.class);

    @Mapping(source = "rebelEntity.location.id", target = "headquarterId")
    Rebel toDomain(RebelEntity rebelEntity);

    @Mapping(source = "rebel.id", target = "id")
    @Mapping(source = "rebel.name", target = "name")
    @Mapping(source = "headquarter", target = "location")
    RebelEntity toEntity(Rebel rebel, Headquarter headquarter);

    RebelEntity toEntity(Rebel rebel);
}

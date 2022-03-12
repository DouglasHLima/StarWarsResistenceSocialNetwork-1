package com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.mappers;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.gateways.persistance.impl.database.entities.HeadquarterEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {RebelEntityMapper.class})
public interface HeadquarterEntityMapper {
    HeadquarterEntityMapper INSTANCE = Mappers.getMapper(HeadquarterEntityMapper.class);

    Headquarter toDomain(HeadquarterEntity headquarterEntity);

    @InheritInverseConfiguration
    HeadquarterEntity toEntity(Headquarter headquarter);
}

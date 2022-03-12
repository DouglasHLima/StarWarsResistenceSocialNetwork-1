package com.starwars.resistancesocialnetwork.gateways.controllers.mappers.response;

import com.starwars.resistancesocialnetwork.domains.Rebel;
import com.starwars.resistancesocialnetwork.gateways.controllers.response.RebelResponse;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RebelResponseMapper {
    RebelResponseMapper INSTANCE = Mappers.getMapper(RebelResponseMapper.class);

    Rebel toDomain(RebelResponse rebelResponse);

    @InheritInverseConfiguration
    RebelResponse toResponse(Rebel rebel);
}

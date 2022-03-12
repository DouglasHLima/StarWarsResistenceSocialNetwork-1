package com.starwars.resistancesocialnetwork.gateways.controllers.mappers.request;

import com.starwars.resistancesocialnetwork.domains.Rebel;
import com.starwars.resistancesocialnetwork.gateways.controllers.request.RebelRequest;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RebelRequestMapper {
    RebelRequestMapper INSTANCE = Mappers.getMapper(RebelRequestMapper.class);

    Rebel toDomain(RebelRequest rebelRequest);

    @InheritInverseConfiguration
    RebelRequest toRequest(Rebel rebel);
}

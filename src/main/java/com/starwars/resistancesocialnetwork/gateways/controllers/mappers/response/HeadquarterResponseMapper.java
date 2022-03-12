package com.starwars.resistancesocialnetwork.gateways.controllers.mappers.response;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.gateways.controllers.response.HeadquarterResponse;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {RebelResponseMapper.class})
public interface HeadquarterResponseMapper {
    HeadquarterResponseMapper INSTANCE = Mappers.getMapper(HeadquarterResponseMapper.class);

    Headquarter toDomain(HeadquarterResponse headquarterResponse);

    @InheritInverseConfiguration
    HeadquarterResponse toResponse(Headquarter headquarter);
}

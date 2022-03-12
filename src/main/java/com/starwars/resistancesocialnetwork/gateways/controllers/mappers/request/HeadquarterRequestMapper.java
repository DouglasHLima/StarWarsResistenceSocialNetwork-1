package com.starwars.resistancesocialnetwork.gateways.controllers.mappers.request;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.gateways.controllers.request.HeadquarterRequest;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {RebelRequestMapper.class})
public interface HeadquarterRequestMapper {
    HeadquarterRequestMapper INSTANCE = Mappers.getMapper(HeadquarterRequestMapper.class);

    Headquarter toDomain(HeadquarterRequest headquarterRequest);

    @InheritInverseConfiguration
    HeadquarterRequest toRequest(Headquarter headquarter);
}

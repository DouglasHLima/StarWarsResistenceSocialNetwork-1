package com.starwars.resistancesocialnetwork.usecases.headquarter;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.gateways.persistance.HeadQuartersPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeadquarterCreateService {
  private final HeadQuartersPersistenceGateway headQuartersPersistence;

  public Headquarter execute(Headquarter headquarter) {
    return headQuartersPersistence.save(headquarter);
  }
}

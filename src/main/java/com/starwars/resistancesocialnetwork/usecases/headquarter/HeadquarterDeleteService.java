package com.starwars.resistancesocialnetwork.usecases.headquarter;

import com.starwars.resistancesocialnetwork.exceptions.HeadquarterNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.persistance.HeadQuartersPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeadquarterDeleteService {
  private final HeadQuartersPersistenceGateway headQuartersPersistence;

  public void execute(Long id){
    if (!headQuartersPersistence.existById(id)) {
      throw HeadquarterNotFoundException.builder().message("Headquarter not found by" + id).build();
    }
    headQuartersPersistence.deleteById(id);
  }
}

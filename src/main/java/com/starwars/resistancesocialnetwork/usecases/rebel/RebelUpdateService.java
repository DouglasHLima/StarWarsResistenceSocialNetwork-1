package com.starwars.resistancesocialnetwork.usecases.rebel;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.domains.Rebel;
import com.starwars.resistancesocialnetwork.exceptions.HeadquarterNotFoundException;
import com.starwars.resistancesocialnetwork.exceptions.RebelNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.persistance.HeadQuartersPersistenceGateway;
import com.starwars.resistancesocialnetwork.gateways.persistance.RebelPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RebelUpdateService {
  private final RebelPersistenceGateway rebelPersistence;
  private final HeadQuartersPersistenceGateway headQuartersPersistence;

  public Rebel execute(Long id, Rebel rebel){
    Long headquarterId = rebel.getHeadquarterId();
    Headquarter headquarter = headQuartersPersistence.findById(headquarterId).orElseThrow(() -> HeadquarterNotFoundException.builder().message("Headquarter not found by" + headquarterId).build());
    Rebel result = rebelPersistence.findById(id).orElseThrow(() -> RebelNotFoundException.builder().message("Rebel not found by" + id).build());
    BeanUtils.copyProperties(rebel, result, "id", "inventory");
    return rebelPersistence.save(result, headquarter);
  }


}

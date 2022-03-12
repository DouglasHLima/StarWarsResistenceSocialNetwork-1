package com.starwars.resistancesocialnetwork.usecases.headquarter;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.exceptions.HeadquarterNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.persistance.HeadQuartersPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeadquarterGetService {

  private final HeadQuartersPersistenceGateway headQuartersPersistence;

  public Headquarter getById(Long id){
    Optional<Headquarter> result = headQuartersPersistence.findById(id);
    return result.orElseThrow(() -> HeadquarterNotFoundException.builder().message("Headquarter not found by" + id).build());
  }

  public Page<Headquarter> getAll(Pageable page) {
    return headQuartersPersistence.findAll(page);
  }
}

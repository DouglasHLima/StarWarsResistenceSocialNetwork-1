package com.starwars.resistancesocialnetwork.usecases.rebel;

import com.starwars.resistancesocialnetwork.exceptions.HeadquarterNotFoundException;
import com.starwars.resistancesocialnetwork.exceptions.RebelNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.persistance.RebelPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RebelDeleteService {
  private final RebelPersistenceGateway rebelPersistence;

  public void execute(Long id){
    if (!rebelPersistence.existById(id)) {
      throw RebelNotFoundException.builder().message("Rebel not found by" + id).build();
    }
    rebelPersistence.deleteById(id);
  }
}

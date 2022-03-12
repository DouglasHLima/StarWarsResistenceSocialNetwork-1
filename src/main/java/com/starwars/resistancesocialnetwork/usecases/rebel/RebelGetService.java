package com.starwars.resistancesocialnetwork.usecases.rebel;

import com.starwars.resistancesocialnetwork.domains.Rebel;
import com.starwars.resistancesocialnetwork.exceptions.RebelNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.persistance.RebelPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RebelGetService {
  private final RebelPersistenceGateway rebelPersistence;

  public Rebel getById(Long id) throws RebelNotFoundException {
    Optional<Rebel> result = rebelPersistence.findById(id);
    return result.orElseThrow(() -> RebelNotFoundException.builder().message("Rebel not found by" + id).build());
  }

  public Page<Rebel> getAll(Pageable page) {
    return rebelPersistence.findAll(page);
  }
}

package com.starwars.resistancesocialnetwork.gateways.persistance;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.domains.Rebel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RebelPersistenceGateway {
    Rebel save(Rebel rebel, Headquarter headquarter);
    Optional<Rebel> findById(Long id);
    boolean existById(Long id);
    Page<Rebel> findAll(Pageable page);
    void deleteById(Long id);
}

package com.starwars.resistancesocialnetwork.gateways.persistance;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface HeadQuartersPersistenceGateway {
    Headquarter save(Headquarter headquarter);
    Optional<Headquarter> findById(Long id);
    boolean existById(Long id);
    Page<Headquarter> findAll(Pageable page);
    void deleteById(Long id);
}

package com.starwars.resistancesocialnetwork.usecases.rebel;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.domains.Rebel;
import com.starwars.resistancesocialnetwork.exceptions.HeadquarterNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.persistance.HeadQuartersPersistenceGateway;
import com.starwars.resistancesocialnetwork.gateways.persistance.RebelPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RebelCreateService {
    private final RebelPersistenceGateway rebelPersistence;
    private final HeadQuartersPersistenceGateway headQuartersPersistence;

    public Rebel execute(Rebel rebel){
        Long headquarterId = rebel.getHeadquarterId();
        Headquarter headquarter = headQuartersPersistence.findById(headquarterId).orElseThrow(() -> HeadquarterNotFoundException.builder().message("Headquarter not found by" + headquarterId).build());
        return rebelPersistence.save(rebel, headquarter);
    }
}

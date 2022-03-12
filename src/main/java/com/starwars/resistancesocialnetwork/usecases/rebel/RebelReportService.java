package com.starwars.resistancesocialnetwork.usecases.rebel;

import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.domains.Rebel;
import com.starwars.resistancesocialnetwork.exceptions.HeadquarterNotFoundException;
import com.starwars.resistancesocialnetwork.exceptions.RebelNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.persistance.HeadQuartersPersistenceGateway;
import com.starwars.resistancesocialnetwork.gateways.persistance.RebelPersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RebelReportService{
    private final RebelPersistenceGateway rebelPersistence;
    private final HeadQuartersPersistenceGateway headQuartersPersistence;

    public Rebel execute(Long id){
        Rebel foundRebel = rebelPersistence.findById(id).orElseThrow(()->RebelNotFoundException.builder().message("Rebel not found by" + id).build());
        Long headquarterId = foundRebel.getHeadquarterId();
        Headquarter headquarter = headQuartersPersistence.findById(headquarterId).orElseThrow(()-> HeadquarterNotFoundException.builder().message("Headquarter not found by" + headquarterId).build());
        foundRebel.reportRebel();
        return rebelPersistence.save(foundRebel, headquarter);
    }
}

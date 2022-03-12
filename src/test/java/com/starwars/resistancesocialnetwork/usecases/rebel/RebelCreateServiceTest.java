package com.starwars.resistancesocialnetwork.usecases.rebel;

import com.starwars.resistancesocialnetwork.builders.domain.HeadQuarterDomainBuilder;
import com.starwars.resistancesocialnetwork.builders.domain.RebelDomainBuilder;
import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.domains.Rebel;
import com.starwars.resistancesocialnetwork.exceptions.HeadquarterNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.persistance.HeadQuartersPersistenceGateway;
import com.starwars.resistancesocialnetwork.gateways.persistance.RebelPersistenceGateway;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RebelCreateServiceTest {

    @Mock
    RebelPersistenceGateway rebelPersistance;
    @Mock
    HeadQuartersPersistenceGateway headquarterPersistance;
    @InjectMocks
    RebelCreateService rebelCreateService;

    @Test
    void execute_when_informed_a_valid_rebel_then_create_it() {
        //given
        Headquarter expectedSavedHeadquarter = HeadQuarterDomainBuilder.builder().build().toDomain();
        Rebel expectedRebel = RebelDomainBuilder.builder().build().toDomain();
        //when
        Mockito.when(headquarterPersistance.findById(expectedRebel.getHeadquarterId()))
                .thenReturn(Optional.of(expectedSavedHeadquarter));
        Mockito.when(rebelPersistance.save(expectedRebel,expectedSavedHeadquarter))
                .thenReturn(expectedRebel);
        //then
        Rebel createdRebel = rebelCreateService.execute(expectedRebel);

        Assertions.assertThat(expectedRebel).isEqualTo(createdRebel);
    }

    @Test
    void execute_when_informed_a_invalid_headquarterId_then_throws_exception() {
        //given
        Rebel expectedRebel = RebelDomainBuilder.builder().build().toDomain();
        //when
        Mockito.when(headquarterPersistance.findById(expectedRebel.getHeadquarterId()))
                .thenReturn(Optional.empty());
        //then
        assertThrows(HeadquarterNotFoundException.class,
                () -> rebelCreateService.execute(expectedRebel));
    }
}
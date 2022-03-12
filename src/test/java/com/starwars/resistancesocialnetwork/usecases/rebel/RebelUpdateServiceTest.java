package com.starwars.resistancesocialnetwork.usecases.rebel;

import com.starwars.resistancesocialnetwork.builders.domain.HeadQuarterDomainBuilder;
import com.starwars.resistancesocialnetwork.builders.domain.RebelDomainBuilder;
import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.domains.Rebel;
import com.starwars.resistancesocialnetwork.exceptions.HeadquarterNotFoundException;
import com.starwars.resistancesocialnetwork.exceptions.RebelNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.persistance.HeadQuartersPersistenceGateway;
import com.starwars.resistancesocialnetwork.gateways.persistance.RebelPersistenceGateway;
import com.starwars.resistancesocialnetwork.usecases.headquarter.HeadquarterUpdateService;
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
class RebelUpdateServiceTest {

    @Mock
    RebelPersistenceGateway rebelPersistance;
    @Mock
    HeadQuartersPersistenceGateway headquarterPersistance;
    @InjectMocks
    RebelUpdateService rebelUpdateService;

    @Test
    void execute_when_inform_a_valid_id_then_update_with_success() {
        //given
        Rebel expectedSavedRebel = RebelDomainBuilder.builder().build().toDomain();
        Rebel expectedRebelToUptade = RebelDomainBuilder.builder()
                .age(25)
                .build().toDomain();
        Headquarter expectedHeadquarter = HeadQuarterDomainBuilder.builder().build().toDomain();
        //when
        Mockito.when(headquarterPersistance.findById(expectedRebelToUptade.getHeadquarterId()))
                .thenReturn(Optional.of(expectedHeadquarter));
        Mockito.when(rebelPersistance.findById(expectedRebelToUptade.getId()))
                .thenReturn(Optional.of(expectedSavedRebel));
        Mockito.when(rebelPersistance.save(expectedSavedRebel,expectedHeadquarter))
                .thenReturn(expectedSavedRebel);
        //then
        Rebel updated = rebelUpdateService.execute(expectedRebelToUptade.getId(), expectedRebelToUptade);

        Assertions.assertThat(updated).isEqualTo(expectedSavedRebel);
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
                () -> rebelUpdateService.execute(expectedRebel.getId(),expectedRebel));
    }

    @Test
    void execute_when_informed_a_invalid_id_then_throws_exception() {
        //given
        Rebel expectedRebel = RebelDomainBuilder.builder().build().toDomain();
        Headquarter expectedHeadquarter = HeadQuarterDomainBuilder.builder().build().toDomain();

        //when
        Mockito.when(rebelPersistance.findById(expectedRebel.getHeadquarterId()))
                .thenReturn(Optional.empty());
        Mockito.when(headquarterPersistance.findById(expectedRebel.getHeadquarterId()))
                .thenReturn(Optional.of(expectedHeadquarter));
        //then
        assertThrows(RebelNotFoundException.class,
                () -> rebelUpdateService.execute(expectedRebel.getId(),expectedRebel));
    }
}
package com.starwars.resistancesocialnetwork.usecases.headquarter;

import com.starwars.resistancesocialnetwork.builders.domain.HeadQuarterDomainBuilder;
import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.exceptions.HeadquarterNotFoundException;
import com.starwars.resistancesocialnetwork.exceptions.RebelNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.persistance.HeadQuartersPersistenceGateway;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HeadquarterUpdateServiceTest {

    @Mock
    HeadQuartersPersistenceGateway headquarterPersistance;
    @InjectMocks
    HeadquarterUpdateService updateService;

    @Test
    void execute_when_inform_a_valid_id_then_update_with_success() {
        //given
        Headquarter expectedHeadquarterToUpdate = HeadQuarterDomainBuilder.builder()
                .name("Terra")
                .build().toDomain();
        Headquarter expectedSavedHeadquarter = HeadQuarterDomainBuilder.builder().build().toDomain();
        //when
        Mockito.when(headquarterPersistance.findById(expectedHeadquarterToUpdate.getId()))
                .thenReturn(Optional.of(expectedSavedHeadquarter));
        Mockito.when(headquarterPersistance.save(expectedSavedHeadquarter))
                .thenReturn(expectedSavedHeadquarter);
        //then
        updateService.execute(expectedHeadquarterToUpdate.getId(),expectedHeadquarterToUpdate);

        Assertions.assertThat(expectedSavedHeadquarter).isEqualTo(expectedHeadquarterToUpdate);


    }

    @Test
    void execute_when_inform_a_invalid_id_then_throw_excpetion() {
        //given
        Headquarter expectedHeadquarterToUpdate = HeadQuarterDomainBuilder.builder()
                .name("Terra")
                .build().toDomain();
        Headquarter expectedSavedHeadquarter = HeadQuarterDomainBuilder.builder().build().toDomain();
        //when
        Mockito.when(headquarterPersistance.findById(expectedHeadquarterToUpdate.getId()))
                .thenReturn(Optional.empty());

        //then
        assertThrows(HeadquarterNotFoundException.class,
                () -> updateService.execute(expectedHeadquarterToUpdate.getId(),expectedSavedHeadquarter));
    }
}
package com.starwars.resistancesocialnetwork.usecases.headquarter;

import com.starwars.resistancesocialnetwork.builders.domain.HeadQuarterDomainBuilder;
import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.gateways.persistance.HeadQuartersPersistenceGateway;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HeadquarterCreateServiceTest {

    @Mock
    HeadQuartersPersistenceGateway headQuartersPersistenceGateway;
    @InjectMocks
    HeadquarterCreateService createService;

    @Test
    void whenInformedAValidHeadQuarterThenItsShouldBeCreated() {
        //given
        Headquarter expectedSaveHeadQuarter = HeadQuarterDomainBuilder.builder().build().toDomain();

        //when
        Mockito.when(headQuartersPersistenceGateway.save(expectedSaveHeadQuarter)).thenReturn(expectedSaveHeadQuarter);

        //then
        Headquarter createdHeadQuarter = createService.execute(expectedSaveHeadQuarter);

        Assertions.assertThat(createdHeadQuarter).isEqualTo(expectedSaveHeadQuarter);
    }
}
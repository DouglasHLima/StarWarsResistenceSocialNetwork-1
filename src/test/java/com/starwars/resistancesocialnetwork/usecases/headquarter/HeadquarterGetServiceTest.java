package com.starwars.resistancesocialnetwork.usecases.headquarter;

import com.starwars.resistancesocialnetwork.builders.domain.HeadQuarterDomainBuilder;
import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.exceptions.HeadquarterNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.persistance.HeadQuartersPersistenceGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

@ExtendWith(MockitoExtension.class)
class HeadquarterGetServiceTest {

    @Mock
    HeadQuartersPersistenceGateway headQuartersPersistenceGateway;
    @InjectMocks
    HeadquarterGetService getService;

    @Test
    void whenRequiredAExistsHeadQuarterShoulBeReturned() throws HeadquarterNotFoundException {
        //given
        Headquarter expectedHeadQuarter = HeadQuarterDomainBuilder.builder().build().toDomain();

        //when
        Mockito.when(headQuartersPersistenceGateway.findById(expectedHeadQuarter.getId())).thenReturn(Optional.of(expectedHeadQuarter));

        //then
        Headquarter result = getService.getById(expectedHeadQuarter.getId());

        assertThat(result).isEqualTo(expectedHeadQuarter);
    }

    @Test
    void whenRequiredAInvalidHeadQuarterShoulBeThrowException(){
        //given
        Headquarter expectedHeadQuarter = HeadQuarterDomainBuilder.builder().build().toDomain();

        //when
        Mockito.when(headQuartersPersistenceGateway.findById(expectedHeadQuarter.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(HeadquarterNotFoundException.class, () ->
                getService.getById(expectedHeadQuarter.getId()));
    }

    @Test
    void whenRequiredAPageOfHeadQuartersTheyShouldReturnPaged() {
        //given
        PageRequest expectedPagination = PageRequest.of(1,10);
        Headquarter expectedHeadQuarter = HeadQuarterDomainBuilder.builder().build().toDomain();
        List<Headquarter> headQuarters = List.of(expectedHeadQuarter);
        Page<Headquarter> pagedExpectedResult = new PageImpl<>(headQuarters,expectedPagination,headQuarters.size());

        //when
        Mockito.when(headQuartersPersistenceGateway.findAll(expectedPagination)).thenReturn(pagedExpectedResult);

        //then
        Page<Headquarter> results = getService.getAll(expectedPagination);

        assertThat(results.getContent()).isEqualTo(headQuarters);
    }
}
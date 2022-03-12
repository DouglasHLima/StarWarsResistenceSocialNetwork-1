package com.starwars.resistancesocialnetwork.usecases.rebel;

import com.starwars.resistancesocialnetwork.builders.domain.HeadQuarterDomainBuilder;
import com.starwars.resistancesocialnetwork.builders.domain.RebelDomainBuilder;
import com.starwars.resistancesocialnetwork.domains.Headquarter;
import com.starwars.resistancesocialnetwork.domains.Rebel;
import com.starwars.resistancesocialnetwork.exceptions.HeadquarterNotFoundException;
import com.starwars.resistancesocialnetwork.exceptions.RebelNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.persistance.HeadQuartersPersistenceGateway;
import com.starwars.resistancesocialnetwork.gateways.persistance.RebelPersistenceGateway;
import com.starwars.resistancesocialnetwork.usecases.headquarter.HeadquarterGetService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RebelGetServiceTest {

    @Mock
    RebelPersistenceGateway rebelPersistance;
    @InjectMocks
    RebelGetService getService;

    @Test
    void getById_when_informed_a_valid_id_then_return_it() {
        //given
        Rebel expectedRebel = RebelDomainBuilder.builder().build().toDomain();
        //when
        Mockito.when(rebelPersistance.findById(expectedRebel.getId())).thenReturn(Optional.of(expectedRebel));
        //then
        Rebel expectedFound = getService.getById(expectedRebel.getId());

        Assertions.assertThat(expectedFound).isEqualTo(expectedRebel);
    }

    @Test
    void getAll_when_require_a_page_of_rebels_they_should_return_paged() {
        //given
        PageRequest expectedPagination = PageRequest.of(1,10);
        Rebel expectedRebel = RebelDomainBuilder.builder().build().toDomain();
        List<Rebel> rebels = List.of(expectedRebel);
        Page<Rebel> pagedExpectedResult = new PageImpl<>(rebels,expectedPagination,rebels.size());

        //when
        Mockito.when(rebelPersistance.findAll(expectedPagination)).thenReturn(pagedExpectedResult);

        //then
        Page<Rebel> results = getService.getAll(expectedPagination);

        assertThat(results.getContent()).isEqualTo(rebels);
    }

    @Test
    void when_required_a_invalid_rebel_should_be_throw_exception(){
        //given
        Rebel expectedRebel = RebelDomainBuilder.builder().build().toDomain();

        //when
        Mockito.when(rebelPersistance.findById(expectedRebel.getId())).thenReturn(Optional.empty());

        //then
        assertThrows(RebelNotFoundException.class, () ->
                getService.getById(expectedRebel.getId()));
    }
}
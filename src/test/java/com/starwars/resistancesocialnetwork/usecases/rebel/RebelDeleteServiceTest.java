package com.starwars.resistancesocialnetwork.usecases.rebel;

import com.starwars.resistancesocialnetwork.builders.domain.RebelDomainBuilder;
import com.starwars.resistancesocialnetwork.domains.Rebel;
import com.starwars.resistancesocialnetwork.exceptions.RebelNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.persistance.RebelPersistenceGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RebelDeleteServiceTest {


    @Mock
    RebelPersistenceGateway rebelPersistance;
    @InjectMocks
    RebelDeleteService rebelDeleteService;

    @Test
    void execute_when_informed_a_valid_id_then_delete() {
        //given
        Rebel expectedRebelToDelete = RebelDomainBuilder.builder().build().toDomain();
        //when
        Mockito.when(rebelPersistance.existById(expectedRebelToDelete.getId()))
                .thenReturn(true);
        Mockito.doNothing().when(rebelPersistance).deleteById(expectedRebelToDelete.getId());
        //then
        rebelDeleteService.execute(expectedRebelToDelete.getId());
        Mockito.verify(rebelPersistance,Mockito.times(1)).deleteById(expectedRebelToDelete.getId());

    }

    @Test
    void execute_when_informed_a_invalid_id_throws_exception() {
        //given
        Rebel expectedRebelToDelete = RebelDomainBuilder.builder().build().toDomain();
        //when
        Mockito.when(rebelPersistance.existById(expectedRebelToDelete.getId()))
                .thenReturn(false);
        //then
        assertThrows(RebelNotFoundException.class,
                ()-> rebelDeleteService.execute(expectedRebelToDelete.getId()));


    }
}
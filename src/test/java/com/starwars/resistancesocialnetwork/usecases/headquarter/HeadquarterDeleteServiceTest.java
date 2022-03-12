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
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class HeadquarterDeleteServiceTest {
    @Mock
    HeadQuartersPersistenceGateway headQuartersPersistenceGateway;
    @InjectMocks
    HeadquarterDeleteService deleteService;

    @Test
    void whenInformedAValidIdThenItsShouldBeDeleted() throws HeadquarterNotFoundException {
        //given
        Headquarter expectedHeadQuarterToDelete = HeadQuarterDomainBuilder.builder().build().toDomain();

        //when
        Mockito.when(headQuartersPersistenceGateway.existById(expectedHeadQuarterToDelete.getId())).thenReturn(true);
        Mockito.doNothing().when(headQuartersPersistenceGateway).deleteById(expectedHeadQuarterToDelete.getId());

        //then
        deleteService.execute(expectedHeadQuarterToDelete.getId());

        Mockito.verify(headQuartersPersistenceGateway, Mockito.times(1)).existById(expectedHeadQuarterToDelete.getId());
        Mockito.verify(headQuartersPersistenceGateway, Mockito.times(1)).deleteById(expectedHeadQuarterToDelete.getId());
    }

    @Test
    void whenInformedAInvalidIdThenItsThrowsException() {
        //given
        Headquarter expectedHeadQuarterToDelete = HeadQuarterDomainBuilder.builder().build().toDomain();

        //when
        Mockito.when(headQuartersPersistenceGateway.existById(expectedHeadQuarterToDelete.getId())).thenReturn(false);

        //then
        assertThrows(HeadquarterNotFoundException.class,
                () -> deleteService.execute(expectedHeadQuarterToDelete.getId()));
    }
}
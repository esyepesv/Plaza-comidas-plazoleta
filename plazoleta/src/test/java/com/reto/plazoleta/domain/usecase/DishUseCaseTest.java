package com.reto.plazoleta.domain.usecase;

import com.reto.plazoleta.domain.model.DishModel;
import com.reto.plazoleta.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DishUseCaseTest {

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @InjectMocks
    private DishUseCase dishUseCase;

    @Test
    void saveDish() {
        DishModel dishModel = new DishModel();
        dishUseCase.saveDish(dishModel);

        verify(dishPersistencePort).saveDish(dishModel);
    }


    @Test
    void getDish() {
        long dishId = 1L;

        DishModel expectedDish = new DishModel();
        expectedDish.setId(dishId);
        when(dishPersistencePort.getDish(dishId)).thenReturn(expectedDish);

        DishModel actualDish = dishUseCase.getDish(dishId);

        assertNotNull(actualDish);
        assertEquals(dishId, actualDish.getId());
        verify(dishPersistencePort).getDish(dishId);
    }

    @Test
    void updateDish() {
        DishModel dishModel = new DishModel();
        long dishId = 1L;
        dishModel.setId(dishId);

        dishUseCase.updateDish(dishModel);

        verify(dishPersistencePort).updateDish(dishModel);
    }
}
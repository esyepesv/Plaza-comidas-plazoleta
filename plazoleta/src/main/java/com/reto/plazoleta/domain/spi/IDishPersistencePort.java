package com.reto.plazoleta.domain.spi;

import com.reto.plazoleta.domain.model.DishModel;

public interface IDishPersistencePort {
    void saveDish(DishModel dish);
    DishModel getDish(Long id);
    void updateDish(DishModel dish);

}

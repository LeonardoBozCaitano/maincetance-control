package br.com.maintenance.handlers.mappers;

import br.com.maintenance.handlers.dtos.SavePersonInput;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.entities.ProductEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductMapperTest {

    @InjectMocks
    private ProductMapper productMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toEntity() {
        final String name = "Moto X";
        final String type = "Celular";
        final String brand = "Motorola";

        ProductEntity entity = productMapper.toEntity(name, type, brand);

        assertEquals(name, entity.getName());
        assertEquals(type, entity.getType());
        assertEquals(brand, entity.getBrand());
    }

}
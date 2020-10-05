package br.com.maintenance.handlers.mappers;

import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.OrderEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.entities.ProductEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderMapperTest {

    @InjectMocks
    private OrderMapper orderMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toEntity() {
        PersonEntity person = new PersonEntity();
        EmployeeEntity employee = new EmployeeEntity();
        ProductEntity product = new ProductEntity();
        String description = "Tela do celular quebrou";

        OrderEntity entity = orderMapper.toEntity(person, employee, product, description);

        assertEquals(product, entity.getProduct());
        assertEquals(employee, entity.getEmployee());
        assertEquals(person, entity.getClient());
        assertEquals(LocalDate.now(), entity.getCreationDate());
        assertEquals(description, entity.getDescription());
    }

}
package br.com.maintenance.handlers.mappers;

import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.OrderEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderEntity toEntity(PersonEntity person, EmployeeEntity employee, ProductEntity product, String description) {
        return new OrderEntity(person, employee, product, description);
    }
}

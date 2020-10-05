package br.com.maintenance.handlers.mappers;

import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.OrderEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    /**
     * transform data into the order entity.
     *
     * @param employee the order assigned employee
     * @param person the Client.
     * @param product the product
     * @param description the description of the problem
     * @return an order entity.
     */
    public OrderEntity toEntity(PersonEntity person, EmployeeEntity employee, ProductEntity product, String description) {
        return new OrderEntity(person, employee, product, description);
    }
}

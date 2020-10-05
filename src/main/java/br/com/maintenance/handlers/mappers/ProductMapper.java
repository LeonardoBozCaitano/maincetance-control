package br.com.maintenance.handlers.mappers;

import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    /**
     * Create a product with the input.
     * @param name name of the product
     * @param type type of the product
     * @param brand brand of the product
     * @return Product entity.
     */
    public ProductEntity toEntity(String name, String type, String brand) {
        return new ProductEntity(name, type, brand);
    }
}

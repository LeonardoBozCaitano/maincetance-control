package br.com.maintenance.handlers.mappers;

import br.com.maintenance.persistence.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity toEntity(String name, String type, String brand) {
        return new ProductEntity(name, type, brand);
    }
}

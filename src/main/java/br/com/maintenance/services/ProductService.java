package br.com.maintenance.services;

import br.com.maintenance.core.exceptions.Exceptions;
import br.com.maintenance.handlers.dtos.SaveProductInput;
import br.com.maintenance.handlers.mappers.ProductMapper;
import br.com.maintenance.persistence.entities.ProductEntity;
import br.com.maintenance.persistence.repositories.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    ProductService(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public ProductEntity getOneOrFail(String id) {
        Optional<ProductEntity> person = productRepository.findById(id);
        if (person.isPresent()) {
            return person.get();
        } else {
            throw Exceptions.productNotFound();
        }
    }

    public ProductEntity saveProduct(SaveProductInput input) {
        return productRepository.save(productMapper.toEntity(input.getName(), input.getType(), input.getBrand()));
    }

}

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

    /**
     * Return every saved product.
     *
     * @return product list
     */
    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    /**
     * Return the product by id, and throws exception if doesn't find.
     *
     * @return Product Entity
     */
    public ProductEntity getOneOrFail(String id) {
        Optional<ProductEntity> person = productRepository.findById(id);
        if (person.isPresent()) {
            return person.get();
        } else {
            throw Exceptions.productNotFound();
        }
    }

    /**
     * Save an product
     *
     * @param input input with the product attributes
     * @return an saved product entity.
     */
    public ProductEntity saveProduct(SaveProductInput input) {
        return productRepository.save(productMapper.toEntity(input.getName(), input.getType(), input.getBrand()));
    }

}

package br.com.maintenance.services;

import br.com.maintenance.handlers.dtos.SaveProductInput;
import br.com.maintenance.handlers.mappers.ProductMapper;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.entities.ProductEntity;
import br.com.maintenance.persistence.repositories.product.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll() {
        ProductEntity productEntity = new ProductEntity("Iphone 11", "Celular", "Apple");

        List<ProductEntity> responseList = Collections.singletonList(productEntity);
        Mockito.when(productRepository.findAll()).thenReturn(responseList);

        List<ProductEntity> response = productService.getAll();

        assertEquals(1L, response.size());
        assertEquals(productEntity, response.get(0));
    }

    @Test
    public void saveProduct() {
        final String name = "Moto X";
        final String type = "Celular";
        final String brand = "Motorola";
        SaveProductInput input = new SaveProductInput(name, type, brand);
        ProductEntity productEntity = new ProductEntity(name, type, brand);

        Mockito.when(productMapper.toEntity(input.getName(), input.getType(), input.getBrand())).thenReturn(productEntity);
        Mockito.when(productRepository.save(productEntity)).thenReturn(productEntity);

        ProductEntity response = productService.saveProduct(input);

        assertEquals(name, response.getName());
        assertEquals(type, response.getType());
        assertEquals(brand, response.getBrand());
    }

    @Test
    public void getOneOrFail_success() {
        UUID id = UUID.randomUUID();
        ProductEntity productEntity = new ProductEntity();
        Mockito.when(productRepository.findById(id.toString())).thenReturn(Optional.of(productEntity));

        ProductEntity response = productService.getOneOrFail(id.toString());

        assertEquals(productEntity, response);
    }

    @Test
    public void getOneOrFail_fail() {
        UUID id = UUID.randomUUID();
        Mockito.when(productRepository.findById(id.toString())).thenReturn(Optional.empty());

        try {
            ProductEntity response = productService.getOneOrFail(id.toString());
            Assert.fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Product not found");
        }
    }
}
package br.com.maintenance.persistence.repositories.product;

import br.com.maintenance.App;
import br.com.maintenance.persistence.entities.ProductEntity;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    public void clean(){
        productRepository.deleteAll();
    }
    @Test
    public void saveOne() {
        ProductEntity productEntity = new ProductEntity("Modelo 1", "Televisão", "LG");

        ProductEntity response = productRepository.save(productEntity);

        assertEquals(productEntity.getName(), response.getName());
        assertEquals(productEntity.getType(), response.getType());
        assertEquals(productEntity.getBrand(), response.getBrand());

    }
}
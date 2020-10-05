package br.com.maintenance.handlers;

import br.com.maintenance.handlers.dtos.SaveProductInput;
import br.com.maintenance.persistence.entities.ProductEntity;
import br.com.maintenance.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductHandler {

    private ProductService productService;

    ProductHandler(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ProductEntity> getAllProducts() {
        try {
            return this.productService.getAll();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ProductEntity saveProduct(@RequestBody SaveProductInput input) {
        try {
            return this.productService.saveProduct(input);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}

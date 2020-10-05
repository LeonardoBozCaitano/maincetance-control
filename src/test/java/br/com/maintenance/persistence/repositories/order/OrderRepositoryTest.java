package br.com.maintenance.persistence.repositories.order;

import br.com.maintenance.App;
import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.OrderEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.entities.ProductEntity;
import br.com.maintenance.persistence.repositories.employee.EmployeeRepository;
import br.com.maintenance.persistence.repositories.person.PersonRepository;
import br.com.maintenance.persistence.repositories.product.ProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    public void clean() {
        orderRepository.deleteAll();
        employeeRepository.deleteAll();
        productRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    public void getAllEmployeeOrders() {
         PersonEntity personEntity1 = personRepository.save(new PersonEntity("João da Silva", "Rua 1, Blumenau", "1111-1111", "joaodasilva@gmail.com"));
        PersonEntity personEntity2 = personRepository.save(new PersonEntity("Maria da Silva", "Rua 2, Blumenau", "2222-1111", "mariadasilva@gmail.com"));
        PersonEntity personEntity3 = personRepository.save(new PersonEntity("Roberto da Silva", "Rua 3, Blumenau", "3333-1111", "robertodasilva@gmail.com"));

        EmployeeEntity employeeEntity1 = employeeRepository.save(new EmployeeEntity(personEntity1));
        EmployeeEntity employeeEntity2 = employeeRepository.save(new EmployeeEntity(personEntity2));

        ProductEntity productEntity1 = productRepository.save(new ProductEntity("Iphone 11", "Celular", "Apple"));
        ProductEntity productEntity2 = productRepository.save(new ProductEntity("Moto G", "Celular", "Motorola"));

        orderRepository.save(new OrderEntity(personEntity2, employeeEntity1, productEntity1, "Derrubei na piscina"));
        orderRepository.save(new OrderEntity(personEntity3, employeeEntity1, productEntity2, "Quebrou a tela"));
        orderRepository.save(new OrderEntity(personEntity3, employeeEntity2, productEntity2, "Parou de carregar"));

        List<OrderEntity> response = orderRepository.getAllEmployeeOrders(employeeEntity1.getId());

        assertEquals(2, response.size());
    }
}
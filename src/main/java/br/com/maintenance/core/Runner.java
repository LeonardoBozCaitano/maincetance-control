package br.com.maintenance.core;

import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.OrderEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.entities.ProductEntity;
import br.com.maintenance.services.EmployeeService;
import br.com.maintenance.services.OrderService;
import br.com.maintenance.services.PersonService;
import br.com.maintenance.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Runner {

    private final ProductService productService;
    private final PersonService personService;
    private final EmployeeService employeeService;
    private final OrderService orderService;

    Logger logger = LoggerFactory.getLogger(Runner.class);

    Runner(ProductService productService, PersonService personService, EmployeeService employeeService, OrderService orderService) {
        this.productService = productService;
        this.personService = personService;
        this.employeeService = employeeService;
        this.orderService = orderService;
    }

    /**
     * When the application is ready, starts the database migration
     */
    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        logger.info("Application Ready!");
    }
}

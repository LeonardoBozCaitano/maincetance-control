package br.com.maintenance.services;

import br.com.maintenance.core.exceptions.Exceptions;
import br.com.maintenance.handlers.dtos.SaveOrderInput;
import br.com.maintenance.handlers.mappers.OrderMapper;
import br.com.maintenance.persistence.entities.*;
import br.com.maintenance.persistence.repositories.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final EmployeeService employeeService;
    private final PersonService personService;
    private final ProductService productService;

    OrderService(OrderRepository orderRepository, EmployeeService employeeService, PersonService personService,
                 ProductService productService, OrderMapper orderMapper){
        this.orderRepository = orderRepository;
        this.employeeService = employeeService;
        this.personService = personService;
        this.productService = productService;
        this.orderMapper = orderMapper;
    }

    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    public OrderEntity saveOrder(SaveOrderInput dto) {
        EmployeeEntity employeeEntity = employeeService.getOneOrFail(dto.getEmployeeId());
        PersonEntity personEntity = personService.getOneOrFail(dto.getClientId());
        ProductEntity productEntity = productService.getOneOrFail(dto.getProductId());

        OrderEntity orderEntity = orderMapper.toEntity(personEntity, employeeEntity, productEntity, dto.getDescription());
        return orderRepository.save(orderEntity);
    }

    public OrderEntity getOneOrFail(String id) {
        Optional<OrderEntity> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw Exceptions.orderNotFound();
        }
    }

    public OrderEntity startWorkAtOrder(String id) {
        OrderEntity orderEntity = getOneOrFail(id);
        orderEntity.setStatusInProgress();
        return orderRepository.save(orderEntity);
    }

    public OrderEntity stopWorkAtOrder(String id) {
        OrderEntity orderEntity = getOneOrFail(id);
        orderEntity.setStatusDone();
        return orderRepository.save(orderEntity);
    }

    public List<OrderEntity> getAllEmployeeOrders(String id) {
        EmployeeEntity employee = employeeService.getOneOrFail(id);
        return orderRepository.getAllEmployeeOrders(employee.getId());
    }
}

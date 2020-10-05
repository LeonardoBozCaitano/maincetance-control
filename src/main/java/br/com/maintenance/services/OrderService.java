package br.com.maintenance.services;

import br.com.maintenance.core.exceptions.Exceptions;
import br.com.maintenance.handlers.dtos.SaveOrderInput;
import br.com.maintenance.handlers.mappers.OrderMapper;
import br.com.maintenance.persistence.entities.*;
import br.com.maintenance.persistence.enums.OrderStatus;
import br.com.maintenance.persistence.repositories.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    /**
     * Return every saved product.
     *
     * @return product list
     */
    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    /**
     * save an order
     *
     * @param dto order data
     * @return saved order
     */
    public OrderEntity saveOrder(SaveOrderInput dto) {
        EmployeeEntity employeeEntity = employeeService.getOneOrFail(dto.getEmployeeId());
        PersonEntity personEntity = personService.getOneOrFail(dto.getClientId());
        ProductEntity productEntity = productService.getOneOrFail(dto.getProductId());

        OrderEntity orderEntity = orderMapper.toEntity(personEntity, employeeEntity, productEntity, dto.getDescription());
        return orderRepository.save(orderEntity);
    }

    /**
     * Return the order by id, and throws exception if doesn't find it.
     *
     * @return Order Entity
     */
    public OrderEntity getOneOrFail(String id) {
        Optional<OrderEntity> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw Exceptions.orderNotFound();
        }
    }

    /**
     * This method, save when the employee started to work at the Order.
     *
     * @param id order Id.
     * @return The order with new attributes.
     */
    public OrderEntity startWorkAtOrder(String id) {
        OrderEntity orderEntity = getOneOrFail(id);
        if (orderEntity.getStartDate() != null) {
            throw Exceptions.orderAlreadyStarted();
        }
        orderEntity.setStartDate(LocalDate.now());
        orderEntity.setStatus(OrderStatus.IN_PROGRESS);

        return orderRepository.save(orderEntity);
    }

    /**
     * This method, save when the employee stoped to work at the Order.
     *
     * @param id order Id.
     * @return The order with new attributes.
     */
    public OrderEntity stopWorkAtOrder(String id) {
        OrderEntity orderEntity = getOneOrFail(id);
        if (orderEntity.getEndDate() != null) {
            throw Exceptions.orderAlreadyDone();
        }
        orderEntity.setEndDate(LocalDate.now());
        orderEntity.setStatus(OrderStatus.DONE);

        return orderRepository.save(orderEntity);
    }

    /**
     * This method returs all the waiting or in progress order of a employee
     *
     * @param id employee id
     * @return The active orders.
     */
    public List<OrderEntity> getAllEmployeeOrders(String id) {
        EmployeeEntity employee = employeeService.getOneOrFail(id);
        return orderRepository.getAllEmployeeOrders(id);
    }
}

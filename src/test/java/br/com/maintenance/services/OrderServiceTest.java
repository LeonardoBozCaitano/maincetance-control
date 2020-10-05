package br.com.maintenance.services;

import br.com.maintenance.handlers.dtos.SaveOrderInput;
import br.com.maintenance.handlers.mappers.OrderMapper;
import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.OrderEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.entities.ProductEntity;
import br.com.maintenance.persistence.enums.OrderStatus;
import br.com.maintenance.persistence.repositories.order.OrderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;

    @Mock
    private EmployeeService employeeService;
    @Mock
    private PersonService personService;
    @Mock
    private ProductService productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll() {
        OrderEntity orderEntity = new OrderEntity();

        List<OrderEntity> responseList = Collections.singletonList(orderEntity);
        Mockito.when(orderRepository.findAll()).thenReturn(responseList);

        List<OrderEntity> response = orderService.getAll();

        assertEquals(1L, response.size());
        assertEquals(orderEntity, response.get(0));
    }

    @Test
    public void saveOrder_success() {
        SaveOrderInput input = new SaveOrderInput("123", "456", "789", "A tela do meu celular rachou");
        PersonEntity personEntity = new PersonEntity();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        ProductEntity productEntity = new ProductEntity();
        OrderEntity outputOrder =  new OrderEntity(personEntity, employeeEntity, productEntity, input.getDescription());

        Mockito.when(personService.getOneOrFail(input.getClientId())).thenReturn(personEntity);
        Mockito.when(employeeService.getOneOrFail(input.getEmployeeId())).thenReturn(employeeEntity);
        Mockito.when(productService.getOneOrFail(input.getProductId())).thenReturn(productEntity);

        Mockito.when(orderMapper.toEntity(personEntity, employeeEntity, productEntity, input.getDescription())).thenReturn(outputOrder);
        Mockito.when(orderRepository.save(outputOrder)).thenReturn(outputOrder);

        OrderEntity order = orderService.saveOrder(input);

        assertEquals(order, outputOrder);
        assertEquals(order.getDescription(), input.getDescription());
        assertEquals(order.getClient(), outputOrder.getClient());
        assertEquals(order.getEmployee(), outputOrder.getEmployee());
        assertEquals(order.getProduct(), outputOrder.getProduct());
    }

    @Test
    public void getOneOrFail_success() {
        UUID id = UUID.randomUUID();
        OrderEntity orderEntity = new OrderEntity();
        Mockito.when(orderRepository.findById(id.toString())).thenReturn(Optional.of(orderEntity));

        OrderEntity response = orderService.getOneOrFail(id.toString());

        assertEquals(orderEntity, response);
    }

    @Test
    public void getOneOrFail_fail() {
        UUID id = UUID.randomUUID();
        Mockito.when(orderRepository.findById(id.toString())).thenReturn(Optional.empty());

        try {
            orderService.getOneOrFail(id.toString());
            Assert.fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Order not found");
        }
    }

    @Test
    public void startWorkAtOrder_success() {
        UUID id = UUID.randomUUID();
        OrderEntity orderEntity = new OrderEntity();
        Mockito.when(orderRepository.findById(id.toString())).thenReturn(Optional.of(orderEntity));
        Mockito.when(orderRepository.save(orderEntity)).thenReturn(orderEntity);

        OrderEntity response = orderService.startWorkAtOrder(id.toString());

        assertEquals(orderEntity, response);
        assertEquals(OrderStatus.IN_PROGRESS, response.getStatus());
        assertNotNull(response.getStartDate());
    }

    @Test
    public void startWorkAtOrder_orderAlreadyStarted() {
        UUID id = UUID.randomUUID();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStartDate(LocalDate.now());
        Mockito.when(orderRepository.findById(id.toString())).thenReturn(Optional.of(orderEntity));
        Mockito.when(orderRepository.save(orderEntity)).thenReturn(orderEntity);

        try {
            orderService.startWorkAtOrder(id.toString());
            Assert.fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Order already started");
        }
    }

    @Test
    public void stopWorkAtOrder_success() {
        UUID id = UUID.randomUUID();
        OrderEntity orderEntity = new OrderEntity();
        Mockito.when(orderRepository.findById(id.toString())).thenReturn(Optional.of(orderEntity));
        Mockito.when(orderRepository.save(orderEntity)).thenReturn(orderEntity);

        OrderEntity response = orderService.stopWorkAtOrder(id.toString());

        assertEquals(orderEntity, response);
        assertEquals(OrderStatus.DONE, response.getStatus());
        assertNotNull(response.getEndDate());
    }

    @Test
    public void stopWorkAtOrder_orderAlreadyDone() {
        UUID id = UUID.randomUUID();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setEndDate(LocalDate.now());
        Mockito.when(orderRepository.findById(id.toString())).thenReturn(Optional.of(orderEntity));
        Mockito.when(orderRepository.save(orderEntity)).thenReturn(orderEntity);

        try {
            orderService.stopWorkAtOrder(id.toString());
            Assert.fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Order already done");
        }
    }

    @Test
    public void getAllEmployeeOrders() {
        String id = UUID.randomUUID().toString();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(id);

        List<OrderEntity> orderEntityList = new ArrayList<>();
        orderEntityList.add(new OrderEntity());
        orderEntityList.add(new OrderEntity());
        orderEntityList.add(new OrderEntity());

        Mockito.when(employeeService.getOneOrFail(id)).thenReturn(employeeEntity);
        Mockito.when(orderRepository.getAllEmployeeOrders(id)).thenReturn(orderEntityList);

        List<OrderEntity> response = orderService.getAllEmployeeOrders(id);

        assertEquals(orderEntityList.size(), response.size());
    }
}
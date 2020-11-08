package br.com.maintenance.handlers;

import br.com.maintenance.handlers.dtos.SaveOrderInput;
import br.com.maintenance.persistence.entities.OrderEntity;
import br.com.maintenance.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderHandler {

    private OrderService orderService;

    OrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public OrderEntity saveOrder(@RequestBody SaveOrderInput dto) {
        try {
            return orderService.saveOrder(dto);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}/startWork")
    public OrderEntity startWorkAtOrder(@PathVariable("id") String id) {
        try {
            return orderService.startWorkAtOrder(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}/stopWork")
    public OrderEntity stopWorkAtOrder(@PathVariable("id") String id) {
        try {
            return orderService.stopWorkAtOrder(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/employee/{employeeId}")
    public List<OrderEntity> getAllEmployeeOrders(@PathVariable("employeeId")  String employeeId) {
        try {
            return orderService.getAllEmployeeOrders(employeeId);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}

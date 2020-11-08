package br.com.maintenance.persistence.repositories.order;

import br.com.maintenance.persistence.entities.OrderEntity;

import java.util.List;

public interface OrderRepositoryCustom {

    List<OrderEntity> getAllEmployeeOrders(String employeeId);

}

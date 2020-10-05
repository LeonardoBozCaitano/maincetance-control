package br.com.maintenance.persistence.repositories.order;

import br.com.maintenance.persistence.entities.OrderEntity;

import java.util.List;

public interface OrderRepositoryCustom {

    /**
     * Get all employee active orders.
     *
     * @param employeeId employee Id.
     * @return List of active orders.
     */
    List<OrderEntity> getAllEmployeeOrders(String employeeId);

}

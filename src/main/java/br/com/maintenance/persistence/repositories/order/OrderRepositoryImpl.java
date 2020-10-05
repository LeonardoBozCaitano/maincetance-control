package br.com.maintenance.persistence.repositories.order;

import br.com.maintenance.persistence.config.RepositoryBaseImpl;
import br.com.maintenance.persistence.entities.OrderEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.entities.QOrderEntity;
import br.com.maintenance.persistence.entities.QPersonEntity;
import br.com.maintenance.persistence.repositories.person.PersonRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderRepositoryImpl extends RepositoryBaseImpl implements OrderRepositoryCustom {

    @Override
    public List<OrderEntity> getAllEmployeeOrders(String employeeId) {
        QOrderEntity qOrderEntity = QOrderEntity.orderEntity;

        JPAQuery<OrderEntity> query = select(qOrderEntity).from(qOrderEntity);
        query.where(qOrderEntity.employee.id.eq(employeeId));

        return query.fetch();
    }
}

package br.com.maintenance.persistence.repositories.order;

import br.com.maintenance.persistence.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String>, OrderRepositoryCustom {

}

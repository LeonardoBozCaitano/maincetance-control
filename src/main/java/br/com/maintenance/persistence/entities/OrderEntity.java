package br.com.maintenance.persistence.entities;

import br.com.maintenance.core.exceptions.Exceptions;
import br.com.maintenance.persistence.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "maintenenceOrder")
@NoArgsConstructor
public class OrderEntity implements Serializable {

    public OrderEntity(PersonEntity client, EmployeeEntity employee, ProductEntity product, String description) {
        this.client = client;
        this.employee = employee;
        this.product = product;
        this.description = description;
        this.creationDate = LocalDate.now();
        this.status = OrderStatus.WAITING;
    }

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="client", nullable=false, referencedColumnName="id")
    private PersonEntity client;

    @NotNull
    @ManyToOne
    @JoinColumn(name="product", nullable=false, referencedColumnName="id")
    private ProductEntity product;

    @NotNull
    @ManyToOne
    @JoinColumn(name="employee", nullable=false, referencedColumnName="id")
    private EmployeeEntity employee;

    @NotEmpty
    @NotNull
    private String description;

    @NotNull
    private OrderStatus status;

    @NotNull
    private LocalDate creationDate;

    private LocalDate startDate;

    private LocalDate endDate;

    /**
     * Set the status IN_PROGRESS and the datetime when it happens.
     */
    public void setStatusInProgress() {
        if (this.getStartDate() != null) {
            throw Exceptions.orderAlreadyStarted();
        }
        this.setStartDate(LocalDate.now());
        this.setStatus(OrderStatus.IN_PROGRESS);
    }

    /**
     * Set the status Done and the datetime when it happens.
     */
    public void setStatusDone() {
        if (this.getEndDate() != null) {
            throw Exceptions.orderAlreadyDone();
        }
        this.setEndDate(LocalDate.now());
        this.setStatus(OrderStatus.DONE);
    }
}

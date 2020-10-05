package br.com.maintenance.persistence.entities;

import br.com.maintenance.persistence.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "maintecenceOrder")
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

}

package br.com.maintenance.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity implements Serializable {

    public EmployeeEntity(PersonEntity personEntity) {
        this.setPerson(personEntity);
    }

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person")
    private PersonEntity person;

}

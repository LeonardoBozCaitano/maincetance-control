package br.com.maintenance.handlers.mappers;

import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    /**
     * create a employee entity with the person
     *
     * @param personEntity Person entity
     * @return Employee Entity
     */
    public EmployeeEntity toEntity(PersonEntity personEntity) {
        return new EmployeeEntity(personEntity);
    }
}

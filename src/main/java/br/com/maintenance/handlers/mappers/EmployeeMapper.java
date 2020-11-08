package br.com.maintenance.handlers.mappers;

import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeEntity toEntity(PersonEntity personEntity) {
        return new EmployeeEntity(personEntity);
    }
}

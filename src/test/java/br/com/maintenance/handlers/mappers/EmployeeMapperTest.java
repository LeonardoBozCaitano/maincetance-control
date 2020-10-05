package br.com.maintenance.handlers.mappers;

import br.com.maintenance.handlers.dtos.SaveEmployeeInput;
import br.com.maintenance.handlers.dtos.SavePersonInput;
import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeMapperTest {

    @InjectMocks
    private EmployeeMapper employeeMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toEntity() {
        PersonEntity personEntity = new PersonEntity("João da Silva", "Rua 1, Blumenau", "9999-1111", "joaodasilva@gmail.com");

        EmployeeEntity entity = employeeMapper.toEntity(personEntity);

        assertEquals(personEntity, entity.getPerson());
    }

}
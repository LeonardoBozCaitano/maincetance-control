package br.com.maintenance.services;

import br.com.maintenance.handlers.dtos.SaveEmployeeInput;
import br.com.maintenance.handlers.mappers.EmployeeMapper;
import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.entities.ProductEntity;
import br.com.maintenance.persistence.repositories.employee.EmployeeRepository;
import br.com.maintenance.persistence.repositories.product.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PersonService personService;

    @Mock
    private EmployeeMapper employeeMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll() {
        EmployeeEntity employeeEntity = new EmployeeEntity(new PersonEntity());

        List<EmployeeEntity> responseList = Collections.singletonList(employeeEntity);
        Mockito.when(employeeRepository.findAll()).thenReturn(responseList);

        List<EmployeeEntity> response = employeeService.getAll();

        assertEquals(1L, response.size());
        assertEquals(employeeEntity, response.get(0));
    }

    @Test
    public void getOneOrFail_success() {
        String id = UUID.randomUUID().toString();
        EmployeeEntity employeeEntity = new EmployeeEntity(id, new PersonEntity());
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(employeeEntity));

        EmployeeEntity response = employeeService.getOneOrFail(id);

        assertEquals(employeeEntity, response);
    }

    @Test
    public void getOneOrFail_fail() {
        UUID id = UUID.randomUUID();
        Mockito.when(employeeRepository.findById(id.toString())).thenReturn(Optional.empty());

        try {
            EmployeeEntity response = employeeService.getOneOrFail(id.toString());
            Assert.fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Employee not found");
        }
    }

    @Test
    public void saveEmployee() {
        SaveEmployeeInput input = new SaveEmployeeInput("123");
        PersonEntity personEntity = new PersonEntity();
        EmployeeEntity employeeEntity = new EmployeeEntity();

        Mockito.when(personService.getOneOrFail(input.getPersonId())).thenReturn(personEntity);
        Mockito.when(employeeMapper.toEntity(personEntity)).thenReturn(employeeEntity);
        Mockito.when(employeeRepository.save(employeeEntity)).thenReturn(employeeEntity);

        EmployeeEntity response = employeeService.save(input);

        assertEquals(employeeEntity, response);
    }
}
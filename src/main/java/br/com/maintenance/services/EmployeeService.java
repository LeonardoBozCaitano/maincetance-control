package br.com.maintenance.services;

import br.com.maintenance.core.exceptions.Exceptions;
import br.com.maintenance.handlers.dtos.SaveEmployeeInput;
import br.com.maintenance.handlers.mappers.EmployeeMapper;
import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.persistence.entities.PersonEntity;
import br.com.maintenance.persistence.repositories.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PersonService personService;
    private final EmployeeMapper employeeMapper;

    EmployeeService(EmployeeRepository employeeRepository, PersonService personService, EmployeeMapper employeeMapper){
        this.employeeRepository = employeeRepository;
        this.personService = personService;
        this.employeeMapper = employeeMapper;
    }

    /**
     * Return all employees.
     *
     * @return Employee list.
     */
    public List<EmployeeEntity> getAll() {
        return employeeRepository.findAll();
    }

    /**
     * Return the employee by id, and throws exception if doesn't find it.
     *
     * @return Employee Entity
     */
    public EmployeeEntity getOneOrFail(String id) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw Exceptions.employeeNotFound();
        }
    }

    /**
     * Save a employee, validating if the person exists.
     *
     * @param dto input
     * @return The employee saved
     */
    public EmployeeEntity save(SaveEmployeeInput dto) {
        PersonEntity personEntity = personService.getOneOrFail(dto.getPersonId());
        return employeeRepository.save(employeeMapper.toEntity(personEntity));
    }
}

package br.com.maintenance.handlers;

import br.com.maintenance.handlers.dtos.SaveEmployeeInput;
import br.com.maintenance.persistence.entities.EmployeeEntity;
import br.com.maintenance.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeHandler {

    private final EmployeeService employeeService;

    EmployeeHandler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<EmployeeEntity> getAll() {
        try {
            return employeeService.getAll();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public EmployeeEntity saveEmployee(@RequestBody SaveEmployeeInput input) {
        try {
            return employeeService.save(input);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
